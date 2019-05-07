package com.bean;

public class StudentCount {
    private String selectCourses;
    private String dropCourses;
    private String assignment;
    private String time;

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getAssignment() {
        return assignment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDropCourses() {
        return dropCourses;
    }

    public String getSelectCourses() {
        return selectCourses;
    }

    public void setDropCourses(String dropCourses) {
        this.dropCourses = dropCourses;
    }

    public void setSelectCourses(String selectCourses) {
        this.selectCourses = selectCourses;
    }
}

