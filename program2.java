// program2.java

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Student {
    String name;
    LocalDate dob;

    public Student(String name, String dobStr) {
        this.name = name;
        this.dob = parseDate(dobStr);
    }

    private LocalDate parseDate(String dobStr) {
        try {
            if (dobStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return LocalDate.parse(dobStr); // YYYY-MM-DD
            } else if (dobStr.matches("\\d{2}-\\d{2}-\\d{4}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.parse(dobStr, formatter); // DD-MM-YYYY
            } else {
                throw new IllegalArgumentException("Invalid date format.");
            }
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    public void displayStudentInfo() {
        System.out.println("Name: " + name);
        if (dob != null) {
            int age = Period.between(dob, LocalDate.now()).getYears();
            System.out.println("Age: " + age);
        } else {
            System.out.println("Invalid DOB");
        }
    }
}