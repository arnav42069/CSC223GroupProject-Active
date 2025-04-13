public class Student extends User{
    private int DOB;

    public Student(){
        super();
    }
    public Student(String name, int DOB){
        super(name);
        this.DOB = DOB;
    }

    public int getDOB(){
        return DOB;
    }
    public void setDOB(int DOB){
        this.DOB = DOB;
    }




}
