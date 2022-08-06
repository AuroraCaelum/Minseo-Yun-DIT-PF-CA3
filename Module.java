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
    //TODO public으로 바꿔도 되는 애들은 변경
    private String name;
    private String moduleCode;
    private String description;
    private int creditUnits;
    private ArrayList<Assessment> assessments;

    public int getCreditUnits() {
        return this.creditUnits;
    }
    public double getOverallMarks() {
        double overall = 0;
        for (Assessment assessment : assessments) {
            overall += assessment.getMarks();
        }
        return overall;
    }

    public double getOverallTotalMarks() {
        double overall = 0;
        for (Assessment assessment : assessments) {
            overall += assessment.getTotalMarks();
        }
        return overall;
    }

    public String getOverallGrades() {
        double score = getOverallMarks() / assessments.size();
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
                return 0.0;
            default:
                return 0.0;
        }
    }

    public double getWeightedGradePoints() {
        double gp = Module.getGradePoint(getOverallGrades());
        return gp * creditUnits;
    }
}
