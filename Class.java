public class Class {

    private Course course;

    private int sectionNumber;

    private String time;

    private Faculty instructor;

    private int capacity;

    private ListInterface<Student> enrolledStudents;

    private ListInterface<Student> waitlist;

    public Class(Course course, int sectionNumber, String time, int capacity) {

        this.course = course;

        this.sectionNumber = sectionNumber;

        this.time = time;

        this.capacity = capacity;

        this.enrolledStudents = new ArrayBasedList<>();

        this.waitlist = new ArrayBasedList<>();

        this.instructor = null; // Initially no instructor

    }

    public Course getCourse() {

        return course;

    }

    public int getSectionNumber() {

        return sectionNumber;

    }

    public String getTime() {

        return time;

    }

    public Faculty getInstructor() {

        return instructor;

    }

    public void setInstructor(Faculty instructor) {

        this.instructor = instructor;

    }

    public int getCapacity() {

        return capacity;

    }

    public ListInterface<Student> getEnrolledStudents() {

        return enrolledStudents;

    }

    public ListInterface<Student> getWaitlist() {

        return waitlist;

    }

    public boolean isFull() {

        return enrolledStudents.getSize() >= capacity;

    }

    public void enrollStudent(Student student) {

        if (!isFull()) {

            enrolledStudents.add(student);

        } else {

            addToWaitlist(student);

        }

    }

    public void dropStudent(Student student) {

        enrolledStudents.remove(student);

        //  If a student drops and there's a waitlist, move the first student

        if (waitlist.getSize() > 0) {

            Student studentToEnroll = waitlist.get(0);

            waitlist.remove(studentToEnroll);

            enrollStudent(studentToEnroll);

        }

    }

    public void addToWaitlist(Student student) {

        waitlist.add(student);

    }


    public int getSection() {
        return sectionNumber;
    }

}