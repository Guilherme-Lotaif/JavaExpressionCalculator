import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        System.out.println("\nEnter the infix expression: (Only Integers) ");
        String infixExpression = System.console().readLine();
        String cleanedInfixExpression = infixExpression.replaceAll("\\s", "");

        if (checkBalancing(cleanedInfixExpression)) {
            String postfixExpression = convertToPostfix(cleanedInfixExpression);
            
            double result = evaluateExpression(spaceNumbers(postfixExpression, infixExpression));
            System.out.println("\nResult: " + result + "\n");
        } else {
            System.out.println("Invalid expression. Check the balance of parentheses and brackets.");
        }
    }

    private static boolean checkBalancing(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char character : expression.toCharArray()) {
            if (character == '(' || character == '[') {
                stack.push(character);
            } else if (character == ')' || character == ']') {
                if (stack.isEmpty() || !isBalanced(stack.pop(), character)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isBalanced(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']');
    }

    private static String convertToPostfix(String infixExpression) {
        String postfix = "";
        Stack<Character> operatorStack = new Stack<>();
    
        for (int i = 0; i < infixExpression.length(); i++) {
            char currentChar = infixExpression.charAt(i);
    
            if (Character.isLetterOrDigit(currentChar)) {
                postfix += currentChar;
            } else if (currentChar == '(') {
                operatorStack.push(currentChar);
            } else if (currentChar == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix += operatorStack.pop();
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && precedence(currentChar) <= precedence(operatorStack.peek())) {
                    postfix += operatorStack.pop();
                }
                operatorStack.push(currentChar);
            }
        }
        while (!operatorStack.isEmpty()) {
            postfix += operatorStack.pop() + " ";
        }
        return postfix.trim();
    }
    
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    public static String spaceNumbers(String input, String infixExpression) {
        StringBuilder result = new StringBuilder();
        List<Integer> numDigits = countDigits(infixExpression);
    
        int i = 0;
        int j = 0;
    
        while (i < input.length()) {
            if (Character.isDigit(input.charAt(i))) {
                int currentNumDigits = numDigits.get(j);
                result.append(input, i, i + currentNumDigits).append(" ");
                i += currentNumDigits;
                j++;
            } else {
                result.append(input.charAt(i)).append(" ");
                i++;
            }
        }
        return result.toString().trim();
    }

    public static double evaluateExpression(String expression) {
        Stack<Double> stack = new Stack<>();

        String[] elements = expression.split(" ");

        for (String element : elements) {
            if (isNumber(element)) {
                stack.push(Double.parseDouble(element));
            } else {
                double num2 = stack.pop();
                double num1 = stack.pop();

                switch (element) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    case "%":
                        stack.push(num1 % num2);
                        break;
                    case "^":
                        stack.push(Math.pow(num1,num2));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + element);
                }
            }
        }
        return stack.pop();
    }

    private static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> countDigits(String expression) {
        List<Integer> numDigitsList = new ArrayList<>();
        StringBuilder currentNum = new StringBuilder();

        for (char character : expression.toCharArray()) {
            if (Character.isDigit(character) || character == '.') {
                currentNum.append(character);
            } else if (currentNum.length() > 0) {
                numDigitsList.add(currentNum.length());
                currentNum.setLength(0);
            }
        }
        if (currentNum.length() > 0) {
            numDigitsList.add(currentNum.length());
        }
        return numDigitsList;
    }
}
