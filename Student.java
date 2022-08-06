/*
# Env       -  JDK 1.8.0_331
# File      -  Student.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

import java.util.ArrayList;

public class Student {
    //TODO public으로 바꿔도 되는 애들은 변경
    private String name; // name of the student
    private String studentID; // studentID
    private ArrayList<Module> modules; // list of modules the student is taking

    public Student() {
        this.name = "Empty Name";
        this.studentID = "Empty StudentID";
        this.modules = new ArrayList<Module>();
    }

    public Student(String name) {
        this.name = name;
        this.studentID = "Empty StudentID";
        this.modules = new ArrayList<Module>();
    }

    public Student(String name, String id) {
        this.name = name;
        this.studentID = id;
        this.modules = new ArrayList<>();
    }

    public String getStudentName() {
        return this.name;
    }
    public String getStudentID() {
        return this.studentID;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public int getTotalCreditUnits() {
        int total = 0;
        for (Module module : modules) {
            total += module.getCreditUnits();
        }
        return total;
    }

    public double getGPA() {
        double totalWGP = 0;
        int totalUnit = 0;
        for (Module module : modules) {
            totalWGP += module.getWeightedGradePoints();
            totalUnit += module.getCreditUnits();
        }
        return totalWGP / totalUnit;
    }

    public String toString() {
        String str = "";
        str += "Name: " + this.name + "\n";
        str += "StudentID: " + this.studentID + "\n";
        str += "Modules: " + this.modules + "\n";
        return str;
    }
}
