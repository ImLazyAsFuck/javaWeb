package com.example.ss5.controller;

import com.example.ss5.model.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/contacts")
public class ContactController extends HttpServlet {
    private final List<Contact> contacts = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void init() {
        contacts.add(new Contact(nextId++, "John", "Doe", "john.doe@example.com", "555-1234"));
        contacts.add(new Contact(nextId++, "Jane", "Smith", "jane.smith@example.com", "555-5678"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if (action == null) {
            listContacts(req, resp);
            return;
        }
        
        switch (action) {
            case "add":
                showAddForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                listContacts(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if (action == null) {
            resp.sendRedirect(req.getContextPath() + "/contacts");
            return;
        }
        
        switch (action) {
            case "create":
                createContact(req, resp);
                break;
            case "update":
                updateContact(req, resp);
                break;
            case "delete":
                deleteContact(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/contacts");
                break;
        }
    }
    
    private void listContacts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("contacts", contacts);
        req.getRequestDispatcher("/views/contactList.jsp").forward(req, resp);
    }
    
    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/contactForm.jsp").forward(req, resp);
    }
    
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Contact contact = findContactById(id);
        
        if (contact == null) {
            resp.sendRedirect(req.getContextPath() + "/contacts");
            return;
        }
        
        req.setAttribute("contact", contact);
        req.setAttribute("isEdit", true);
        req.getRequestDispatcher("/views/contactForm.jsp").forward(req, resp);
    }
    
    private void createContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        
        Contact contact = new Contact(nextId++, firstName, lastName, email, phone);
        contacts.add(contact);
        
        resp.sendRedirect(req.getContextPath() + "/contacts");
    }
    
    private void updateContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        
        Contact contact = findContactById(id);
        
        if (contact != null) {
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setEmail(email);
            contact.setPhone(phone);
        }
        
        resp.sendRedirect(req.getContextPath() + "/contacts");
    }
    
    private void deleteContact(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Contact contact = findContactById(id);
        
        if (contact != null) {
            contacts.remove(contact);
        }
        
        resp.sendRedirect(req.getContextPath() + "/contacts");
    }
    
    private Contact findContactById(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }
}
