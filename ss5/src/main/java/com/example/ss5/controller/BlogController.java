package com.example.ss5.controller;

import com.example.ss5.model.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/blog/*")
public class BlogController extends HttpServlet {
    
    private final List<Post> posts = new ArrayList<>();
    private static int idCounter = 1;
    
    @Override
    public void init() throws ServletException {
        super.init();
        addSamplePosts();
    }
    
    private void addSamplePosts() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        posts.add(new Post(
            idCounter++,
            "Getting Started with Java Servlets",
            "# Introduction to Java Servlets\n\nServlets are the Java technology used to extend web server functionality. Servlets provide a component-based, platform-independent method for building web applications, without the performance limitations of CGI programs.\n\n## Key Features\n\n- Platform independent\n- Runs inside a servlet container\n- Supports request and response paradigm\n- Lifecycle management\n\n```java\n@WebServlet(\"/hello\")\npublic class HelloServlet extends HttpServlet {\n    protected void doGet(HttpServletRequest request, HttpServletResponse response) {\n        response.getWriter().println(\"Hello World!\");\n    }\n}\n```",
            "Java Developer",
            today
        ));
        
        posts.add(new Post(
            idCounter++,
            "Understanding MVC in Web Applications",
            "# Model-View-Controller Architecture\n\nThe Model-View-Controller (MVC) pattern is a widely adopted architectural pattern for developing web applications.\n\n## Components\n\n1. **Model**: Represents the application data and business logic\n2. **View**: Displays the data to the user\n3. **Controller**: Handles user input and updates the model\n\n## Benefits\n\n- Separation of concerns\n- Improved code organization\n- Better testability\n- Code reusability",
            "Architecture Expert",
            today
        ));
        
        posts.add(new Post(
            idCounter++,
            "Building Dynamic Web Pages with JSP",
            "# Java Server Pages (JSP)\n\nJSP is a technology that helps software developers create dynamically generated web pages based on HTML, XML, or other document types.\n\n## Key Features\n\n- Simplified development\n- Code reuse through components\n- Platform independent\n- Access to entire Java API\n\n## Example\n\n```jsp\n<html>\n<body>\n    <h1>Hello JSP</h1>\n    <% \n    String name = request.getParameter(\"name\");\n    if(name != null) {\n        out.println(\"Welcome, \" + name);\n    }\n    %>\n</body>\n</html>\n```",
            "Web Developer",
            today
        ));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            listPosts(request, response);
        } else if (pathInfo.equals("/post")) {
            showPost(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/blog");
        }
    }
    
    private void listPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/views/blog/postList.jsp").forward(request, response);
    }
    
    private void showPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Post post = findPostById(id);
                
                if (post != null) {
                    Post processedPost = new Post(
                        post.getId(),
                        post.getTitle(),
                        processMarkdownForJsp(post.getContent()),
                        post.getAuthor(),
                        post.getPublishDate()
                    );
                    
                    request.setAttribute("post", processedPost);
                    request.getRequestDispatcher("/views/blog/postDetail.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/blog");
    }
    
    private String processMarkdownForJsp(String markdown) {
        StringBuilder html = new StringBuilder("<p>");
        String[] lines = markdown.split("\n");
        boolean inCodeBlock = false;
        boolean inList = false;
        
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            
            if (line.startsWith("```")) {
                if (inCodeBlock) {
                    html.append("</code></pre>");
                    inCodeBlock = false;
                } else {
                    html.append("</p><pre><code>");
                    inCodeBlock = true;
                }
                continue;
            }
            
            if (inCodeBlock) {
                html.append(line).append("\n");
                continue;
            }
            
            if (line.trim().isEmpty()) {
                if (inList) {
                    html.append("</ul><p>");
                    inList = false;
                } else {
                    html.append("</p><p>");
                }
                continue;
            }
            
            if (line.startsWith("# ")) {
                html.append("</p><h1>").append(line.substring(2)).append("</h1><p>");
            } else if (line.startsWith("## ")) {
                html.append("</p><h2>").append(line.substring(3)).append("</h2><p>");
            } else if (line.startsWith("### ")) {
                html.append("</p><h3>").append(line.substring(4)).append("</h3><p>");
            } else if (line.startsWith("- ")) {
                if (!inList) {
                    html.append("</p><ul>");
                    inList = true;
                }
                html.append("<li>").append(line.substring(2)).append("</li>");
            } else {
                String processed = line;
                
                while (processed.contains("`")) {
                    int start = processed.indexOf("`");
                    int end = processed.indexOf("`", start + 1);
                    
                    if (end != -1) {
                        String before = processed.substring(0, start);
                        String code = processed.substring(start + 1, end);
                        String after = processed.substring(end + 1);
                        
                        processed = before + "<code>" + code + "</code>" + after;
                    } else {
                        break;
                    }
                }
                
                html.append(processed).append(" ");
            }
        }
        
        if (inList) {
            html.append("</ul>");
        }
        
        html.append("</p>");
        
        String result = html.toString()
            .replace("<p></p>", "")
            .replace("<p><h", "<h")
            .replace("</h1><p></p>", "</h1>")
            .replace("</h2><p></p>", "</h2>")
            .replace("</h3><p></p>", "</h3>");
            
        return result;
    }
    
    private Post findPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }
}
