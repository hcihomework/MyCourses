package com.serviceimpl;

import com.bean.DirectorCount;
import com.bean.Person;
import com.bean.TeacherCount;
import com.entity.*;
import com.repository.*;
import com.service.UserIdentityService;
import com.tool.MD5Password;
import com.tool.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserIdentityImpl implements UserIdentityService {
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private IssueCoursesRepository issueCoursesRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    private static UserIdentityImpl userIdentity;
    private int codeLength=6;
    @PostConstruct
    public void init(){
        userIdentity=this;
        userIdentity.passwordRepository=this.passwordRepository;
        userIdentity.usersRepository=this.usersRepository;
        userIdentity.coursesRepository=this.coursesRepository;
        userIdentity.issueCoursesRepository=this.issueCoursesRepository;
        userIdentity.assignmentRepository=this.assignmentRepository;
    }

    @Override
    public String sendCode(String email) {
        String code="";
        for(int i=0;i<codeLength;i++){
            code=code+""+(int)(Math.random()*10);
        }
        Mail mail=new Mail();
        String message="您的MyCourses验证码为："+code+"；请勿转发他人。";
        try {
            mail.sendMail(email,"MyCourses验证",message);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return code;
    }

    @Override
    public String registerUser(Person person) {
        Optional<Users> optional=userIdentity.usersRepository.findById(person.getEmail());
        Users userReturn=optional.isPresent()?optional.get():null;
        //System.out.println(userReturn+" sss");
        if(userReturn!=null&&userReturn.isUsing()==true){
            return "该邮箱已被注册！";
        }else{
            Users users=new Users();
            users.setEmail(person.getEmail());
            users.setName(person.getName());
            users.setNumber(person.getId());
            users.setIdentity(person.getIdentity());
            users.setUsing(true);
            //
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH");
            String date=simpleDateFormat.format(new Date());
            System.out.println(date);
            //
            users.setTime(date);
            try {
                if(userReturn!=null&&userReturn.isUsing()==false){
                    userIdentity.usersRepository.deleteById(person.getEmail());
                }
                userIdentity.usersRepository.save(users);
            }catch (Exception e){
                e.printStackTrace();
                return "注册失败！";
            }
            //
            MD5Password md5Password=new MD5Password();
            Password password=new Password(person.getEmail(),md5Password.getMD5(person.getPassword()));
            try {
                if(userReturn!=null&&userReturn.isUsing()==false){
                    userIdentity.passwordRepository.deleteById(person.getEmail());
                }
                userIdentity.passwordRepository.save(password);
            }catch (Exception e){
                e.printStackTrace();
                return "注册失败！";
            }
            return "success";
        }




    }

    @Override
    public String login(String email, String password) {
        Optional<Users> optional=userIdentity.usersRepository.findById(email);
        System.out.println(email);
        Users users=optional.isPresent()?optional.get():null;
        if(users==null){
            return "fail";
        }else{
            if(users.isUsing()==false){
                return "fail";
            }else {
                MD5Password md5Password=new MD5Password();
                System.out.println(md5Password.getMD5(password)+"   "+userIdentity.passwordRepository.findById(email).get().getPassword());
                if(!userIdentity.passwordRepository.findById(email).get().getPassword().equals(md5Password.getMD5(password))){
                    return "fail";
                }else {
                    return users.getIdentity();
                }
            }
        }
    }

    @Override
    public boolean modifyMessage(String user, String name, String id) {
        Users users=userIdentity.usersRepository.findById(user).get();
        users.setName(name);
        users.setNumber(id);
        userIdentity.usersRepository.save(users);
        return true;
    }

    @Override
    public boolean modifyPassword(String user, String password) {
        MD5Password md5Password=new MD5Password();
        String savePassword=md5Password.getMD5(password);
        Password passwordClass=userIdentity.passwordRepository.findById(user).get();
        passwordClass.setPassword(savePassword);
        userIdentity.passwordRepository.save(passwordClass);
        return true;
    }

    @Override
    public boolean cancel(String user) {
        Users users=userIdentity.usersRepository.findById(user).get();
        users.setUsing(false);
        userIdentity.usersRepository.save(users);
        return true;
    }

    @Override
    public Users findUserMessage(String user) {
        return userIdentity.usersRepository.findById(user).get();
    }

    @Override
    public TeacherCount getTeacherCount(String teacher) {
        TeacherCount teacherCount=new TeacherCount();
        Users users=userIdentity.usersRepository.findById(teacher).get();
        teacherCount.setTime(users.getTime());//1
        List<Courses> coursesList=userIdentity.coursesRepository.findByTeacherAndApproval(teacher,true);
        teacherCount.setCreateApprovalNum(coursesList.size()+"");//2
        List<Courses> coursesList1=userIdentity.coursesRepository.findByTeacher(teacher);
        teacherCount.setCreateCourseNum(coursesList1.size()+"");//3
        List<IssueCourses> issueCoursesList=userIdentity.issueCoursesRepository.findByTeacher(teacher);
        teacherCount.setIssueCourseNum(issueCoursesList.size()+"");//4
        List<IssueCourses> issueCoursesList1=userIdentity.issueCoursesRepository.findByTeacherAndApproval(teacher,true);
        teacherCount.setIssueApprovalNum(issueCoursesList1.size()+"");//5
        List<Assignment> assignmentList=userIdentity.assignmentRepository.findByTeacher(teacher);
        teacherCount.setAssignmentNum(assignmentList.size()+"");//6
        return teacherCount;
    }
    @Override
    public DirectorCount getDirectorCount(){
        DirectorCount directorCount=new DirectorCount();
        List<Users> usersList=userIdentity.usersRepository.findByIdentity("teacher");
        directorCount.setTeacher(usersList.size()+"");
        List<Users> usersList1=userIdentity.usersRepository.findByIdentity("student");
        directorCount.setStudent(usersList1.size()+"");
        directorCount.setNumber((usersList.size()+usersList1.size())+"");
        return  directorCount;
    }
}
