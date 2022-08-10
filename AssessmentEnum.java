/*
# Env       -  JDK 1.8.0_331
# File      -  AssessmentEnum.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

public enum AssessmentEnum {
    // Pre-defined Assessment Values
    CA1("CA1", "Continuous Assessment 1 - Quiz", 100, 10),
    CA2("CA2", "Common Test", 100, 40),
    CA3("CA3", "Individual Assignments", 100, 40),
    CA4("CA4", "Continuous Assessment 4 - Quiz", 100, 10);

    private final String name;
    private final String description;
    private final double totalMarks;
    private final double weightage;
    AssessmentEnum(String name, String description, double totalMarks, double weightage) {
        this.name = name;
        this.description = description;
        this.totalMarks = totalMarks;
        this.weightage = weightage;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getTotalMarks() {
        return this.totalMarks;
    }

    public double getWeightage() {
        return this.weightage;
    }
}
