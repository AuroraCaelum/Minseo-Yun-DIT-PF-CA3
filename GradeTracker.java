/*
# Env       -  JDK 1.8.0_331
# File      -  OrderingSystem.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        menuMain();
    }

    public static void menuMain(){
        Scanner scanner = new Scanner(System.in);
        String name, id;
        System.out.print("===== Select a menu =====\n1. Student Management\n2. Module Management\n3. Assessment Management\n0. Quit Program\n=========================\nSelect: ");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
            case "Student Management":
                System.out.print("==== Student Management ====\n1. Create New Students\n2. Delete Students\n3. Calculate a student's GPA\n0. Back to menu\n============================\nSelect: ");
                input = scanner.nextLine();
                switch (input) {
                    case "1":
                    case "Create New Students":
                        System.out.println("Enter the student's name");
                        name = scanner.nextLine();
                        System.out.println("Enter the student's studentId");
                        id = scanner.nextLine();
                        students.add(new Student(name, id));
                        menuMain();
                        break;
                    case "2":
                    case "Delete Students":
                        Student temp = findStudent();
                        if (temp != null) {
                            System.out.println("Student " + temp.getStudentName() + " has been deleted");
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
                    case "0":
                    case "Back to menu":
                        menuMain();
                        break;
                }
                break;
            case "2":
            case "Module Management":
                System.out.print("================== Module Management ==================\n1. Add modules to a student\n2. Remove modules from a student\n3. Calculate Marks\n4. Calculate Grades\n0. Back to menu\n=======================================================\nSelect: ");
                input = scanner.nextLine();
                switch (input) {
                    case "1":
                    case "Add modules to a student":
                        Student temp = findStudent();
                        if (temp != null) {
                            System.out.print("Enter the module's name: "); //TODO 중복 핸들링
                            String moduleName = scanner.nextLine();
                            System.out.print("Enter the module code: "); //TODO 중복 핸들링
                            String moduleCode = scanner.nextLine();
                            System.out.print("Enter the module's description: ");
                            String moduleDesc = scanner.nextLine();
                            System.out.print("Enter the module's credit units: ");
                            int moduleCredit = scanner.nextInt();
                            temp.addModule(new Module(moduleName, moduleCode, moduleDesc, moduleCredit));
                        }
                        menuMain();
                        break;
                    case "2":
                    case "Remove modules from a student":
                        moduleMenuHandler(1);
                        menuMain();
                        break;
                    case "3":
                    case "Calculate Marks":
                        moduleMenuHandler(2);
                        menuMain();
                        break;
                    case "4":
                    case "Calculate Grades":
                        moduleMenuHandler(3);
                        menuMain();
                        break;
                    case "0":
                    case "Back to menu":
                        menuMain();
                        break;
                }
                break;
            case "3":
            case "Assessment Management":
                System.out.print("==== Assessment Management ====\n1. Add assessments to a module\n2. Remove assessments in a module\n0. Back to menu\n============================\nSelect: ");
                input = scanner.nextLine();
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
                                System.out.print("Enter the assessment's description: ");
                                String assessmentDesc = scanner.nextLine();
                                System.out.print("Enter the assessment's total marks: ");
                                double assessmentTotalMarks = scanner.nextDouble();
                                System.out.print("Enter the assessment's marks: ");
                                double assessmentMarks = scanner.nextDouble();
                                while (assessmentMarks > assessmentTotalMarks) {
                                    System.out.println("Marks can't be greater than total marks");
                                    System.out.print("Enter the assessment's marks: ");
                                    assessmentMarks = scanner.nextDouble();
                                }
                                System.out.print("Enter the assessment's weightage: ");
                                double assessmentWeight = scanner.nextDouble();
                                tempModule.addAssessment(new Assessment(assessmentName, assessmentDesc, tempModule, assessmentMarks, assessmentTotalMarks, assessmentWeight));
                            }
                        }
                        /*System.out.print("Enter the module code: ");
                        String moduleCode = scanner.nextLine();
                        for (Module module : Student.allModules) {
                            if (module.getModuleCode().equals(moduleCode)) {
                                System.out.print("Enter the assessment name: ");
                                String asmtName = scanner.nextLine();
                                System.out.print("Enter the assessment description: ");
                                String asmtDesc = scanner.nextLine();
                                System.out.print("Enter the assessment marks: ");
                                double asmtMarks = scanner.nextDouble();
                                System.out.print("Enter the assessment total marks: ");
                                double asmtTotal = scanner.nextDouble();
                                System.out.print("Enter the assessment weightage: ");
                                double asmtWeight = scanner.nextDouble();
                            }
                        }*/
                        menuMain();
                        break;
                    case "2":
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
                    case "0":
                    case "Back to menu":
                        menuMain();
                        break;
                }
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

    public static Student findStudent() { //TODO 사용가능하면 변경 -> return null일때에 대한 핸들링이 가능해야 할 것.
        Scanner scanner = new Scanner(System.in);
        boolean exist = false;
        Student stdTemp = null;
        System.out.print("Enter the student's studentId: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equals(id)) {
                exist = true;
                stdTemp = student;
            }
        }
        if (!exist) {
            System.out.println("Can't find student ID '" + id + "'");
        }
        return stdTemp;
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
        /*boolean exist = false;
        System.out.print("Enter the student's studentId: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equals(id)) {
                exist = true;
                System.out.print("Enter the module's code: ");
                String moduleCode = scanner.nextLine();
                switch (type) {
                    case 1:
                        student.removeModule(moduleCode);
                        break;
                    case 2:
                        student.getModuleMarks(moduleCode);
                        break;
                    case 3:
                        student.getModuleGrades(moduleCode);
                        break;
                    default:
                        System.out.println("Unacceptable Type Code");
                        break;
                }
            }
        }
        if (!exist) {
            System.out.println("Can't find student ID '" + id + "'");
        }*/
    }
}
