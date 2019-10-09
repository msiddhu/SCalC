package siddhu.projects.androidcalculator;

public class MyStack {
    // Attributes
    public float[] stackList;
    public int top;
    public int size;

    // Constructor
    public MyStack(){
        // Initializing
        top = -1;
        size = 0;
        stackList = new float[size];
    }

    // Push Function
    public void push(float i){
        size++;													// Increment the size of the stack
        float[] newStackList = new float[size];						// Create a new stack
        if (size-1 == 0){										// If stack size is one then adjust the top position to be 0
            top = 0;
        } else {												// Else copy elements of old stack in new stack
            for(int k=0; k < size-1; k++){
                newStackList[k] =  stackList[k];
                top = size - 1;									// and adjust the top position
            }
        }

        stackList = newStackList;								// The old stack is now the new stack
        stackList[size-1] = i;									// Storing the pushed element in the last place of the stack
    }

    // Pop Function
    public float pop(){
        // If the stack is empty, then return false
        if (top == -1){
            return 0;
        }
        float topElement = stackList[(int) top];

        // If the stacks' size is greater than 1 then copy elements of the old stack in a new stack
        if (size > 1) {
            size--;												// Decrement the size of the stack by one
            float[] newStackList = new float[size]; 				// Create a new stack

            for(int k=0; k < size; k++){						// Copy elements of old stack in new stack
                newStackList[k] = stackList[k];
            }

            stackList = newStackList;							// The old stack is now the new stack
            top = size - 1;										// Adjusting the top position
            return topElement;									// returning the saved top element
        }
        // Else if the stacks' size is equal to one then return the saved top element and decrease the size of the stack
        else if (size == 1) {
            size--;
            top = -1;
            return topElement;
        } else {
            return -1;
        }
    }

    // This is the peek function:
    public float getTopElement(){
        if(top!=-1)
            return stackList[top];
        return 0;
    }
}