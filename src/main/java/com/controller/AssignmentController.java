package com.controller;

import com.bean.CourseID;
import com.entity.Assignment;
import com.entity.StudentAssignment;
import com.service.AssignmentService;
import com.service.CreateCoursesService;
import com.serviceimpl.AssignmentImpl;
import com.serviceimpl.CreateCoursesImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("assignment")
public class AssignmentController {
    List<String> filePaths=new ArrayList<>();
    AssignmentService assignmentService=new AssignmentImpl();
    CreateCoursesService createCoursesService=new CreateCoursesImpl();
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public boolean createAssignment(String deadline,String course_id,String message,String name){
        boolean res=assignmentService.createAssignment(name,deadline,course_id,message,filePaths);
        filePaths=new ArrayList<>();
        return res;
    }
    @RequestMapping(value = "create.ware",method = RequestMethod.POST)
    public boolean uploadAssignmentWare(@RequestParam("file") MultipartFile[] multipartFiles){
        if(multipartFiles!=null&&multipartFiles.length>0){
            for (MultipartFile file:multipartFiles){
                try {
                    String path = createCoursesService.createFilePath(file.getOriginalFilename());
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File(path)));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    System.out.println();
                    filePaths.add(path.substring(path.lastIndexOf("\\")+1));
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
    @RequestMapping(value = "find.assignment.list",method = RequestMethod.GET)
    public List<Assignment> findAssignmentList(String course_id){
        return assignmentService.findAssignmentList(course_id);
    }
    @RequestMapping(value = "find.assignment.file.list",method = RequestMethod.GET)
    public List<String> findAssignmentFileList(String assignment_id){
        return assignmentService.findAssignmentFileList(assignment_id);
    }
    @RequestMapping(value = "get.student.assignment.list",method = RequestMethod.GET)
    public List<StudentAssignment> getStudentAssignmentList(String assignment_id){
        return assignmentService.getStudentAssignmentList(assignment_id);
    }
    @RequestMapping(value = "submit.assignment",method = RequestMethod.POST)
    public boolean submitAssignment(HttpSession session,@RequestParam("file") MultipartFile[] multipartFiles, CourseID assignmentID){
        if(multipartFiles!=null&&multipartFiles.length>0){
            for (MultipartFile file:multipartFiles){
                try {
                    //System.out.println(assignmentID.getId());
                    String path = createCoursesService.createFilePath(file.getOriginalFilename());
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File(path)));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    String savePath=path.substring(path.lastIndexOf("\\")+1);
                    System.out.println("-------------------------------------------"+assignmentID.getId());
                    assignmentService.submitAssignment(assignmentID.getId(),session.getAttribute("user").toString(),savePath);
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
    @RequestMapping(value = "get.student.assignment",method = RequestMethod.GET)
    public StudentAssignment getStudentAssignment(HttpSession session,String assignment_id){//System.out.println(assignment_id);
        return assignmentService.getStudentAssignment(assignment_id,session.getAttribute("user").toString());
    }
    @RequestMapping(value = "give.student.score",method = RequestMethod.GET)
    public boolean giveStudentScore(String assignment,String student,String score){
        return assignmentService.giveStudentScore(assignment,student,score);
    }
}
