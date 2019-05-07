package com.serviceimpl;

import com.bean.CheckCreateBean;
import com.bean.CheckIssueBean;
import com.bean.PreviewCourses;
import com.entity.Courses;
import com.entity.IssueCourses;
import com.entity.Selection;
import com.repository.CoursesRepository;
import com.repository.IssueCoursesRepository;
import com.repository.SelectionRepository;
import com.service.SearchCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchCoursesImpl implements SearchCoursesService {
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private IssueCoursesRepository issueCoursesRepository;
    @Autowired
    private SelectionRepository selectionRepository;
    private static SearchCoursesImpl searchCoursesImpl;
    @PostConstruct
    public void init(){
        searchCoursesImpl=this;
        searchCoursesImpl.coursesRepository=this.coursesRepository;
        searchCoursesImpl.issueCoursesRepository=this.issueCoursesRepository;
        searchCoursesImpl.selectionRepository=this.selectionRepository;
    }
    @Override
    public List<PreviewCourses> findNeedIssue(String teacher) {
        List<Courses> coursesList=searchCoursesImpl.coursesRepository.findByTeacherAndApproval(teacher,true);
        List<PreviewCourses> previewCoursesList=new ArrayList<>();
        if(coursesList!=null&&previewCoursesList.size()==0){
            for(int i=0;i<coursesList.size();i++){
                PreviewCourses previewCourses=new PreviewCourses(coursesList.get(i).getId(),coursesList.get(i).getName());
                previewCoursesList.add(previewCourses);
            }
        }
        return previewCoursesList;
    }

    @Override
    public List<CheckCreateBean> findCheckCreateList(String teacher) {
        List<Courses> coursesList=searchCoursesImpl.coursesRepository.findByTeacher(teacher);
        List<CheckCreateBean> checkCreateBeanList=new ArrayList<>();
        if(coursesList!=null&&coursesList.size()!=0){
            for(int i=0;i<coursesList.size();i++){
                CheckCreateBean checkCreateBean=new CheckCreateBean();
                checkCreateBean.setId(coursesList.get(i).getId());
                checkCreateBean.setName(coursesList.get(i).getName());
                checkCreateBean.setGrade(coursesList.get(i).getGrade());
                checkCreateBean.setNote(coursesList.get(i).getMessage());
                checkCreateBean.setTime(coursesList.get(i).getTime());
                if(coursesList.get(i).isApproval()){
                    checkCreateBean.setApproval("是");
                }else{
                    checkCreateBean.setApproval("否");
                }
                checkCreateBeanList.add(checkCreateBean);
            }
        }
        return checkCreateBeanList;
    }

    @Override
    public List<CheckIssueBean> findCheckIssueList(String teacher) {
        List<IssueCourses> issueCoursesList=searchCoursesImpl.issueCoursesRepository.findByTeacher(teacher);
        List<CheckIssueBean> checkIssueBeanList=new ArrayList<>();
        if(issueCoursesList!=null&&issueCoursesList.size()!=0){
            for(int i=0;i<issueCoursesList.size();i++) {
                System.out.println(issueCoursesList.get(i).getId());

                CheckIssueBean checkIssueBean=new CheckIssueBean();

                Courses courses = searchCoursesImpl.coursesRepository.findById(issueCoursesList.get(i).getClass_id()).get();

                checkIssueBean.setId(issueCoursesList.get(i).getId());//1
                checkIssueBean.setClass_id(issueCoursesList.get(i).getClass_id());//2
                checkIssueBean.setBegin_time(issueCoursesList.get(i).getTime());//3
                checkIssueBean.setGrade(courses.getGrade());//4
                checkIssueBean.setName(courses.getName());//5
                checkIssueBean.setLimit_class(issueCoursesList.get(i).getLimit_class()+"");//6
                checkIssueBean.setLimit_student(issueCoursesList.get(i).getLimit_student()+"");//7
                if(issueCoursesList.get(i).isApproval()){
                    checkIssueBean.setApproval("是");//8
                }else{
                    checkIssueBean.setApproval("否");//8
                }
                checkIssueBean.setTime(issueCoursesList.get(i).getIssue_time());//9
                List<Selection> selectionList=searchCoursesImpl.selectionRepository.findByIssue_course(issueCoursesList.get(i).getId());
                if(selectionList!=null&&selectionList.size()!=0){
                    checkIssueBean.setChoice_student(selectionList.size()+"");//10
                }else {
                    checkIssueBean.setChoice_student("0");//10
                }
                checkIssueBeanList.add(checkIssueBean);
            }
        }

        return checkIssueBeanList;
    }
}
