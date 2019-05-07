package com.serviceimpl;

import com.bean.CheckCreateBean;
import com.bean.CheckIssueBean;
import com.entity.Courses;
import com.entity.IssueCourses;
import com.entity.News;
import com.repository.CoursesRepository;
import com.repository.IssueCoursesRepository;
import com.repository.NewsRepository;
import com.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class DirectorImpl implements DirectorService {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private IssueCoursesRepository issueCoursesRepository;
    private static DirectorImpl directorimpl;
    @PostConstruct
    public void init(){
       directorimpl=this;
       directorimpl.coursesRepository=this.coursesRepository;
       directorimpl.newsRepository=this.newsRepository;
       directorimpl.issueCoursesRepository=this.issueCoursesRepository;
    }
    @Override
    public List<CheckCreateBean> findCreate() {
        List<CheckCreateBean> checkCreateBeanList=new ArrayList<>();
        List<Courses> coursesList=directorimpl.coursesRepository.findByApproval();
        if(coursesList!=null){
            for(int i=0;i<coursesList.size();i++){
                CheckCreateBean checkCreateBean=new CheckCreateBean();
                checkCreateBean.setId(coursesList.get(i).getId());//1
                checkCreateBean.setName(coursesList.get(i).getName());//2
                checkCreateBean.setNote(coursesList.get(i).getMessage());//3
                checkCreateBean.setTime(coursesList.get(i).getTime());//4
                checkCreateBean.setGrade(coursesList.get(i).getGrade());//5
                checkCreateBean.setTeacher(coursesList.get(i).getTeacher());//6
                checkCreateBeanList.add(checkCreateBean);
            }
        }
        return checkCreateBeanList;
    }

    @Override
    public boolean throughCreate(String course_id) {
        try {
            Courses courses=directorimpl.coursesRepository.findById(course_id).get();
            courses.setApproval(true);
            directorimpl.coursesRepository.save(courses);
            //----------------------
            News news=new News();
            List<News> newsList=directorimpl.newsRepository.findAll();
            String newsId="0";
            if(newsList!=null){
                newsId=newsList.size()+"";
            }
            news.setId(newsId);//1;
            news.setType("approval");//2
            news.setFromWhere("MyCourses主管");//3
            news.setToWhere(courses.getTeacher());//4
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH");
            String date=simpleDateFormat.format(new Date());
            news.setTime(date);//5
            news.setMessage("你创建的课程："+courses.getName()+" 已通过审批。");//6
            directorimpl.newsRepository.save(news);
            //----------------------
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<CheckIssueBean> findIssue() {
       List<CheckIssueBean> checkIssueBeanList=new ArrayList<>();
       List<IssueCourses> issueCoursesList=directorimpl.issueCoursesRepository.findByApproval();
       if(issueCoursesList!=null){
           for(int i=0;i<issueCoursesList.size();i++){
               CheckIssueBean checkIssueBean=new CheckIssueBean();
               checkIssueBean.setId(issueCoursesList.get(i).getId());//1
               Courses courses=directorimpl.coursesRepository.findById(issueCoursesList.get(i).getClass_id()).get();
               checkIssueBean.setName(courses.getName());//2
               checkIssueBean.setBegin_time(issueCoursesList.get(i).getTime());//3
               checkIssueBean.setTime(issueCoursesList.get(i).getIssue_time());//4
               checkIssueBean.setLimit_student(issueCoursesList.get(i).getLimit_student()+"");//5
               checkIssueBean.setLimit_class(issueCoursesList.get(i).getLimit_class()+"");//6
               //checkCreateBean.setName(issueCoursesList.get(i).get);
               checkIssueBeanList.add(checkIssueBean);
           }
       }
       return checkIssueBeanList;
    }

    @Override
    public boolean throughIssue(String id) {
        IssueCourses issueCourses=directorimpl.issueCoursesRepository.findById(id).get();
        issueCourses.setApproval(true);
        directorimpl.issueCoursesRepository.save(issueCourses);
        //----------------------
        News news=new News();
        List<News> newsList=directorimpl.newsRepository.findAll();
        String newsId="0";
        if(newsList!=null){
            newsId=newsList.size()+"";
        }
        news.setId(newsId);//1;
        news.setType("approval");//2
        news.setFromWhere("MyCourses主管");//3
        Courses courses=directorimpl.coursesRepository.findById(issueCourses.getClass_id()).get();
        news.setToWhere(courses.getTeacher());//4
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH");
        String date=simpleDateFormat.format(new Date());
        news.setTime(date);//5
        news.setMessage("你发布的课程："+courses.getName()+" 已通过审批。");//6
        directorimpl.newsRepository.save(news);
        //----------------------
        return true;
    }
}
