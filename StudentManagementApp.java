import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();//build object from studentService class

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll No: ");
                    String roll =sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Branch: ");
                    String branch = sc.nextLine();

                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    Student s = new Student(roll, name, branch, marks);
                    service.addStudent(s);
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Roll No to search: ");
                    String searchRoll = sc.nextLine();
                    Student found = service.searchStudent(searchRoll);
                    if (found != null) {
                        System.out.println("Roll\tName\tBranch\tMarks");
                        found.display();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    String deleteRoll = sc.nextLine();
                    if (service.deleteStudent(deleteRoll)) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

//  SERVICE CLASS

class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Roll\tName\tBranch\tMarks");
        for (Student s : students) {
            s.display();
        }
    }

    public Student searchStudent(String rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteStudent(String rollNo) {
        Student s = searchStudent(rollNo);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }
}

// MODEL CLASS
class Student {

    private String rollNo;
    private String name;
    private String branch;
    private double marks;

    public Student(String rollNo, String name, String branch, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.branch = branch;
        this.marks = marks;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void display() {
        System.out.println(rollNo + "\t" + name + "\t" + branch + "\t" + marks);
    }
}
