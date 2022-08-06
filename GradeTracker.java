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
                        System.out.println("Enter the student's studentId");
                        id = scanner.nextLine();
                        for (Student student : students) {
                            if (student.getStudentID().equals(id)) {
                                String studentName = student.getStudentName();
                                students.remove(student);
                                System.out.println("Student " + studentName + " has been deleted");
                            }
                        }
                        break;
                    case "3":
                    case "Calculate a student's GPA":
                        System.out.println("Enter the student's studentId");
                        id = scanner.nextLine();
                        for (Student student : students) {
                            if (student.getStudentID().equals(id)) {
                                System.out.println(student.getStudentName());
                            }
                        }
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
                        System.out.println("Sel 1");
                        break;
                    case "2":
                    case "Remove modules from a student":
                        System.out.println("Sel 2");
                        break;
                    case "3":
                    case "Calculate Marks":
                        System.out.println("Sel 3");
                        break;
                    case "4":
                    case "Calculate Grades":
                        System.out.println("Sel 4");
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
                        System.out.println("Sel 1");
                        break;
                    case "2":
                    case "Remove assessments in a module":
                        System.out.println("Sel 2");
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
}
