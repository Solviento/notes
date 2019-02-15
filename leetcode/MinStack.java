// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack.
import java.util.Stack;
class MinStack {
    /** initialize your data structure here. */
    Stack<Integer> minStack_;
    Stack<Integer> stack_;
    public MinStack() {
        minStack_ = new Stack<>();
        stack_ = new Stack<>();
    }
    public void push(int x) {
        if (minStack_.isEmpty()){
            minStack_.push(x);
        }
        else if(minStack_.peek() >= x){
            minStack_.push(x);
        }
        stack_.push(x);
    }
    
    public void pop() {
        int x = minStack_.peek();
        int y = stack_.peek();
        if(x == y){
            minStack_.pop();
        }
        stack_.pop();
    }
    
    public int top() {
        return stack_.peek();
    }
    
    public int getMin() {
        return minStack_.peek();
    }

    public static void main(String... args){
        MinStack s = new MinStack();
        s.push(512);
        s.push(-1024);
        s.push(-1024);
        s.push(512);
        s.pop();
        s.pop();
        s.pop();
        System.out.println(s.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */