package com.example.lyl.myapplication.rxjava;

/**
 * @author lyl
 * @date 2017/11/15.
 */

public class Course {
    public Course(String lesson) {
        this.lesson = lesson;
    }

    private String lesson;

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
