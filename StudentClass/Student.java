class Student {
    // Attributes
    private String name;
    private int age;
    private String studentId;

    // Constructor
    public Student(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStudentId() {
        return studentId;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Age must be positive.");
        }
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    // Display student details
    public void displayInfo() {
        System.out.println("Student Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Student ID: " + studentId);
    }

    // Main method to test the Student class
    public static void main(String[] args) {
        Student student1 = new Student("Sathya", 22, "20BCE1045");
        student1.displayInfo();

        // Modifying student details
        student1.setAge(21);
        student1.setName("Sathiya");

        System.out.println("\nUpdated Student Info:");
        student1.displayInfo();
    }
}
