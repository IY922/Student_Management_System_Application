package com.example.smsa;


public class ExamDetails {
    private String examName;
    private String sem;
    private String branch;
    private String subject1Name;
    private String subject1Timetable;
    private String subject2Name;
    private String subject2Timetable;
    private String subject3Name;
    private String subject3Timetable;
    private String subject4Name;
    private String subject4Timetable;

    public ExamDetails(String examName, String sem, String branch, String subject1Name, String subject1Timetable,
                       String subject2Name, String subject2Timetable, String subject3Name, String subject3Timetable,
                       String subject4Name, String subject4Timetable) {
        this.examName = examName;
        this.sem = sem;
        this.branch = branch;
        this.subject1Name = subject1Name;
        this.subject1Timetable = subject1Timetable;
        this.subject2Name = subject2Name;
        this.subject2Timetable = subject2Timetable;
        this.subject3Name = subject3Name;
        this.subject3Timetable = subject3Timetable;
        this.subject4Name = subject4Name;
        this.subject4Timetable = subject4Timetable;
    }

    public String getExamName() {
        return examName;
    }

    public String getSem() {
        return sem;
    }

    public String getBranch() {
        return branch;
    }

    public String getSubject1Name() {
        return subject1Name;
    }

    public String getSubject1Timetable() {
        return subject1Timetable;
    }

    public String getSubject2Name() {
        return subject2Name;
    }

    public String getSubject2Timetable() {
        return subject2Timetable;
    }

    public String getSubject3Name() {
        return subject3Name;
    }

    public String getSubject3Timetable() {
        return subject3Timetable;
    }

    public String getSubject4Name() {
        return subject4Name;
    }

    public String getSubject4Timetable() {
        return subject4Timetable;
    }
}