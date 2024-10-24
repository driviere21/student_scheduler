package com.zybooks.studentscheduler;

public class Assessments {

    private int assessmentId;
    private String assessmentTitle, startDate, endDate, assessmentType;

    public Assessments() {
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Assessments(String assessmentTitle, String startDate, String endDate, String assessmentType) {
        this.assessmentTitle = assessmentTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assessmentType = assessmentType;
    }

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentTitle='" + assessmentTitle + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
