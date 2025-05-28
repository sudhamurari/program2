import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Student {
    private String name;
    private LocalDate dob;

    public Student(String name, String dobStr) {
        this.name = name;
        this.dob = parseDate(dobStr);
    }

    private LocalDate parseDate(String dateStr) {
        LocalDate date;
        try {
            if (dateStr.contains("-")) {
                if (dateStr.indexOf('-') == 4) {
                    date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } else {
                    date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }
            } else {
                throw new IllegalArgumentException("Unsupported date format");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        return date;
    }

    public void displayInfo() {
        LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();
        System.out.println("Student Name: " + name);
        System.out.println("Age: " + age);
    }
}

class StudentCourses {
    private Map<Integer, Map<String, Integer>> semesterCourses;

    public StudentCourses() {
        semesterCourses = new HashMap<>();
    }

    public void addCourse(int semester, String courseName, int marks) {
        semesterCourses.putIfAbsent(semester, new HashMap<>());
        semesterCourses.get(semester).put(courseName, marks);
    }

    public void displayCourses() {
        for (int semester : semesterCourses.keySet()) {
            System.out.println("Semester " + semester + ":");
            Map<String, Integer> courses = semesterCourses.get(semester);
            for (String course : courses.keySet()) {
                System.out.println("  Course: " + course + ", Marks: " + courses.get(course));
            }
        }
    }
}

public class program2 {
    public static void main(String[] args) {
        // Create and display student info
        Student student = new Student("Alice", "2004-09-15");
        student.displayInfo();

        // Create and display student course info
        StudentCourses sc = new StudentCourses();
        sc.addCourse(1, "Mathematics", 85);
        sc.addCourse(1, "Physics", 90);
        sc.addCourse(2, "Chemistry", 88);
        sc.displayCourses();
    }
}
