import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculadora {

    public static void main(String[] args) {
        System.out.println("\nDigite a expressão infixa:");
        String expressaoInfixa = System.console().readLine();
        String ExpressãoInfixafinal = expressaoInfixa.replaceAll("\\s", "");

        if (verificarBalanceamento(ExpressãoInfixafinal)) {
            String expressaoPosfixa = converterParaPosfixo(ExpressãoInfixafinal);
            
            double resultado = calcularExpressao(espacos(expressaoPosfixa, expressaoInfixa));
            System.out.println("\nResultado: " + resultado + "\n");
        } else {
            System.out.println("Expressão inválida. Verifique o balanceamento de parênteses e colchetes.");
        }
    }

    private static boolean verificarBalanceamento(String expressao) {
        Stack<Character> pilha = new Stack<>();

        for (char caractere : expressao.toCharArray()) {
            if (caractere == '(' || caractere == '[') {
                pilha.push(caractere);
            } else if (caractere == ')' || caractere == ']') {
                if (pilha.isEmpty() || !balanceamento(pilha.pop(), caractere)) {
                    return false;
                }
            }
        }
        return pilha.isEmpty();
    }

    private static boolean balanceamento(char aberto, char fechado) {
        return (aberto == '(' && fechado == ')') || (aberto == '[' && fechado == ']');
    }

    private static String converterParaPosfixo(String expressaoInfixa) {
        String posfixa = "";
        Stack<Character> pilhaOperadores = new Stack<>();
    
        for (int i = 0; i < expressaoInfixa.length(); i++) {
            char caractereAtual = expressaoInfixa.charAt(i);
    
            if (Character.isLetterOrDigit(caractereAtual)) {
                posfixa += caractereAtual;
            } else if (caractereAtual == '(') {
                pilhaOperadores.push(caractereAtual);
            } else if (caractereAtual == ')') {
                while (!pilhaOperadores.isEmpty() && pilhaOperadores.peek() != '(') {
                    posfixa += pilhaOperadores.pop();
                }
                pilhaOperadores.pop();
            } else {
                while (!pilhaOperadores.isEmpty() && precedencia(caractereAtual) <= precedencia(pilhaOperadores.peek())) {
                    posfixa += pilhaOperadores.pop();
                }
                pilhaOperadores.push(caractereAtual);
            }
        }
        while (!pilhaOperadores.isEmpty()) {
            posfixa += pilhaOperadores.pop() + " ";
        }
        return posfixa.trim();
    }
    
    private static int precedencia(char operador) {
        switch (operador) {
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

    public static String espacos(String input, String expressaoInfixa) {
        StringBuilder resultado = new StringBuilder();
        List<Integer> numDigitos = contarDigitos(expressaoInfixa);
    
        int i = 0;
        int j = 0;
    
        while (i < input.length()) {
            if (Character.isDigit(input.charAt(i))) {
                int currentNumDigitos = numDigitos.get(j);
                resultado.append(input, i, i + currentNumDigitos).append(" ");
                i += currentNumDigitos;
                j++;
            } else {
                resultado.append(input.charAt(i)).append(" ");
                i++;
            }
        }
        return resultado.toString().trim();
    }

    public static double calcularExpressao(String expressao) {
        Stack<Double> pilha = new Stack<>();

        String[] elementos = expressao.split(" ");

        for (String elemento : elementos) {
            if (isNumero(elemento)) {
                pilha.push(Double.parseDouble(elemento));
            } else {
                double num2 = pilha.pop();
                double num1 = pilha.pop();

                switch (elemento) {
                    case "+":
                        pilha.push(num1 + num2);
                        break;
                    case "-":
                        pilha.push(num1 - num2);
                        break;
                    case "*":
                        pilha.push(num1 * num2);
                        break;
                    case "/":
                        pilha.push(num1 / num2);
                        break;
                    case "%":
                        pilha.push(num1 % num2);
                        break;
                    default:
                        throw new IllegalArgumentException("Operador inválido: " + elemento);
                }
            }
        }
        return pilha.pop();
    }

    private static boolean isNumero(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.') {
                return false;
            }
        }
        return true;
    }

       private static List<Integer> contarDigitos(String expressao) {
        List<Integer> numDigitosList = new ArrayList<>();
        StringBuilder numAtual = new StringBuilder();

        for (char caractere : expressao.toCharArray()) {
            if (Character.isDigit(caractere) || caractere == '.') {
                numAtual.append(caractere);
            } else if (numAtual.length() > 0) {
                numDigitosList.add(numAtual.length());
                numAtual.setLength(0);
            }
        }

        if (numAtual.length() > 0) {
            numDigitosList.add(numAtual.length());
        }
        return numDigitosList;
    }
}
