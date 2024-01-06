# Arithmetic Expressions Evaluator in Java

This program was developed to solve arithmetic expressions, after checking whether the parentheses are balanced.

## Key Features

1. **Logic walkthrough**<br>

- The program uses a stack structure to store and check the matching of parentheses '()' and brackets '[]' in the entered expression.
- It iterates through each element in the expression.
- If the element is an open parenthesis/bracket ('(' or '['), it is pushed onto the stack.
- When a closing parenthesis/bracket (')' or ']') is encountered, it checks if it matches the last open parenthesis/bracket on the stack.
- If there is a match, the last element is popped from the stack; otherwise, the expression is considered incorrect.
- After iterating through the entire expression, if the stack is empty, the expression is correct in terms of matching parentheses/brackets.
- In this case, the program calculates and displays the final value of the expression.

2. **Usage Instructions**

- Run the program in a Java environment.
- Enter the desired arithmetic expression, using integers and arithmetic operators (+, -, *, /, %, ^).
- Use parentheses '()' and/or brackets '[]' as needed in the expression.
- The program will check the correctness of the expression and display the result or an error message.

## License

This project is licensed under the MIT License - [LICENSE](https://github.com/Guilherme-Lotaif/JavaExpressionCalculator/blob/main/LICENSE).
