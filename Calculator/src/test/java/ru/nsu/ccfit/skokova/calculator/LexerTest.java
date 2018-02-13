package ru.nsu.ccfit.skokova.calculator;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {
    @Test
    public void emptyTest() throws Exception {
        Lexer lexer = new Lexer(new StringReader(""));
        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.EOF, lexeme.getType());
    }

    @Test
    public void simpleTest() throws Exception {
        Lexer lexer = new Lexer(new StringReader("2+2"));

        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.NUMBER, lexeme.getType());
        assertEquals("2", lexeme.getLexemeString());

        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.PLUS, lexeme.getType());
        assertEquals("+", lexeme.getLexemeString());
    }

    @Test
    public void plusMinusTest() throws Exception {
        Lexer lexer = new Lexer(new StringReader("2+2-2"));
        lexer.getLexeme();

        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.PLUS, lexeme.getType());

        lexer.getLexeme();
        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.MINUS, lexeme.getType());
    }

    @Test
    public void multDivTest() throws Exception {
        Lexer lexer = new Lexer(new StringReader("2*2/2"));
        lexer.getLexeme();

        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.MULT, lexeme.getType());

        lexer.getLexeme();
        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.DIVISION, lexeme.getType());
    }

    @Test
    public void powerTest() throws Exception {
        Lexer lexer = new Lexer(new StringReader("2^2"));
        lexer.getLexeme();

        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.POWER, lexeme.getType());
    }

    @Test
    public void bracketsTest() throws Exception {
        Lexer lexer = new Lexer(new StringReader("(2*2)"));

        Lexeme lexeme = lexer.getLexeme();
        assertEquals(LexemeType.OPEN, lexeme.getType());

        lexer.getLexeme();
        lexer.getLexeme();
        lexer.getLexeme();

        lexeme = lexer.getLexeme();
        assertEquals(LexemeType.CLOSE, lexeme.getType());
    }
}