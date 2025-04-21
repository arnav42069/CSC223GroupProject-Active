public class Course {

    private String courseAbbreviation;

    private int courseNumber;

    private String courseName;

    private int credits;

    public Course(String courseAbbreviation, int courseNumber, String courseName, int credits) {

        this.courseAbbreviation = courseAbbreviation;

        this.courseNumber = courseNumber;

        this.courseName = courseName;

        this.credits = credits;

    }

    public String getCourseAbbreviation() {

        return courseAbbreviation;

    }

    public int getCourseNumber() {

        return courseNumber;

    }

    public String getCourseName() {

        return courseName;

    }

    public int getCredits() {

        return credits;

    }

    public void setCredits(int credits) {

        this.credits = credits;

    }

    @Override

    public String toString() {

        return courseAbbreviation + " " + courseNumber + " - " + courseName;

    }

}