package com.controller;


import com.bean.CourseID;
import com.service.CreateCoursesService;
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
@RequestMapping("create")
public class CreateCoursesController {

    List<String> filePaths=new ArrayList<>();
    CreateCoursesService createCoursesService=new CreateCoursesImpl();
    @RequestMapping(value = "createcourse",method = RequestMethod.GET)
    public boolean createCourse(HttpSession session,String courseName, String grade, String message){
        //String user=session.getAttribute("user").toString();
        try {
            String user = session.getAttribute("user").toString();
            createCoursesService.createCourse(user, courseName, grade, message, filePaths);
        }catch (Exception e){
            if(filePaths!=null&&filePaths.size()!=0){
                for(int i=0;i<filePaths.size();i++){
                    File file=new File(filePaths.get(i));
                    file.delete();
                }
            }
            e.printStackTrace();
            filePaths.clear();
            return false;
        }
        filePaths.clear();

        return true;
    }
    @RequestMapping(value = "uploadcourseware",method = RequestMethod.POST)
    public boolean addCourseware(@RequestParam("file")MultipartFile[] multipartFiles){
        if(multipartFiles!=null&&multipartFiles.length>0){
            for (MultipartFile file:multipartFiles){
                try {
                    String path = createCoursesService.createFilePath(file.getOriginalFilename());
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File(path)));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    filePaths.add(path.substring(path.lastIndexOf("\\")+1));
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
    @RequestMapping(value = "findcourseware",method = RequestMethod.GET)
    public List<String> findWareList(String courseId){
        return createCoursesService.findWareList(courseId);
    }
    @RequestMapping(value = "addcourseware",method = RequestMethod.POST)
    public boolean addWare(@RequestParam("file")MultipartFile[] multipartFiles, CourseID courseID){
        if(multipartFiles!=null&&multipartFiles.length>0){
            for (MultipartFile file:multipartFiles){
                try {
                    String path = createCoursesService.createFilePath(file.getOriginalFilename());
                    BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream(new File(path)));
                    out.write(file.getBytes());
                    out.flush();
                    out.close();
                    createCoursesService.addWare(courseID.getId(),path.substring(path.lastIndexOf("\\")+1));
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
