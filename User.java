public class User {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String name;
    private int ID;
    private String password;

    public User(String name, int ID, String password) {
        this.name = name;
        this.ID = ID;
        this.password = password;
    }

    public User(){
        this.name = "";
        this.ID = 0;
        this.password = "";
    }

    public User(String name){
        this.name = name;
        this.ID = 0;
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String password, int ID){
        this.password = password;
        this.ID = ID;
    }
}
