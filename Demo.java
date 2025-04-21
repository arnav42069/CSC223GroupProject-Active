import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        ArrayBasedList<Student> studentList = new ArrayBasedList<>();
        //ArrayBasedList<Faculty> facultyList = new ArrayBasedList<>();  // No longer needed
        int choice = 0;

        do {
            System.out.println("Welcome to the Class Registration System!");
            System.out.println("Please select your user type:");
            System.out.println("1. Student");
            System.out.println("2. Faculty");
            System.out.println("3. Administrator");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    handleStudentLogin(sc, studentList);
                    break;
                case 2:
                    handleFacultyLogin(sc, admin.getfacultyStack());  // Changed to admin.getFacultyList()
                    break;
                case 3:
                    handleAdminLogin(sc, admin, studentList); // Removed facultyList
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 4);
        sc.close();
    }

    private static void handleStudentLogin(Scanner sc, ArrayBasedList<Student> studentList) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your birthdate (YYYY-MM-DD): ");
        String birthdate = sc.nextLine();

        Student student = findStudent(studentList, name, birthdate);

        if (student != null) {
            System.out.println("Student already exists. Please log in.");
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            if (student.getCredentials().checkLogin(username, password)) {
                studentMenu(sc, student);
            } else {
                System.out.println("Invalid credentials.");
            }
        } else {
            student = new Student(name, birthdate);
            System.out.print("Enter a username: ");
            String username = sc.nextLine();
            System.out.print("Enter a password: ");
            String password = sc.nextLine();
            Credentials credentials = new Credentials(username, password);
            student.setCredentials(credentials);
            student.setStudentId(generateStudentId());
            studentList.add(student);
            System.out.println("Student registered successfully with ID: " + student.getStudentId());
            studentMenu(sc, student);
        }
    }

    private static Student findStudent(ArrayBasedList<Student> studentList, String name, String birthdate) {
        for (int i = 0; i < studentList.getSize(); i++) {
            Student s = studentList.get(i);
            if (s.getName().equals(name) && s.getBirthdate().equals(birthdate)) {
                return s;
            }
        }
        return null;
    }

    private static String generateStudentId() {
        return "STU" + (int) (Math.random() * 10000);
    }

    private static void handleFacultyLogin(Scanner sc, ArrayBasedStack facStack) {  // Changed parameter
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Faculty faculty = findFaculty(facStack, name);

        if (faculty != null) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            if (faculty.getCredentials().checkLogin(username, password)) {
                facultyMenu(sc, faculty);
            } else {
                System.out.println("Invalid credentials.");
            }
        } else {
            System.out.println("Faculty not found. Please contact the administrator.");
        }
    }

    private static Faculty findFaculty(ArrayBasedStack<Faculty> facStack, String name) {  // Changed parameter type
        for (int i = 0; i < facStack.getSize(); i++) {  // Changed getSize() to size()
            Faculty f = facStack.getStack()[i];
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    private static void handleAdminLogin(Scanner sc, Admin admin, ArrayBasedList<Student> studentList) {  // Removed facultyList
        if (admin.checkCredentials()) {
            adminMenu(sc, admin, studentList); // Removed facultyList
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void studentMenu(Scanner sc, Student student) {
        int choice;
        do {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Enrolled Classes");
            System.out.println("2. Enroll in a Class");
            System.out.println("3. Drop a Class");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    student.viewEnrolledClasses();
                    break;
                case 2:
                    enrollInClass(sc, student, null); // Need classList
                    break;
                case 3:
                    dropClass(sc, student);
                    break;
                case 4:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void facultyMenu(Scanner sc, Faculty faculty) {
        int choice;
        do {
            System.out.println("\nFaculty Menu:");
            System.out.println("1. View Assigned Classes");
            System.out.println("2. View Students in a Class");
            System.out.println("3. View Waitlist for a Class");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    faculty.viewAssignedClasses();
                    break;
                case 2:
                    viewStudentsInClass(sc, faculty);
                    break;
                case 3:
                    viewWaitlistForClass(sc, faculty);
                    break;
                case 4:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void adminMenu(Scanner sc, Admin admin, ArrayBasedList<Student> studentList) {  // Removed facultyList
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a Course");
            System.out.println("2. Remove a Course");
            System.out.println("3. Schedule a Class");
            System.out.println("4. Cancel a Class");
            System.out.println("5. Assign Faculty to Class");
            System.out.println("6. Hire Faculty");
            System.out.println("7. Fire Faculty");
            System.out.println("8. View All Students");
            System.out.println("9. View All Faculty");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addCourse(sc, admin);
                    break;
                case 2:
                    removeCourse(sc, admin);
                    break;
                case 3:
                    scheduleClass(sc, admin);
                    break;
                case 4:
                    cancelClass(sc, admin);
                    break;
                case 5:
                    assignFacultyToClass(sc, admin, admin.getfacultyStack());  // Changed facultyList
                    break;
                case 6:
                    hireFaculty(sc, admin);  // Removed facultyList
                    break;
                case 7:
                    fireFaculty(sc, admin);  // Removed facultyList
                    break;
                case 8:
                    viewAllStudents(studentList);
                    break;
                case 9:
                    viewAllFaculty(admin);  // Changed facultyList to admin
                    break;
                case 10:
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 10);
    }

    //  --- Student Menu Actions ---
    private static void enrollInClass(Scanner sc, Student student, ArrayBasedList<Class> classList) {
        System.out.println("\n--- Enroll in Class ---");
        if (classList.getSize() == 0) {
            System.out.println("No classes are available to enroll in.");
            return;
        }
        System.out.println("Available Classes:");
        for (int i = 0; i < classList.getSize(); i++) {
            Class c = classList.get(i);
            System.out.println(i + 1 + ". " + c.getCourse().getCourseAbbreviation() + " " +
                    c.getCourse().getCourseNumber() + " - Section " + c.getSection() +
                    " (" + c.getTime() + ")");
        }
        System.out.print("Enter the number of the class to enroll in (or 0 to cancel): ");
        int classChoice = sc.nextInt();
        sc.nextLine();  // Consume newline

        if (classChoice > 0 && classChoice <= classList.getSize()) {
            Class selectedClass = classList.get(classChoice - 1);
            student.enrollInClass(selectedClass);
        } else if (classChoice != 0) {
            System.out.println("Invalid class selection.");
        } else {
            System.out.println("Enrollment canceled.");
        }
    }

    private static void dropClass(Scanner sc, Student student) {
        System.out.println("\n--- Drop Class ---");
        ArrayBasedList<Class> enrolledClasses = (ArrayBasedList<Class>) student.getEnrolledClasses();
        if (enrolledClasses.getSize() == 0) {
            System.out.println("You are not currently enrolled in any classes.");
            return;
        }
        System.out.println("Enrolled Classes:");
        for (int i = 0; i < enrolledClasses.getSize(); i++) {
            Class c = enrolledClasses.get(i);
            System.out.println(i + 1 + ". " + c.getCourse().getCourseAbbreviation() + " " +
                    c.getCourse().getCourseNumber() + " - Section " + c.getSection() +
                    " (" + c.getTime() + ")");
        }
        System.out.print("Enter the number of the class to drop (or 0 to cancel): ");
        int dropChoice = sc.nextInt();
        sc.nextLine();

        if (dropChoice > 0 && dropChoice <= enrolledClasses.getSize()) {
            Class classToDrop = enrolledClasses.get(dropChoice - 1);
            student.dropClass(classToDrop);
        } else if (dropChoice != 0) {
            System.out.println("Invalid class selection.");
        } else {
            System.out.println("Drop canceled.");
        }
    }

    //  --- Faculty Menu Actions ---
    private static void viewStudentsInClass(Scanner sc, Faculty faculty) {
        System.out.println("\n--- View Students in Class ---");
        ArrayBasedList<Class> assignedClasses = (ArrayBasedList<Class>) faculty.getAssignedClasses();
        if (assignedClasses.getSize() == 0) {
            System.out.println("You are not assigned to teach any classes.");
            return;
        }
        System.out.println("Assigned Classes:");
        for (int i = 0; i < assignedClasses.getSize(); i++) {
            Class c = assignedClasses.get(i);
            System.out.println(i + 1 + ". " + c.getCourse().getCourseAbbreviation() + " " +
                    c.getCourse().getCourseNumber() + " - Section " + c.getSection() +
                    " (" + c.getTime() + ")");
        }
        System.out.print("Enter the number of the class to view students (or 0 to cancel): ");
        int classChoice = sc.nextInt();
        sc.nextLine();

        if (classChoice > 0 && classChoice <= assignedClasses.getSize()) {
            Class selectedClass = assignedClasses.get(classChoice - 1);
            System.out.println(selectedClass.getEnrolledStudents());
        } else if (classChoice != 0) {
            System.out.println("Invalid class selection.");
        } else {
            System.out.println("Action canceled.");
        }
    }

    private static void viewWaitlistForClass(Scanner sc, Faculty faculty) {
        System.out.println("\n--- View Waitlist for Class ---");
        ArrayBasedList<Class> assignedClasses = (ArrayBasedList<Class>) faculty.getAssignedClasses();
        if (assignedClasses.getSize() == 0) {
            System.out.println("You are not assigned to teach any classes.");
            return;
        }
        System.out.println("Assigned Classes:");
        for (int i = 0; i < assignedClasses.getSize(); i++) {
            Class c = assignedClasses.get(i);
            System.out.println(i + 1 + ". " + c.getCourse().getCourseAbbreviation() + " " +
                    c.getCourse().getCourseNumber() + " - Section " + c.getSection() +
                    " (" + c.getTime() + ")");
        }
        System.out.print("Enter the number of the class to view waitlist (or 0 to cancel): ");
        int classChoice = sc.nextInt();
        sc.nextLine();

        if (classChoice > 0 && classChoice <= assignedClasses.getSize()) {
            Class selectedClass = assignedClasses.get(classChoice - 1);
            System.out.println(selectedClass.getWaitlist());
        } else if (classChoice != 0) {
            System.out.println("Invalid class selection.");
        } else {
            System.out.println("Action canceled.");
        }
    }

    //  --- Admin Menu Actions ---
    private static void addCourse(Scanner sc, Admin admin) {
        System.out.print("Enter course abbreviation (e.g., CSC): ");
        String abbreviation = sc.nextLine();
        System.out.print("Enter course number (e.g., 223): ");
        int number = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter course name: ");
        String name = sc.nextLine();
        System.out.print("Enter course credits: ");
        int credits = sc.nextInt();
        sc.nextLine();
        Course course = new Course(abbreviation, number, name, credits);
        admin.addCourse(course);
        System.out.println("Course added.");
    }

    private static void removeCourse(Scanner sc, Admin admin) {
        System.out.print("Enter the course abbreviation to remove: ");
        String abbreviation = sc.nextLine();
        System.out.print("Enter the course number to remove: ");
        int number = sc.nextInt();
        Course courseToRemove = null;
        for (int i = 0; i < admin.getCourseList().getSize(); i++) {  // Changed getSize() to size()
            Course c = admin.getCourseList().get(i);
            if (c.getCourseAbbreviation().equals(abbreviation) && c.getCourseNumber() == number) {
                courseToRemove = c;
                break;
            }
        }
        if (courseToRemove != null) {
            admin.removeCourse(courseToRemove);
            System.out.println("Course removed.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void scheduleClass(Scanner sc, Admin admin) {
        System.out.print("Enter course abbreviation: ");
        String abbreviation = sc.nextLine();
        System.out.print("Enter course number: ");
        int number = sc.nextInt();
        Course course = null;
        for (int i = 0; i < admin.getCourseList().getSize(); i++) {  // Changed getSize() to size()
            Course c = admin.getCourseList().get(i);
            if (c.getCourseAbbreviation().equals(abbreviation) && c.getCourseNumber() == number) {
                course = c;
                break;
            }
        }
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        System.out.print("Enter section number: ");
        int section = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter class time: ");
        String time = sc.nextLine();
        System.out.print("Enter class capacity: ");
        int capacity = sc.nextInt();
        admin.scheduleClass(course, section, time, capacity);
        System.out.println("Class scheduled.");
    }

    private static void cancelClass(Scanner sc, Admin admin) {
        System.out.println("Feature not fully implemented yet.");
    }

    private static void assignFacultyToClass(Scanner sc, Admin admin, ArrayBasedStack<Faculty> facultyList) {  // Changed parameter
        System.out.println("Feature not fully implemented yet.");
    }

    private static void hireFaculty(Scanner sc, Admin admin) {  // Removed facultyList parameter
        System.out.print("Enter faculty name: ");
        String name = sc.nextLine();
        Faculty faculty = new Faculty(name);
        System.out.print("Enter faculty username: ");
        String username = sc.nextLine();
        System.out.print("Enter faculty password: ");
        String password = sc.nextLine();
        Credentials credentials = new Credentials(username, password);
        faculty.setCredentials(credentials);
        admin.hireFaculty(faculty);
        System.out.println("Faculty hired.");
    }

    private static void fireFaculty(Scanner sc, Admin admin) {  // Removed facultyList parameter
        admin.fireFaculty();
        System.out.println("Faculty fired.");
    }

    private static void viewAllStudents(ArrayBasedList<Student> studentList) {
        System.out.println("\nAll Students:");
        if (studentList.getSize() == 0) {
            System.out.println("No students registered.");
        } else {
            for (int i = 0; i < studentList.getSize(); i++) {
                Student student = studentList.get(i);
                System.out.println("- " + student.getStudentId() + " - " + student.getName() +
                        " (" + student.getBirthdate() + ")");
            }
        }
    }

    private static void viewAllFaculty(Admin admin) {
        System.out.println("\nAll Faculty:");
        ArrayBasedStack<Faculty> facultyStack = admin.getFacultyStack(); // Get faculty stack from admin
        if (facultyStack.getSize() == 0) {
            System.out.println("No faculty hired.");
        } else {
            for (int i = 0; i < facultyStack.getSize(); i++) {
                Faculty faculty = facultyStack.getStack()[i];  // Access element using getStack()[i]
                System.out.println("- " + faculty.getFacultyId() + " - " + faculty.getName());
            }
        }
    }
}