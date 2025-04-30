public class Faculty {

    private String facultyId;
    private String name;
    private Credentials credentials;
    private ListInterface<Class> assignedClasses;
    private boolean fulltime;

    public boolean isFulltime() {
        return fulltime;
    }

    public void setFulltime(boolean fulltime) {
        this.fulltime = fulltime;
    }

    public void setAssignedClasses(ListInterface<Class> assignedClasses) {
        this.assignedClasses.add((Class) assignedClasses);
    }

    public Faculty(String name, boolean fulltime, Credentials c) { // Modified constructor
        this.credentials = c;
        this.fulltime = fulltime;
        this.name = name;
        this.assignedClasses = new ArrayBasedList<>();
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public ListInterface<Class> getAssignedClasses() {
        return assignedClasses;
    }

    public void assignClass(Class classToAssign) {
        this.assignedClasses.add(classToAssign);
    }

    public void removeClass(Class classToRemove) {
        this.assignedClasses.remove(classToRemove);
    }

    public int calculateTotalCredits() {
        int totalCredits = 0;
        for (int i = 0; i < assignedClasses.getSize(); i++) {
            Class c = assignedClasses.get(i);
            totalCredits += c.getCourse().getCredits();
        }
        return totalCredits;
    }

    public void viewAssignedClasses() {
        if (assignedClasses.getSize() == 0) {
            System.out.println("No classes assigned.");
        } else {
            System.out.println("Assigned Classes:");
            for (int i = 0; i < assignedClasses.getSize(); i++) {
                Class c = assignedClasses.get(i);
                System.out.println("- " + c.getCourse().getCourseAbbreviation() + " " +
                        c.getCourse().getCourseNumber() + " - Section " + c.getSectionNumber());
            }
        }
    }

    public void viewStudentsInClass(Class classToView) {
        if (!assignedClasses.find(classToView)) {
            System.out.println("Error: You are not assigned to teach this class.");
            return;
        }

        System.out.println("Students Enrolled in " + classToView.getCourse().getCourseAbbreviation() +
                " " + classToView.getCourse().getCourseNumber() + " - Section " +
                classToView.getSectionNumber() + ":");

        if (classToView.getEnrolledStudents().getSize() == 0) {
            System.out.println("No students currently enrolled.");
        } else {
            for (int i = 0; i < classToView.getEnrolledStudents().getSize(); i++) {
                Student student = classToView.getEnrolledStudents().get(i);
                System.out.println("- " + student.getStudentId() + " - " + student.getName());
            }
        }
    }

    public void viewWaitlistForClass(Class classToView) {
        if (!assignedClasses.find(classToView)) {
            System.out.println("Error: You are not assigned to teach this class.");
            return;
        }

        System.out.println("Waitlist for " + classToView.getCourse().getCourseAbbreviation() +
                " " + classToView.getCourse().getCourseNumber() + " - Section " +
                classToView.getSectionNumber() + ":");

        if (classToView.getWaitlist().getSize() == 0) {
            System.out.println("No students on the waitlist.");
        } else {
            for (int i = 0; i < classToView.getWaitlist().getSize(); i++) {
                Student student = classToView.getWaitlist().get(i);
                System.out.println("- " + student.getStudentId() + " - " + student.getName());
            }
        }
    }

//    public void addAssignedClass(Class selectedClass) {
//        this.addAssignedClass(selectedClass);
//        selectedClass.setFaculty(this);
//    }
}
