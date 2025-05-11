package com.example.category.model.category;

import com.example.category.model.Inputable;
import com.example.category.utils.Input;

public class Category implements Inputable{
    private int id;
    private String name;
    private String description;
    private CatalogStatus status;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public CatalogStatus getStatus(){
        return status;
    }

    public void setStatus(CatalogStatus status){
        this.status = status;
    }

    public Category(){
    }

    public Category(int id, String name, String description, CatalogStatus status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public void input(){
        this.id = Integer.parseInt(Input.input.nextLine());
        this.name = Input.input.nextLine();
        this.description = Input.input.nextLine();
        this.status = CatalogStatus.valueOf(Input.input.nextLine());
    }
}
