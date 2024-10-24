package com.zybooks.studentscheduler;

public class Courses {
    private int courseId, courseTermId;
    private String courseTitle, startDate, endDate, status, instructorName, instructorTel, instructorEmail, notes;


    public Courses() {
    }

    public Courses(String courseTitle, String startDate, String endDate, String status, String instructorName, String instructorTel, String instructorEmail, String notes) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorTel = instructorTel;
        this.instructorEmail = instructorEmail;
        this.notes = notes;
    }

    public Courses(String courseTitle) {
        this.courseTitle = courseTitle;
    }


    @Override
    public String toString() {
        return "Courses{" +
                "courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorTel='" + instructorTel + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public int getCourseTermId() {
        return courseTermId;
    }

    public void setCourseTermId(int courseTermId) {
        this.courseTermId = courseTermId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorTel() {
        return instructorTel;
    }

    public void setInstructorTel(String instructorTel) {
        this.instructorTel = instructorTel;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public class courseList {
        private  String courseListId;

        public courseList(String courseListId) {
            this.courseListId = courseListId;
        }

        public String getCourseListId() {
            return courseListId;
        }

        public void setCourseListId(String courseListId) {
            this.courseListId = courseListId;
        }
    }
}


