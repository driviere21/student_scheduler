package com.zybooks.studentscheduler;

public class Term {

    private int termId;
    private String termTitle, startDate, endDate;

    public Term() {

    }

    public Term( String termTitle, String startDate, String endDate){
        this.termId = termId;
        this.termTitle = termTitle;
        this.startDate = startDate;
        this.endDate = endDate;


    }

    @Override
    public String toString() {
        return "Term{" +
                "termId=" + termId +
                ", termTitle='" + termTitle + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
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
