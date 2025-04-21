import java.util.ArrayList;

public class User extends Credentials {
    private String type;
    private String name;
    private ArrayList<Class> classes;


//these lists just store username and passwords

    private ArrayBasedList<Credentials> stuList;
    private ArrayBasedStack<Credentials> facList;



    public User(String name, String username, String password) {
        super(username, password);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setLogin(String password, String username){
        this.password = password;
        this.username = username;
    }

    private boolean exists(String username, String password){
        Credentials cred = new Credentials(username, password);
        if (stuList.find(cred) || facList.find(cred)){
            return true;
        }
        return false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
// workin on this rn
//    public ArrayBasedList<User> addStu(ArrayBasedList<Credentials> stuList) {
//        ArrayBasedList<User> stuList = new ArrayBasedList<>(10);
//        stuList.add();
//
//    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}
