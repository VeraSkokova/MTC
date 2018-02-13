package ru.nsu.ccfit.skokova.calculator;

import java.io.IOException;

public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        current = lexer.getLexeme();
    }

    public int calculate() throws IOException {
        int result = parseExpression();
        if(current.getType() != LexemeType.EOF) {
            throw new IllegalStateException("Bad expression");
        }
        return result;
    }

    public int parseExpression() throws IOException {
        int temp = parseTerm();
        while ((current.getType() == LexemeType.PLUS) || (current.getType() == LexemeType.MINUS)) {
            int sign = current.getType() == LexemeType.PLUS ? 1 : -1;
            current = lexer.getLexeme(); //не обсуждали?
            int value = parseTerm();
            temp += sign * value;
        }
        return temp;
    }

    public int parseTerm() throws IOException {
        int temp = parseFactor();
        while ((current.getType() == LexemeType.MULT) || (current.getType() == LexemeType.DIVISION)) {
            if (current.getType() == LexemeType.MULT) {
                current = lexer.getLexeme();
                int value = parseFactor();
                temp *= value;
            } else {
                current = lexer.getLexeme();
                int value = parseFactor();
                temp /= value;
            }
        }
        return temp;
    }

    public int parseFactor() throws IOException {
        int temp = parsePower();
        if (current.getType() == LexemeType.POWER) {
            current = lexer.getLexeme();
            return (int) Math.pow(temp, parseFactor()); //???
        }
        //current = lexer.getLexeme();
        return temp;

    }

    public int parsePower() throws IOException {
        if (current.getType() == LexemeType.MINUS) {
            current = lexer.getLexeme();
            return -1 * parseAtom();
        }
        return parseAtom();
    }

    public int parseAtom() throws IOException {
        if (current.getType() == LexemeType.NUMBER) {
            int result = Integer.parseInt(current.getLexemeString());
            current = lexer.getLexeme();
            return result;
        } else if (current.getType() == LexemeType.OPEN) {
            current = lexer.getLexeme();
            int temp = parseExpression();

            if (current.getType() != LexemeType.CLOSE) {
                throw new IllegalStateException("Bad lexeme, expected ')'");
            }

            current = lexer.getLexeme();
            return temp;
        } else {
            throw new RuntimeException();
        }
    }
}
