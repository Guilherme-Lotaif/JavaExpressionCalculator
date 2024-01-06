# Arithmetic Expressions Evaluator in Java

This program was developed to solve arithmetic expressions, after checking whether the parentheses are balanced.

## Key Features

1. **Correction Verification**<br>

The program uses a stack structure to store and check the matching of parentheses '()' and brackets '[]' in the entered expression. When analyzing the expression, the program performs the following steps:

Iterates through the expression and pushes open parentheses/brackets ('(' or '['). Upon encountering a closing parenthesis/bracket (')' or ']'), it checks if it matches the last open parenthesis/bracket on the stack. If it matches, it pops the last element from the stack; otherwise, the expression is incorrect.
After traversing the entire expression, if the stack is empty, the expression is correct in terms of matching parentheses/brackets. In this case, the program calculates and displays the final value of the expression.

2. **Usage Instructions**

- Run the program in a Java environment.
- Enter the desired arithmetic expression, using integers and arithmetic operators (+, -, *, /, %, ^).
- Use parentheses '()' and/or brackets '[]' as needed in the expression.
- The program will check the correctness of the expression and display the result or an error message.
