package com.example.lyl.myapplication.rxjava;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lyl
 * @date 2017/11/15.
 */

public class Student implements Serializable {


    private String name;
    private int age;

    private List<Course> courses=new ArrayList<>();

    public Student(String name){
        this.name=name;
    }

    public Student(String name, int age,List<Course> courses) {
        this.name = name;
        this.courses=courses;
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;


    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(){

    }
}
