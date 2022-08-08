/*
# Env       -  JDK 1.8.0_331
# File      -  Student.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;

public class Student {
    //YUN("Yun Minseo", "10240311");
    private String name; // name of the student
    private String studentID; // studentID
    private ArrayList<Module> modules; // list of modules the student is taking
    public static ArrayList<Module> allModules = new ArrayList<>();

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
        modules.add(module);
        boolean exist = false;
        for (Module temp : allModules) {
            if (temp.getModuleCode().equals(module.getModuleCode())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            allModules.add(module);
        }
    }
    public void removeModule(String moduleCode) {
        //modules.removeIf(module -> module.getModuleCode().equals(moduleCode));
        boolean exist = false;
        Module temp = null;
        for (Module module : modules) {
            if (module.getModuleCode().equals(moduleCode)) {
                exist = true;
                temp = module;
            }
        }
        if (!exist) {
            System.out.println("Can't find module code '" + moduleCode + "' in this student");
        } else {
            System.out.println("Module '" + temp.getName() + "' has successfully removed.");
            modules.remove(temp);
        }
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
        for (Module module : modules) {
            totalWGP += module.getWeightedGradePoints();
        }
        return totalWGP / getTotalCreditUnits();
    }

    public String toString() {
        String str = "";
        str += "Name: " + this.name + "\n";
        str += "StudentID: " + this.studentID + "\n";
        str += "Modules: " + this.modules + "\n";
        return str;
    }

    public void getModuleMarks(String moduleCode) {
        Module temp = findModule(moduleCode);
        if (temp != null) {
            System.out.println("Name: " + this.name);
            System.out.println("Module Name: " + temp.getName());
            System.out.println("Earned Total Marks: " + temp.getOverallMarks());
            System.out.println("Calculated Mark: " + temp.getCalculatedMarks());
        }
    }

    public void getModuleGrades(String moduleCode) {
        Module temp = findModule(moduleCode);
        if (temp != null) {
            System.out.println("Name: " + this.name);
            System.out.println("Module Name: " + temp.getName());
            System.out.println("Calculated Grade: " + temp.getOverallGrades() + " (" + Module.getGradePoint(temp.getOverallGrades()) + ")");
        }
    }

    public Module findModule(String moduleCode) {
        boolean exist = false;
        Module temp = null;
        for (Module module : modules) {
            if (module.getModuleCode().equals(moduleCode)) {
                exist = true;
                temp = module;
            }
        }
        if (!exist) {
            System.out.println("Can't find module code '" + moduleCode + "' in this student");
        }
        return temp;
    }
}
