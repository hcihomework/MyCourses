package com.bean;

public class TeacherCount {
    private String createCourseNum;
    private String createApprovalNum;
    private String issueCourseNum;
    private String issueApprovalNum;
    private String assignmentNum;
    private String time;

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreateApprovalNum() {
        return createApprovalNum;
    }

    public String getTime() {
        return time;
    }

    public String getAssignmentNum() {
        return assignmentNum;
    }

    public String getCreateCourseNum() {
        return createCourseNum;
    }

    public String getIssueApprovalNum() {
        return issueApprovalNum;
    }

    public String getIssueCourseNum() {
        return issueCourseNum;
    }

    public void setAssignmentNum(String assignmentNum) {
        this.assignmentNum = assignmentNum;
    }

    public void setCreateApprovalNum(String createApprovalNum) {
        this.createApprovalNum = createApprovalNum;
    }

    public void setCreateCourseNum(String createCourseNum) {
        this.createCourseNum = createCourseNum;
    }

    public void setIssueApprovalNum(String issueApprovalNum) {
        this.issueApprovalNum = issueApprovalNum;
    }

    public void setIssueCourseNum(String issueCourseNum) {
        this.issueCourseNum = issueCourseNum;
    }
}
