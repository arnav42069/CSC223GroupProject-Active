import java.util.Scanner;

public class Admin {

    private final Credentials adminLogin = new Credentials("admin", "admin");

    private ListInterface<Class> classList = new ArrayBasedList<>();

    private ListInterface<Course> courseList = new ArrayBasedList<>();

    private ArrayBasedStack<Faculty> facultyStack = new ArrayBasedStack<>();

    public ArrayBasedStack<Faculty> getFacultyStack() {
        return facultyStack;
    }
    public boolean checkCredentials() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("Enter admin password: ");
        String password = sc.nextLine();
        return username.equals(adminLogin.getUsername()) && password.equals(adminLogin.getPassword());
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void removeCourse(Course course) {
        //  Remove the course
        courseList.remove(course);

        //  Remove any classes associated with this course and unassign faculty/drop students
        for (int i = 0; i < classList.getSize(); i++) {
            Class c = classList.get(i);
            if (c.getCourse().equals(course)) {
                unassignFacultyFromClass(c);
                dropAllStudentsFromClass(c);
                classList.remove(c);
                i--; // Adjust index after removal
            }
        }
    }

    public void scheduleClass(Course course, int sectionNumber, String time, int capacity) {
        Class newClass = new Class(course, sectionNumber, time, capacity);
        classList.add(newClass);
    }

    public void cancelClass(Class classToCancel) {
        unassignFacultyFromClass(classToCancel);
        dropAllStudentsFromClass(classToCancel);
        classList.remove(classToCancel);
    }

    public void assignFacultyToClass(Class classToAssign, Faculty faculty) {
        //  Check for time conflicts (basic check - can be improved)
        if (isTimeConflict(classToAssign, faculty)) {
            System.out.println("Error: Time conflict detected. Cannot assign faculty.");
            return;
        }

        classToAssign.setInstructor(faculty);
        faculty.assignClass(classToAssign);
    }

    public void hireFaculty(Faculty faculty) {
        // Generate a faculty ID
        faculty.setFacultyId(generateFacultyId());
        facultyStack.push(faculty);
    }

    public void fireFaculty() {
        try {
            Faculty facultyToRemove = facultyStack.pop(); // Pop from the stack
            // Unassign faculty from all classes
            for (int i = 0; i < classList.getSize(); i++) {
                Class c = classList.get(i);
                if (c.getInstructor() != null && c.getInstructor().equals(facultyToRemove)) {
                    unassignFacultyFromClass(c);
                }
            }
            System.out.println("Fired faculty: " + facultyToRemove.getName());
        } catch (StackEmptyException e) {
            System.out.println("No faculty to fire.");
        }
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public ListInterface<Class> getClassList() {
        return classList;
    }

    public ArrayBasedStack<Faculty> getfacultyStack() {
        return facultyStack;
    }

    // --- Helper Methods ---

    private boolean isTimeConflict(Class classToAssign, Faculty faculty) {
        for (int i = 0; i < faculty.getAssignedClasses().getSize(); i++) {
            Class assignedClass = faculty.getAssignedClasses().get(i);
            if (assignedClass.getTime().equals(classToAssign.getTime())) {
                return true; //  Simple time conflict check
            }
        }
        return false;
    }

    private void unassignFacultyFromClass(Class classToUnassign) {
        Faculty currentInstructor = classToUnassign.getInstructor();
        if (currentInstructor != null) {
            currentInstructor.removeClass(classToUnassign);
            classToUnassign.setInstructor(null);
        }
    }

    private void dropAllStudentsFromClass(Class classToDrop) {
        // Drop all enrolled students
        while (classToDrop.getEnrolledStudents().getSize() > 0) {
            Student student = classToDrop.getEnrolledStudents().get(0);
            classToDrop.dropStudent(student); // Use Class's dropStudent method
        }

        // Clear the waitlist (assuming waitlist is also a ListInterface)
        while (classToDrop.getWaitlist().getSize() > 0) {
            classToDrop.getWaitlist().remove(classToDrop.getWaitlist().get(0));
        }
    }



    private String generateFacultyId() {
        return "FAC" + (int) (Math.random() * 10000);
    }
}