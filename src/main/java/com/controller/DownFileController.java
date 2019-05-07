package com.controller;

import com.service.CreateCoursesService;
import com.serviceimpl.CreateCoursesImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping("file")
public class DownFileController {
    //CreateCoursesService createCoursesService=new CreateCoursesImpl();
    private String parentPath = "C:\\Users\\16634\\Desktop\\MyCourses\\src\\main\\java\\com\\file";
    @RequestMapping(value = "down")
    public void createAssignment(HttpServletResponse httpResponse, String path){
        System.out.println("----------------------------------------"+path);
        String fileName = path.substring(path.lastIndexOf("+")+1);
        httpResponse.setHeader("content-type", "application/octet-stream");
        httpResponse.setContentType("application/octet-stream");
        httpResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = httpResponse.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(parentPath+"\\"+path));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }
}
