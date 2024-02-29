package com.example.smsa;

public class Student {
    private String id;
    private String name;
    private String usn;
    private String sem;
    private String branch;

    public Student(String id, String name, String usn, String sem, String branch) {
        this.id = id;
        this.name = name;
        this.usn = usn;
        this.sem = sem;
        this.branch = branch;
    }

    // Getter methods for the fields

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsn() {
        return usn;
    }

    public String getSem() {
        return sem;
    }

    public String getBranch() {
        return branch;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
