package com.service;

import com.entity.Assignment;
import com.entity.StudentAssignment;

import java.util.List;

public interface AssignmentService {
    boolean createAssignment(String name,String deadline, String course_id, String message, List<String> filePaths);
    List<Assignment> findAssignmentList(String course_id);
    List<String> findAssignmentFileList(String assignment_id);
    List<StudentAssignment> getStudentAssignmentList(String assignment_id);
    boolean submitAssignment(String assignmentID,String student,String path);
    StudentAssignment getStudentAssignment(String assignment_id,String student);
    boolean giveStudentScore(String assignment,String student,String score);
}
