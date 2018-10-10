package com.ktverdov.app;

public class Person {
    private long id;
    private String name;
    private String note;
    private int imageRes;

    public Person(long id, String name, String note, int imageRes) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.imageRes = imageRes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
