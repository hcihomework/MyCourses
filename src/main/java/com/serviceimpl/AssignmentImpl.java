package com.serviceimpl;

import com.entity.Assignment;
import com.entity.AssignmentWare;
import com.entity.StudentAssignment;
import com.repository.AssignmentRepository;
import com.repository.AssignmentWareRepository;
import com.repository.StudentAssignmentRepository;
import com.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private AssignmentWareRepository assignmentWareRepository;
    @Autowired
    private StudentAssignmentRepository studentAssignmentRepository;
    private static AssignmentImpl assignmentImpl;
    @PostConstruct
    public void init(){
        assignmentImpl=this;
        assignmentImpl.assignmentRepository=this.assignmentRepository;
        assignmentImpl.assignmentWareRepository=this.assignmentWareRepository;
        assignmentImpl.studentAssignmentRepository=this.studentAssignmentRepository;
   }
    @Override
    public boolean createAssignment(String name,String deadline, String course_id, String message, List<String> filePaths) {
        List<Assignment> assignmentList=assignmentImpl.assignmentRepository.findAll();
        Assignment assignment=new Assignment();
        if(assignmentList!=null){
            assignment.setId(assignmentList.size()+"");
        }else {
            assignment.setId("0");
        }
        assignment.setName(name);
        assignment.setCourses(course_id);
        assignment.setDeadline(deadline);
        assignment.setMessage(message);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=simpleDateFormat.format(new Date());
        assignment.setTime(date);
        assignmentImpl.assignmentRepository.save(assignment);
        if(filePaths!=null){
            for(int i=0;i<filePaths.size();i++){
                List<AssignmentWare> assignmentWareList=assignmentImpl.assignmentWareRepository.findAll();
                String wareId=0+"";
                if(assignmentWareList!=null){
                    wareId=assignmentWareList.size()+"";
                }
                AssignmentWare assignmentWare=new AssignmentWare();
                assignmentWare.setId(wareId);
                assignmentWare.setAssignment_id(assignment.getId());
                assignmentWare.setPath(filePaths.get(i));
                assignmentImpl.assignmentWareRepository.save(assignmentWare);
            }
        }
        return true;
    }

    @Override
    public List<Assignment> findAssignmentList(String course_id) {
        return assignmentImpl.assignmentRepository.findByCourses(course_id);
    }

    @Override
    public List<String> findAssignmentFileList(String assignment_id) {
        List<String> stringList=new ArrayList<>();
        List<AssignmentWare> assignmentWareList=assignmentImpl.assignmentWareRepository.findByAssignment_id(assignment_id);
        if(assignmentWareList!=null){
            for(AssignmentWare assignmentWare:assignmentWareList){
                stringList.add(assignmentWare.getPath());
            }
        }
        return stringList;
    }

    @Override
    public List<StudentAssignment> getStudentAssignmentList(String assignment_id) {
        return assignmentImpl.studentAssignmentRepository.findByAssignment(assignment_id);
    }
    @Override
    public boolean submitAssignment(String assignmentID, String student, String path) {
        StudentAssignment studentAssignment=new StudentAssignment();
        List<StudentAssignment> studentAssignmentList=assignmentImpl.studentAssignmentRepository.findAll();
        String workId="0";
        if(studentAssignmentList!=null)
            workId=studentAssignmentList.size()+"";
        studentAssignment.setId(workId);//1
        studentAssignment.setPath(path);//2
        studentAssignment.setStudent(student);//3
        studentAssignment.setAssignment(assignmentID);//4
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date=simpleDateFormat.format(new Date());
        studentAssignment.setTime(date);//5
        studentAssignment.setScore("null");//6
        assignmentImpl.studentAssignmentRepository.save(studentAssignment);
        return true;
    }

    @Override
    public StudentAssignment getStudentAssignment(String assignment_id, String student) {
        Optional<StudentAssignment> optionalStudentAssignment=assignmentImpl.studentAssignmentRepository.findByStudentAndAssignment(student,assignment_id);
        if(optionalStudentAssignment.isPresent()){
            return optionalStudentAssignment.get();
        }
        return new StudentAssignment();
    }

    @Override
    public boolean giveStudentScore(String assignment,String student, String score) {
        Optional<StudentAssignment> optionalStudentAssignment=assignmentImpl.studentAssignmentRepository.findByStudentAndAssignment(student,assignment);
        StudentAssignment studentAssignment=optionalStudentAssignment.get();
        studentAssignment.setScore(score);
        assignmentImpl.studentAssignmentRepository.save(studentAssignment);
        return true;
    }
}
