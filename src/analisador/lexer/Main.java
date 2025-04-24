package analisador.lexer;

import java.util.List;
import java.util.Scanner;

//gabriel
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código fonte (linha vazia para terminar):");
        StringBuilder code = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            code.append(line).append("\n");
        }

        List<Token> tokens = Lexer.tokenize(code.toString());
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
