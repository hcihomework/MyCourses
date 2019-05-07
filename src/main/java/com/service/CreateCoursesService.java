package com.service;

import java.util.List;

public interface CreateCoursesService {
    String createCourse(String teacher, String name, String grade, String message, List<String> filePaths);
    String createFilePath(String name);
    List<String> findWareList(String courseId);
    boolean addWare(String courseId,String path);
}
