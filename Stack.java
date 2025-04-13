public abstract class Stack<T> {
    //instance variables
    private String name;


    public Stack() {
        super();
        name = "";
    }

    public Stack(String stackName) {
        super();
        name = stackName;
    }

    //non-static methods
    public void setName(String stackName) {
        name = stackName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }

    /**
     * abstract methods are method headings with no body or implementation
     * that must be implemented by every child class that is concrete by
     * using the same method heading, but then giving it an
     * implementation
     */
    public abstract void push(T item) throws StackFullException; // <-- method

    public abstract void pop() throws StackEmptyException; // <-- method heading

    public abstract String peek(); // <-- method heading
}