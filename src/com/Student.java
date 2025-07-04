package com;

public class Student {
    static int id;
    static String college;
    int rollNo;
    String name;
    float cgpa;

    static {
        id = 3000;
        college = "PIET";
    }

    public Student(String name, float cgpa) {
        this.name = name;
        this.cgpa = cgpa;
        this.rollNo = ++id;
    }
}