package com.example.sqlite.Model;

public class Student {

    private String name;
    private String surname;
    private int mark;
    private int id;

    public Student(int id, String name, String surname, int mark) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
