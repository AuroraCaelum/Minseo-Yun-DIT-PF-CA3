/*
# Env       -  JDK 1.8.0_331
# File      -  Assessment.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

public class Assessment {
    //TODO public으로 바꿔도 되는 애들은 변경
    private String name;
    private String description;
    private Module module;
    private double marks;
    private double totalMarks;
    private double weightage;

    public Assessment(String name, String description, Module module, double marks, double totalMarks, double weightage) {
        this.name = name;
        this.description = description;
        this.module = module;
        this.marks = marks;
        this.totalMarks = totalMarks;
        this.weightage = weightage;
    }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public Module getModule() { return this.module; }
    public double getMarks() { return this.marks; }
    public double getTotalMarks() { return this.totalMarks; }
    public double getWeightedMarks() {
        return (marks / totalMarks) * weightage;
    }
}
