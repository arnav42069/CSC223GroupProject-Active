public class Student {

    private String studentId;

    private String name;

    private String birthdate;

    private Credentials credentials;

    private ListInterface<Class> enrolledClasses;

    public Student(String name, String birthdate) {

        this.name = name;

        this.birthdate = birthdate;

        this.enrolledClasses = new ArrayBasedList<>();

    }

    public String getStudentId() {

        return studentId;

    }

    public void setStudentId(String studentId) {

        this.studentId = studentId;

    }

    public String getName() {

        return name;

    }

    public String getBirthdate() {

        return birthdate;

    }

    public Credentials getCredentials() {

        return credentials;

    }

    public void setCredentials(Credentials credentials) {

        this.credentials = credentials;

    }

    public ListInterface<Class> getEnrolledClasses() {

        return enrolledClasses;

    }

    public void enrollInClass(Class classToEnroll) {
        // Check if the student is already enrolled in this exact class
        for (int i = 0; i < enrolledClasses.getSize(); i++) {
            Class enrolledClass = enrolledClasses.get(i);
            if (enrolledClass.equals(classToEnroll)) {
                System.out.println("Error: Already enrolled in this exact class.");
                return; // Don't enroll, exit the method
            }
        }

        // Check if the student is already enrolled in any section of the same course
        for (int i = 0; i < enrolledClasses.getSize(); i++) {
            Class enrolledClass = enrolledClasses.get(i);
            if (enrolledClass.getCourse().equals(classToEnroll.getCourse())) {
                System.out.println("Error: Already enrolled in another section of this course.");
                return; // Don't enroll, exit the method
            }
        }

        // If all checks pass, enroll the student
        this.enrolledClasses.add(classToEnroll);
        System.out.println("Successfully enrolled in " +
                classToEnroll.getCourse().getCourseAbbreviation() + " " +
                classToEnroll.getCourse().getCourseNumber() + " - Section " +
                classToEnroll.getSectionNumber());
    }

    public void dropClass(Class classToDrop) {

        this.enrolledClasses.remove(classToDrop);

    }

    public void viewEnrolledClasses() {

        if (enrolledClasses.getSize() == 0) {

            System.out.println("Not enrolled in any classes.");

        } else {

            System.out.println("Enrolled Classes:");

            for (int i = 0; i < enrolledClasses.getSize(); i++) {

                Class c = enrolledClasses.get(i);

                System.out.println("- " + c.getCourse().getCourseAbbreviation() + " " +

                        c.getCourse().getCourseNumber() + " - Section " + c.getSectionNumber());

            }

        }

    }


}