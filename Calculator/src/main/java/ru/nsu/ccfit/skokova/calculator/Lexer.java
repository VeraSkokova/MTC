package ru.nsu.ccfit.skokova.calculator;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class Lexer {
    private Reader reader;
    private char current;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        current = (char) reader.read();
    }

    public ru.nsu.ccfit.skokova.calculator.Lexeme getLexeme() throws IOException {
        if (current == '\uFFFF') {
            return new Lexeme("", LexemeType.EOF);
        }
        while (Character.isWhitespace(current)) {
            current = (char) reader.read();
        }
        String temp = "";

        if (Character.isDigit(current)) {
            do {
                temp += current;
                current = (char) reader.read();
            } while (Character.isDigit(current));
        }

        if (!Objects.equals(temp, "")){
            return new Lexeme(temp, LexemeType.NUMBER);
        }

        switch (current) {
            case '+':
                current = (char) reader.read();
                return new Lexeme("+", LexemeType.PLUS);
            case '-':
                current = (char) reader.read();
                return new Lexeme("-", LexemeType.MINUS);
            case '*':
                current = (char) reader.read();
                return new Lexeme("*", LexemeType.MULT);
            case '/':
                current = (char) reader.read();
                return new Lexeme("/", LexemeType.DIVISION);
            case '^':
                current = (char) reader.read();
                return new Lexeme("^", LexemeType.POWER);
            case '(':
                current = (char) reader.read();
                return new Lexeme("(", LexemeType.OPEN);
            case ')':
                current = (char) reader.read();
                return new Lexeme(")", LexemeType.CLOSE);
            default:
                break;

        }
        throw new RuntimeException();
    }
}
