import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        ArrayBasedStack<Student> studentStack = new ArrayBasedStack<>();
        do {
            System.out.println("Welcome!");
            System.out.print("Please select your user type: ");
            System.out.print("1. for Student");
            System.out.print("2. for Teacher");
            System.out.print("3. for Admin");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.next();
                    System.out.print("Enter your Date of Birth(DDMMYYYY): ");
                    int age = sc.nextInt();
                    Student thisStudent = new Student(name, age);
                    thisStudent.setType("Student");
                    studentStack.push(thisStudent);
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    name = sc.next();
                    System.out.print("Enter your password");

                    // go to instructor login w constructor

                    break;
                case 3:
                    System.out.print("Enter your admin userID");
                    int ID = sc.nextInt();
                    System.out.print("Enter your admin passsword");
                    String pass = sc.next();
                    //make admin constructor w login
                    break;
                default:
                    break;

            }
        }while (choice != 4);
    }
}
