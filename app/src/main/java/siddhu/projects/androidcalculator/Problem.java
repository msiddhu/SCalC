package siddhu.projects.androidcalculator;
public class Problem {

       public String calcTheResult(String input){
        return  calculatePostfix(inToPost(input));
    }

    // Function to convert infix to postfix expression
    public static String inToPost(String userInput){
        // Variables
        String postfix = "";
        char[] charInput = userInput.toCharArray();
        MyStack stack = new MyStack();

        // Loop till the end of the user input
        for (int i = 0; i < userInput.length(); i++) {

            // If the character at the i(th) position is a digit then append it to postfix result
            if (charInput[i] >= '0' && charInput[i] <= '9') {
                postfix += charInput[i];
            }

            // If the character at the i(th) position is a + or - then
            else if (charInput[i] == '+' || charInput[i] == '-') {
                postfix += " ";
                // Check for the null point exception
                if (stack.getTopElement() == 0 || stack.getTopElement() == -1 || stack.getTopElement() == '(') {
                    stack.push(charInput[i]);
                }
                // Also check for the precedence and then push it on to stack
                else {
                    if ((stack.getTopElement() == '*' || stack.getTopElement() == '/' || stack.getTopElement() == '+' || stack.getTopElement() == '-')){
                        postfix += " " + (char)stack.pop();
                        if (stack.getTopElement() == '+'||stack.getTopElement() == '-'){
                            postfix += " " + (char)stack.pop();
                        }
                        stack.push(charInput[i]);
                    }
                }
            }

            // If the character at the i(th) position is a * or / then
            else if (charInput[i] == '*' || charInput[i] == '/') {
                postfix += " ";
                // Check for the null pointer exception
                if (stack.getTopElement() == 0) {
                    stack.push(charInput[i]);
                }
                // Also check for precedence and then push it on to stack
                else {
                    if (stack.getTopElement() == '/') {
                        postfix += " " + (char) stack.pop();
                        stack.push(charInput[i]);
                    } else {
                        stack.push(charInput[i]);
                    }
                }
            }

            // If the character at the i(th) position is a ( then push it on to stack
            else if (charInput[i] == '(') {
                postfix += " ";
                stack.push(charInput[i]);
            }

            // If the character at the i(th) position is a ) then
            else if (charInput[i] == ')') {
                postfix += " ";
                // Append all operators before (
                while (stack.getTopElement() != '(') {
                    postfix += " " + (char) stack.pop();
                }
                // and pop (
                stack.pop();
            }
        }

        // At the end pop everything remaining in the stack
        while (stack.top != -1) {
            postfix += " " +  (char) stack.pop();
        }

        // Then print the result
        System.out.println("Here is the postfix expression: " + postfix);

        return postfix;
    }

    // Function to evaluate the postfix expression
    public static String calculatePostfix(String expression){
        // Variables
        float result = 0;
        MyStack resultStack = new MyStack();
        char[] charPostfix = expression.toCharArray();

        // Loop till the end of the expression and when an operator is encountered, use it on last two elements of the Stack
        for (int i = 0; i < expression.length(); i++){
            if (charPostfix[i] == ' '){
                continue;
            } else if (charPostfix[i] >= '0' && charPostfix[i] <= '9'){
                int j = i;
                int count = 0;
                resultStack.push((int)(charPostfix[i])-'0');
                while (charPostfix[j+1] >= '0' && charPostfix[j+1] <= '9'){
                    resultStack.push((int)(charPostfix[i] - '0') + (resultStack.pop()* 10));
                    count++;
                    j++;
                }
                i += count;
                //System.out.println(resultStack.getTopElement());
            } else if (charPostfix[i] == '+'){
                resultStack.push(resultStack.pop() + resultStack.pop());
            } else if (charPostfix[i] == '-'){
                resultStack.push(resultStack.pop() - resultStack.pop());
            } else if (charPostfix[i] == '*'){
                resultStack.push(resultStack.pop() * resultStack.pop());
            } else if (charPostfix[i] == '/'){
                resultStack.push(resultStack.pop() / resultStack.pop());
            }
        }

        // Pop the result and display it
        result = resultStack.pop();
        return Float.toString(result);
    }
}