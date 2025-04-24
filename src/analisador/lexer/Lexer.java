/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador.lexer;

/**
 *
 * @author ig_al
 */
import java.util.*;
import java.util.regex.*;

public class Lexer {

    // Lista de palavras-chave da linguagem
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
        "if", "else", "while", "int", "return", "for", "float", "void"
    ));

    // Map de expressões regulares por tipo de token
    private static final Map<TokenType, String> TOKEN_REGEX = Map.of(
        TokenType.WHITESPACE, "\\s+",
        TokenType.KEYWORD, "\\b(if|else|while|int|return|for|float|void)\\b",
        TokenType.IDENTIFIER, "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b",
        TokenType.NUMBER, "\\b\\d+\\b",
        TokenType.OPERATOR, "[+\\-*/=<>!]=?|==|&&|\\|\\|",
        TokenType.SYMBOL, "[(){};,]"
    );

    // Método que realiza a tokenização do código
    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>(); // cria uma lista para armazenar os tokens

        List<Map.Entry<TokenType, Pattern>> patterns = new ArrayList<>(); //Prepara uma lista com os padrões compilados de expressões regulares
        for (var entry : TOKEN_REGEX.entrySet()) {
            patterns.add(Map.entry(entry.getKey(), Pattern.compile(entry.getValue())));
        }

        int pos = 0;
        while (pos < input.length()) {
            boolean matched = false;

            for (var entry : patterns) {
                Matcher matcher = entry.getValue().matcher(input);
                matcher.region(pos, input.length());
                if (matcher.lookingAt()) {
                    String value = matcher.group();
                    if (entry.getKey() != TokenType.WHITESPACE) {
                        tokens.add(new Token(entry.getKey(), value));
                    }
                    pos = matcher.end();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                tokens.add(new Token(TokenType.ERROR, String.valueOf(input.charAt(pos))));
                pos++;
            }
        }

        return tokens;
    }
}

