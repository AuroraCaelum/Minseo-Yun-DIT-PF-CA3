/*
# Env       -  JDK 1.8.0_331
# File      -  Module.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

import java.util.ArrayList;

public class Module {
    private String name;
    private String moduleCode;
    private String description;
    private int creditUnits;
    private ArrayList<Assessment> assessments;

    public Module(String name, String moduleCode, String description, int creditUnits) {
        this.name = name;
        this.moduleCode = moduleCode;
        this.description = description;
        this.creditUnits = creditUnits;
        this.assessments = new ArrayList<Assessment>();
    }

    public String getName() {
        return this.name;
    }

    public String getModuleCode() {
        return this.moduleCode;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCreditUnits() {
        return this.creditUnits;
    }

    public ArrayList<Assessment> getAssessments() {
        return this.assessments;
    }

    public double getOverallMarks() {
        double overall = 0;
        for (Assessment assessment : assessments) { //TODO assessment 가 없을때에 대한 핸들링
            overall += assessment.getWeightedMarks();
        }
        return overall;
    }

    public double getOverallTotalMarks() {
        double overall = 0;
        for (Assessment assessment : assessments) { //TODO assessment 가 없을때에 대한 핸들링
            overall += assessment.getWeightedTotalMarks();
        }
        return overall;
    }

    public double getCalculatedMarks() {
        return (getOverallMarks() / getOverallTotalMarks()) * 100;
    }

    public String getOverallGrades() {
        double score = getCalculatedMarks();
        if (score >= 90) {
            return "A+";
        } else if (score >= 80) {
            return "A";
        } else if (score >= 75) {
            return "B+";
        } else if (score >= 70) {
            return "B";
        } else if (score >= 65) {
            return "C+";
        } else if (score >= 60) {
            return "C";
        } else if (score >= 55) {
            return "D+";
        } else if (score >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static double getGradePoint(String grade) {
        switch (grade) {
            case "A+":
            case "A":
                return 4.0;
            case "B+":
                return 3.5;
            case "B":
                return 3.0;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            case "D+":
                return 1.5;
            case "D":
                return 1.0;
            case "F":
            case "ABS":
            case "T":
            default:
                return 0.0;
        }
    }

    public double getWeightedGradePoints() {
        double gp = Module.getGradePoint(getOverallGrades());
        return gp * creditUnits;
    }

    public void addAssessment(Assessment assessment) {
        this.assessments.add(assessment);
    }

    public void removeAssessment(String assessmentName) {
        boolean exist = false;
        Assessment temp = null;
        for (Assessment assessment : assessments) {
            if (assessment.getName().equalsIgnoreCase(assessmentName)) {
                exist = true;
                temp = assessment;
            }
        }
        if (!exist) {
            System.out.println("Can't find assessment name '" + assessmentName + "' in module '" + moduleCode + "'");
        } else {
            assessments.remove(temp);
            System.out.println("Assessment '" + assessmentName + "' has successfully removed");
        }
    }
}
