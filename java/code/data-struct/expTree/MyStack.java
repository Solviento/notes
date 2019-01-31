import java.util.LinkedList; // Linkedlist for popping, push, etc

public class MyStack<T> {
  // Any ~ Shorthand for generic objects
  LinkedList<T> list;
  public MyStack(){
    list = new LinkedList<T>();
  }
  // Returns stack size
  public int size() {
    return list.size();
  }
  // Push element on stack
  public void push(T n) {
    list.addFirst(n);
  }
  // Pop element on stack
  public T pop() {
    if(list.size() == 0){
			return null;
		}
		else{
			return list.remove();
		}
  }
  // Retrieves element
  public T get(int i) {
    if (isEmpty()) {
      return null;
    }
    return list.get(i);
  }
  // displays first element in stack
  public T peek() {
    if(list.size() == 0)
      return null;
    else
      return list.getFirst();
  }
  // Important for exceptions out of bounds
  public boolean isEmpty() {
    return list.isEmpty();
  }
}