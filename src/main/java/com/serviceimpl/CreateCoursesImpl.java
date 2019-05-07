package com.serviceimpl;

import com.entity.Courses;
import com.entity.Courseware;
import com.entity.News;
import com.repository.CoursesRepository;
import com.repository.CoursewareRepository;
import com.repository.NewsRepository;
import com.service.CreateCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class CreateCoursesImpl implements CreateCoursesService {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private CoursewareRepository coursewareRepository;
    @Autowired
    private NewsRepository newsRepository;
    private static CreateCoursesImpl createCourses;
    private String parentPath = "C:\\Users\\16634\\Desktop\\MyCourses\\src\\main\\java\\com\\file";
    @PostConstruct
    public void init(){
        createCourses=this;
        createCourses.coursesRepository=this.coursesRepository;
        createCourses.coursewareRepository=this.coursewareRepository;
        createCourses.newsRepository=this.newsRepository;
    }
    @Override
    public String createCourse(String teacher, String name, String grade, String message, List<String> filePaths) {
        Courses course=new Courses();
        course.setTeacher(teacher);
        course.setName(name);
        course.setGrade(grade);
        course.setMessage(message);
        course.setApproval(false);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH");
        String date=simpleDateFormat.format(new Date());
        course.setTime(date);
        List<Courses> coursesList=createCourses.coursesRepository.findAll();
        String courseId="0";
        if(coursesList!=null&&coursesList.size()!=0)
            courseId=coursesList.size()+"";
        course.setId(courseId);
        createCourses.coursesRepository.save(course);
        if(filePaths!=null&&filePaths.size()!=0){
            for(int i=0;i<filePaths.size();i++){
                Courseware courseware=new Courseware(filePaths.get(i),courseId);
                createCourses.coursewareRepository.save(courseware);
            }
        }
        //
        News news=new News();
        List<News> newsList=createCourses.newsRepository.findAll();
        String newsId="0";
        if(newsList!=null&newsList.size()!=0)
            newsId=""+newsList.size();
        news.setId(newsId);
        news.setFromWhere(teacher);
        news.setToWhere(teacher);
        news.setType("create");
        news.setTime(date);
        news.setMessage("课程："+name+" 已创建成功,等待管理员审批。");
        createCourses.newsRepository.save(news);
        return "success";
    }

    @Override
    public String createFilePath(String name) {
        File[] files=new File(parentPath).listFiles();
        int id=files.length;
        String filePath=parentPath+"\\"+id+"+"+name;
        return filePath;
    }

    @Override
    public List<String> findWareList(String courseId) {
        List<Courseware> coursewareList=createCourses.coursewareRepository.findByCourses_id(courseId);
        List<String> stringList=new ArrayList<>();
        if(coursewareList!=null&&coursewareList.size()!=0){
            for(int i=0;i<coursewareList.size();i++){
                String path=coursewareList.get(i).getPath();
                stringList.add(path);
            }
        }
        return stringList;
    }

    @Override
    public boolean addWare(String courseId, String path) {
        try {
            Courseware courseware = new Courseware(path, courseId);
            createCourses.coursewareRepository.save(courseware);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
