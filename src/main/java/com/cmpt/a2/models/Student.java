package com.cmpt.a2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String hairColour;
    private double weight; // Assuming weight can be a decimal
    private double height; // Assuming height can be a decimal
    private double gpa;    // GPA is typically a decimal
    

    public Student() {
    }

    public Student(String name, String hairColour, double weight, double height, double gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.gpa = gpa;
        this.hairColour=hairColour;
    }

    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
