@SuppressWarnings("ALL")
public class ArrayBasedStack<T> extends Stack<T>
{
    //instance variables
    private int top; //keeps track of the top of the stack
    private T [] stack; //reference to an array of Strings
    private int size;
    /**
     * constructors
     */
    public ArrayBasedStack() {
        super(); //call the default constructor of the parent class (Stack)
        top = -1; //the stack is empty when the top is -1
        stack = (T[])new Object[5]; //create an array of Strings with size 5
    }
    public ArrayBasedStack(int size) {
        super();
        top = -1;
        if(size > 0) {
            stack = (T[])new Object[size];
        }
        else {
            stack = (T[])new Object[5];
        }
    }
    @Override
    public void push(T item) {
        if(top < stack.length - 1) { //if there is room for another item
            top++;
            stack[top] = item;
            size++;
        }
        else {
            T[] biggerStack = (T[])new Object[size+size];
            for(int i = 0; i < size; i++) { //manual array copy, instead of System.arraycopy
                biggerStack[i] = stack[i];
            }
            biggerStack[size] = item;
            stack = biggerStack;
            top++;
            size++;
        }

    }
    @Override
    public String peek() {
        String topItem;
        if(top > -1) { //check if the stack is not empty
            topItem = stack[top].toString();
        }
        else {
            topItem = "The stack is empty";
        }
        return topItem;
    }
    @Override
    public void pop() throws StackEmptyException {

        if(top > -1) { //check if the stack is not empty
            stack[top] = null;
            top--;
            size--;
        }
        else {
            throw new StackEmptyException("Pop attempted on an empty stack!");
        }

    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public T[] getStack() {
        return stack;
    }

    public void setStack(T[] stack) {
        this.stack = stack;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}