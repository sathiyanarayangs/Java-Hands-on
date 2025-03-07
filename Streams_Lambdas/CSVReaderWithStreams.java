import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Dept: " + department + ", Salary: Rs." + salary;
    }
}

public class CSVReaderWithStreams {
    public static void main(String[] args) {
        String fileName = "employees.csv";

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            // Read & process CSV (Skipping Header)
            List<Employee> employees = lines.skip(1)
                    .map(line -> line.split(","))
                    .map(data -> new Employee(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            Double.parseDouble(data[3])
                    ))
                    .collect(Collectors.toList());

            // 🔹 Print all employees
            System.out.println("All Employees:");
            employees.forEach(System.out::println);

            // 🔹 Filter: Employees with Salary > ₹60,000
            System.out.println("\nEmployees earning more than ₹60,000:");
            employees.stream()
                    .filter(emp -> emp.getSalary() > 60000)
                    .forEach(System.out::println);

            // 🔹 Sort: Employees by Name
            System.out.println("\nEmployees Sorted by Name:");
            employees.stream()
                    .sorted(Comparator.comparing(Employee::getName))
                    .forEach(System.out::println);

            // 🔹 Highest Paid Employee
            System.out.println("\nHighest Paid Employee:");
            employees.stream()
                    .max(Comparator.comparing(Employee::getSalary))
                    .ifPresent(System.out::println);

            // 🔹 Average Salary
            double avgSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0);
            System.out.println("\nAverage Salary: ₹" + avgSalary);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
