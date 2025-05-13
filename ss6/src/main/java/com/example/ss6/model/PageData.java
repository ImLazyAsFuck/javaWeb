package com.example.ss6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageData {
    private List<Employee> employees;
    private int totalPages;
    private int currentPage;
    private String sortField;
    private String sortDirection;
}
