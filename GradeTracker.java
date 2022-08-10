/*
# Env       -  JDK 1.8.0_331
# File      -  OrderingSystem.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        menuMain();
    }

    public static void menuMain(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n===== Select a menu =====\n1. Student Management\n2. Module Management\n3. Assessment Management\n4. Print all data\n0. Quit Program\n--------------------\na. Restore Pre-initialized student values\nb. Export/Save data to XML\nc. Import/Restore data from XML\n=========================\nSelect: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
            case "Student Management":
                studentMenu();
                break;
            case "2":
            case "Module Management":
                moduleMenu();
                break;
            case "3":
            case "Assessment Management":
                assessmentMenu();
                break;
            case "4":
            case "Print all data":
                printAllData();
                menuMain();
                break;
            case "a":
            case "A":
                initStudent();
                menuMain();
                break;
            case "b":
            case "B":
            case "Export/Save data to XML":
                System.out.print("This will replace the contents of [data.xml] to the current program database.\nAre you sure you want to continue? (y/n): ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("y")) {
                    try {
                        FileIO.exportData();
                    } catch (ParserConfigurationException | IOException | TransformerException e) {     // Export exception handling
                        System.out.println("Some error occurred while exporting data.");
                    }
                }
                menuMain();
                break;
            case "c":
            case "C":
            case "Import/Restore data from XML":
                System.out.print("This will remove all existing data and replace to imported data.\nAre you sure you want to continue? (y/n): ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("y")) {
                    FileIO.importData();                    // Import exception handling in FileIO class
                }
                menuMain();
                break;
            case "0":
            case "Quit":
            case "exit":
                System.out.println("Program Ended");
                break;
            default:
                System.out.println("Invalid input");
                menuMain();
        }
    }

    static void studentMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n==== Student Management ====\n1. Create New Students\n2. Delete Students\n3. Calculate a student's GPA\n4. Print all students\n0. Back to menu\n============================\nSelect: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
            case "Create New Students":
                System.out.print("Enter the student's name: ");
                String name = scanner.nextLine();
                System.out.print("Enter the student's studentId: ");
                String id = scanner.nextLine();
                validateDuplication(id);              // Avoiding duplication
                students.add(new Student(name, id));
                System.out.println("Student '" + name + "(" + id + ")' has been created.");
                menuMain();
                break;
            case "2":
            case "Delete Students":
                Student temp = findStudent();
                if (temp != null) {
                    System.out.println("Student " + temp.getStudentName() + "(" + temp.getStudentID() + ")' has been deleted");
                    students.remove(temp);
                }
                menuMain();
                break;
            case "3":
            case "Calculate a student's GPA":
                temp = findStudent();
                if (temp != null) {
                    System.out.println("Name: " + temp.getStudentName());
                    System.out.println("GPA: " + temp.getGPA());
                }
                menuMain();
                break;
            case "4":
            case "Print all students":
                for (Student student : students) {
                    System.out.println(student.getStudentID() + "\t" + student.getStudentName());
                }
                menuMain();
                break;
            case "0":
            case "Back to menu":
                menuMain();
                break;
            default:
                System.out.println("Invalid input");
                studentMenu();
        }
    }

    static void moduleMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n================== Module Management ==================\n1. Add modules to a student\n2. Add modules from the template\n3. Remove modules from a student\n4. Calculate Marks\n5. Calculate Grades\n6. Print all modules assigned to a student\n0. Back to menu\n=======================================================\nSelect: ");
        String input = scanner.nextLine();
        Student temp;
        switch (input) {
            case "1":
            case "Add modules to a student":
                temp = findStudent();
                if (temp != null) {
                    System.out.print("Enter the module's name: ");
                    String moduleName = scanner.nextLine();
                    System.out.print("Enter the module code: ");
                    String moduleCode = scanner.nextLine();
                    validateDuplication(temp, moduleCode);              // Avoiding duplication
                    System.out.print("Enter the module's description: ");
                    String moduleDesc = scanner.nextLine();
                    System.out.print("Enter the module's credit units: ");
                    int moduleCredit = scanner.nextInt();
                    temp.addModule(new Module(moduleName, moduleCode, moduleDesc, moduleCredit));
                    System.out.println("Module '" + moduleName + "(" + moduleCode + ")' has been added to " + temp.getStudentName() + "(" + temp.getStudentID() + ")");
                }
                menuMain();
                break;
            case "2":
            case "Add modules from the template":
                temp = findStudent();
                if (temp != null) {
                    for (ModuleEnum module : ModuleEnum.values()) {
                        System.out.println("Module Code: " + module.getModuleCode() + "\tModule Name: " + module.getName());
                    }
                    System.out.print("Enter the module's code: ");
                    String moduleCode = scanner.nextLine();
                    validateDuplication(temp, moduleCode);              // Avoiding duplication
                    boolean value = false;
                    for (ModuleEnum module : ModuleEnum.values()) {
                        if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                            value = true;
                            temp.addModule(new Module(module.getName(), module.getModuleCode(), module.getDescription(), module.getCreditUnits()));
                            System.out.println("Module '" + module.getName() + "(" + module.getModuleCode() + ")' has been added to " + temp.getStudentName() + "(" + temp.getStudentID() + ")");
                        }
                    }
                    if (!value) {
                        System.out.println("Invalid module code. Return to module menu.");
                        moduleMenu();
                    }
                }
                menuMain();
                break;
            case "3":
            case "Remove modules from a student":
                moduleMenuHandler(1);
                menuMain();
                break;
            case "4":
            case "Calculate Marks":
                moduleMenuHandler(2);
                menuMain();
                break;
            case "5":
            case "Calculate Grades":
                moduleMenuHandler(3);
                menuMain();
                break;
            case "6":
            case "Print all modules assigned to a student":
                temp = findStudent();
                if (temp != null) {
                    for (Module module : temp.getModules()) {
                        System.out.println(module.getModuleCode() + "\t" + module.getName());
                    }
                }
                menuMain();
                break;
            case "0":
            case "Back to menu":
                menuMain();
                break;
            default:
                System.out.println("Invalid input");
                moduleMenu();
        }
    }

    public static void moduleMenuHandler(int type) {
        Student temp = findStudent();
        if (temp != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the module's code: ");
            String moduleCode = scanner.nextLine();
            switch (type) {
                case 1:
                    temp.removeModule(moduleCode);
                    break;
                case 2:
                    temp.getModuleMarks(moduleCode);
                    break;
                case 3:
                    temp.getModuleGrades(moduleCode);
                    break;
                default:
                    System.out.println("Unacceptable Type Code");
                    break;
            }
        }
    }

    static void assessmentMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n==== Assessment Management ====\n1. Add assessments to a module\n2. Add assessments from template\n3. Remove assessments in a module\n4. Print all assessments assigned to a student\n0. Back to menu\n============================\nSelect: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
            case "Add assessments to a module":
                Student tempStudent = findStudent();
                if (tempStudent != null) {
                    System.out.print("Enter the module code: ");
                    String moduleCode = scanner.nextLine();
                    Module tempModule = tempStudent.findModule(moduleCode);
                    if (tempModule != null) {
                        System.out.print("Enter the assessment's name: ");
                        String assessmentName = scanner.nextLine();
                        validateDuplication(tempModule, assessmentName);                     // Avoiding duplication
                        System.out.print("Enter the assessment's description: ");
                        String assessmentDesc = scanner.nextLine();
                        System.out.print("Enter the assessment's total marks: ");
                        double assessmentTotalMarks = scanner.nextDouble();
                        System.out.print("Enter the assessment's marks: ");
                        double assessmentMarks = scanner.nextDouble();
                        while (assessmentMarks > assessmentTotalMarks) {                    // Avoid invalid marks
                            System.out.println("Marks can't be greater than total marks");
                            System.out.print("Enter the assessment's marks: ");
                            assessmentMarks = scanner.nextDouble();
                        }
                        System.out.print("Enter the assessment's weightage: ");
                        double assessmentWeight = scanner.nextDouble();
                        tempModule.addAssessment(new Assessment(assessmentName, assessmentDesc, tempModule, assessmentMarks, assessmentTotalMarks, assessmentWeight));
                        System.out.println("Assessment '" + assessmentName + "' has been added to " + tempModule.getModuleCode() + "(" + tempModule.getName() + ")");
                    }
                }
                menuMain();
                break;
            case "2":
            case "Add assessments from template":
                tempStudent = findStudent();
                if (tempStudent != null) {
                    System.out.print("Enter the module code: ");
                    String moduleCode = scanner.nextLine();
                    Module tempModule = tempStudent.findModule(moduleCode);
                    if (tempModule != null) {
                        for (AssessmentEnum assessment : AssessmentEnum.values()) {
                            System.out.println("Assessment Name: " + assessment.getName() + "\tAssessment Total Marks: " + assessment.getTotalMarks() + "\tAssessment Weightage: " + assessment.getWeightage() + "%");
                        }
                        System.out.print("Enter the assessment's name: ");
                        String assessmentName = scanner.nextLine();
                        validateDuplication(tempModule, assessmentName);                     // Avoiding duplication
                        boolean value = false;
                        for (AssessmentEnum assessment : AssessmentEnum.values()) {
                            if (assessment.getName().equalsIgnoreCase(assessmentName)) {
                                value = true;
                                System.out.print("Enter the assessment's mark: ");
                                double marks = scanner.nextDouble();
                                while (marks > assessment.getTotalMarks()) {                    // Avoid invalid marks
                                    System.out.println("Marks can't be greater than total marks");
                                    System.out.print("Enter the assessment's marks: ");
                                    marks = scanner.nextDouble();
                                }
                                tempModule.addAssessment(new Assessment(assessment.getName(), assessment.getDescription(), tempModule, marks, assessment.getTotalMarks(), assessment.getWeightage()));
                                System.out.println("Assessment '" + assessment.getName() + "' has been added to " + tempModule.getModuleCode() + "(" + tempModule.getName() + ")");
                            }
                        }
                        if (!value) {
                            System.out.println("Invalid assessment name. Return to assessment menu.");
                            assessmentMenu();
                        }
                    }
                }
                menuMain();
                break;
            case "3":
            case "Remove assessments in a module":
                tempStudent = findStudent();
                if (tempStudent != null) {
                    System.out.print("Enter the module code: ");
                    String moduleCode = scanner.nextLine();
                    Module tempModule = tempStudent.findModule(moduleCode);
                    if (tempModule != null) {
                        System.out.print("Enter the assessment's name: ");
                        String assessmentName = scanner.nextLine();
                        tempModule.removeAssessment(assessmentName);
                    }
                }
                menuMain();
                break;
            case "4":
            case "Print all assessments assigned to a student":
                tempStudent = findStudent();
                if (tempStudent != null) {
                    for (Module module : tempStudent.getModules()) {
                        System.out.println(module.getModuleCode() + "\t" + module.getName());
                        for (Assessment assessment : module.getAssessments()) {
                            System.out.println("\t" + assessment.getName() + "\tMarks: " + assessment.getMarks() + "\tTotal Marks: " + assessment.getTotalMarks() + "\tWeightage: " + assessment.getWeightage() + "%");
                        }
                    }
                }
                menuMain();
                break;
            case "0":
            case "Back to menu":
                menuMain();
                break;
            default:
                System.out.println("Invalid input");
                assessmentMenu();
        }
    }

    static void printAllData() {
        for (Student student : students) {
            System.out.println(student.getStudentID() + "\t" + student.getStudentName());
            for (Module module : student.getModules()) {
                System.out.println("\t" + module.getModuleCode() + "\t" + module.getName());
                for (Assessment assessment : module.getAssessments()) {
                    System.out.println("\t\t" + assessment.getName() + "\tMarks: " + assessment.getMarks() + "\tTotal Marks: " + assessment.getTotalMarks() + "\tWeightage: " + assessment.getWeightage() + "%");
                }
            }
        }
    }

    static void initStudent(){
        for (StudentEnum studentEnum : StudentEnum.values()) {
            System.out.println(studentEnum.getName() + "\t" + studentEnum.getId());
            Student student = new Student(studentEnum.getName(), studentEnum.getId());
            students.add(student);
        }
    }

    public static Student findStudent() {
        Scanner scanner = new Scanner(System.in);
        boolean exist = false;
        Student stdTemp = null;
        System.out.print("Enter the student's studentId: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(id)) {
                exist = true;
                stdTemp = student;
            }
        }
        if (!exist) {
            System.out.println("Can't find student ID '" + id + "'. Back to main menu.");
        }
        return stdTemp;
    }

    static void validateDuplication(String studentId) {
        for (Student student : students) {              // Avoiding duplication
            if (student.getStudentID().equalsIgnoreCase(studentId)) {
                System.out.println("Student ID already exists");
                studentMenu();
            }
        }
    }

    static void validateDuplication(Student temp, String moduleCode) {
        for (Module module : temp.getModules()) {              // Avoiding duplication
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                System.out.println("Module Code '" + module.getModuleCode() + "' is already assigned to this student.");
                moduleMenu();
            }
        }
    }

    static void validateDuplication(Module temp, String assessmentName){
        for (Assessment assessment : temp.getAssessments()) {              // Avoiding duplication
            if (assessment.getName().equalsIgnoreCase(assessmentName)) {
                System.out.println("Assessment Name '" + assessment.getName() + "' is already assigned to this module.");
                assessmentMenu();
            }
        }
    }

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
}
