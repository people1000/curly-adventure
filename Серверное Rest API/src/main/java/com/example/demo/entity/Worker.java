package com.example.demo.entity;

public class Worker extends BaseEntity{
    private String name;
    private String post;
    private String phone_number;

    public Worker(Integer id, String name, String post, String phone_number) {
        super(id);
        this.name = name;
        this.post = post;
        this.phone_number = phone_number;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
