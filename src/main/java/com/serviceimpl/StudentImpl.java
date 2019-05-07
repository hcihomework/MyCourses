package com.serviceimpl;

import com.bean.CheckIssueBean;
import com.bean.StudentCount;
import com.entity.*;
import com.repository.*;
import com.service.QuarterTimeService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class StudentImpl implements StudentService {
    @Autowired
    private SelectionRepository selectionRepository;
    @Autowired
    private IssueCoursesRepository issueCoursesRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;
    private static StudentImpl studentImpl;
    @PostConstruct
    public void init(){
        studentImpl=this;
        studentImpl.selectionRepository=this.selectionRepository;
        studentImpl.issueCoursesRepository=this.issueCoursesRepository;
        studentImpl.coursesRepository=this.coursesRepository;
        studentImpl.usersRepository=this.usersRepository;
        studentImpl.studentAssignmentRepository=this.studentAssignmentRepository;
    }
    @Override
    public List<CheckIssueBean> findIssueCourse() {
        List<IssueCourses> issueCoursesList=studentImpl.issueCoursesRepository.findByApprovalThrough();
        List<CheckIssueBean> checkIssueBeanList=new ArrayList<>();
        if(issueCoursesList!=null&&issueCoursesList.size()>0){
            for(IssueCourses issueCourses:issueCoursesList){
                CheckIssueBean checkIssueBean=new CheckIssueBean();
                checkIssueBean.setId(issueCourses.getId());//1
                checkIssueBean.setBegin_time(issueCourses.getTime());//2
                checkIssueBean.setLimit_class(issueCourses.getLimit_class()+"");//3
                checkIssueBean.setLimit_student(issueCourses.getLimit_student()+"");//4
                Courses courses=studentImpl.coursesRepository.findById(issueCourses.getClass_id()).get();
                checkIssueBean.setGrade(courses.getGrade());//5
                checkIssueBean.setName(courses.getName());//6
                checkIssueBean.setClass_id(issueCourses.getClass_id());//7
                List<Selection> selectionList=studentImpl.selectionRepository.findByIssue_course(issueCourses.getId());
                if(selectionList!=null&&selectionList.size()!=0){
                    checkIssueBean.setChoice_student(selectionList.size()+"");//8
                }else {
                    checkIssueBean.setChoice_student("0");//8
                }
                checkIssueBeanList.add(checkIssueBean);
            }
        }

        return checkIssueBeanList;
    }

    @Override
    public String chooseCourse(String issue_id,String student) {
        Optional<Selection> selectionOptional=studentImpl.selectionRepository.findByStudentAAndIssue_course(student,issue_id);
        if(!selectionOptional.isPresent()){

            List<Selection> studentChooseList=studentImpl.selectionRepository.findByIssue_course(issue_id);
            int chooseNumber=0;
            if(studentChooseList!=null)
                chooseNumber=studentChooseList.size();
            int limit_number=studentImpl.issueCoursesRepository.findById(issue_id).get().getLimit_student();
            if(chooseNumber<limit_number) {
                Selection selection = new Selection();
                selection.setDrop_course(false);
                selection.setIssue_course(issue_id);
                selection.setStudent(student);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = simpleDateFormat.format(new Date());
                selection.setTime(date);
                List<Selection> selectionList = studentImpl.selectionRepository.findAll();
                String id = "0";
                if (selectionList != null) {
                    id = selectionList.size() + "";
                }
                selection.setId(id);
                studentImpl.selectionRepository.save(selection);
                return "success";
            }else {
                return "limit";
            }
        }else {
            return "exist";
        }


    }

    @Override
    public List<CheckIssueBean> findMyCourses(String student) {
        List<CheckIssueBean> checkIssueBeanList=new ArrayList<>();
        List<Selection> selectionList=studentImpl.selectionRepository.findByStudent(student);
        if(selectionList!=null){
            for(Selection selection:selectionList){
                CheckIssueBean checkIssueBean=new CheckIssueBean();
                IssueCourses issueCourses=studentImpl.issueCoursesRepository.findById(selection.getIssue_course()).get();
                checkIssueBean.setId(issueCourses.getId());//1
                checkIssueBean.setClass_id(issueCourses.getClass_id());//2
                checkIssueBean.setBegin_time(issueCourses.getTime());//3
                Courses courses=studentImpl.coursesRepository.findById(issueCourses.getClass_id()).get();
                checkIssueBean.setGrade(courses.getGrade());//4
                checkIssueBean.setTime(courses.getTeacher());//5
                checkIssueBean.setName(courses.getName());//6
                checkIssueBeanList.add(checkIssueBean);
            }
        }

        return checkIssueBeanList;
    }

    @Override
    public List<Selection> findCourseStudent(String issue_id) {
        List<Selection> selectionList=studentImpl.selectionRepository.findByIssue_course(issue_id);
        return selectionList;
    }

    @Override
    public StudentCount getStudentCount(String student) {
        StudentCount studentCount=new StudentCount();
        Users users=studentImpl.usersRepository.findById(student).get();
        studentCount.setTime(users.getTime());//1
        List<Selection> selectionList=studentImpl.selectionRepository.findByStudent(student);
        studentCount.setSelectCourses(""+selectionList.size());//2
        List<Selection> selectionList1=studentImpl.selectionRepository.findByStudentAndDrop_course(student,true);
        studentCount.setDropCourses(selectionList1.size()+"");//3
        List<StudentAssignment> studentAssignmentList=studentImpl.studentAssignmentRepository.findByStudent(student);
        studentCount.setAssignment(studentAssignmentList.size()+"");
        return studentCount;
    }


}
