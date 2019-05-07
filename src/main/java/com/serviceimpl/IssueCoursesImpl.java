package com.serviceimpl;


import com.entity.Courses;
import com.entity.IssueCourses;
import com.entity.News;
import com.repository.CoursesRepository;
import com.repository.IssueCoursesRepository;
import com.repository.NewsRepository;
import com.service.IssueCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class IssueCoursesImpl implements IssueCoursesService {
    @Autowired
    private IssueCoursesRepository issueCoursesRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private NewsRepository newsRepository;
    public static IssueCoursesImpl issueCoursesimpl;
    @PostConstruct
    public void init(){
        issueCoursesimpl=this;
        issueCoursesimpl.issueCoursesRepository=this.issueCoursesRepository;
        issueCoursesimpl.coursesRepository=this.coursesRepository;
        issueCoursesimpl.newsRepository=this.newsRepository;
    }
    @Override
    public boolean issueCourses(String classId, String time, String limitClass, String limitStudent) {
        List<IssueCourses> issueCoursesList=issueCoursesimpl.issueCoursesRepository.findAll();
        String id="0";
        if(issueCoursesList!=null&&issueCoursesList.size()!=0)
            id=""+issueCoursesList.size();
        IssueCourses issueCourses=new IssueCourses();
        issueCourses.setId(id);
        issueCourses.setClass_id(classId);
        issueCourses.setTime(time);
        issueCourses.setLimit_class(Integer.parseInt(limitClass));
        issueCourses.setLimit_student(Integer.parseInt(limitStudent));

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH");
        String date=simpleDateFormat.format(new Date());

        issueCourses.setIssue_time(date);
        issueCourses.setApproval(false);

        issueCoursesimpl.issueCoursesRepository.save(issueCourses);

        News news=new News();
        List<News> newsList = issueCoursesimpl.newsRepository.findAll();
        String newsId="0";
        if(newsList!=null&&newsList.size()!=0)
            newsId=newsList.size()+"";
        news.setId(newsId);
        news.setTime(date);
        news.setType("issue");
        Optional<Courses> coursesOptional = issueCoursesimpl.coursesRepository.findById(classId);
        Courses courses = coursesOptional.get();
        news.setFromWhere(courses.getTeacher());
        news.setToWhere(courses.getTeacher());
        news.setMessage("课程：" + courses.getName() + "已发布成功，等待管理员审批。");
        issueCoursesimpl.newsRepository.save(news);

        return true;
    }
}
