package ru.nsu.ccfit.skokova.calculator;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void plusTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("2+2")));
        int result = parser.calculate();
        assertEquals(4, result);

        parser = new Parser(new Lexer(new StringReader("2+3+4")));
        result = parser.calculate();
        assertEquals(9, result);
    }

    @Test
    public void minusTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("2-2")));
        int result = parser.calculate();
        assertEquals(0, result);

        parser = new Parser(new Lexer(new StringReader("10-5-7")));
        result = parser.calculate();
        assertEquals(-2, result);
    }

    @Test
    public void multTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("2*2")));
        int result = parser.calculate();
        assertEquals(4, result);

        parser = new Parser(new Lexer(new StringReader("10*12*20")));
        result = parser.calculate();
        assertEquals(2400, result);
    }

    @Test
    public void divTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("2/2")));
        int result = parser.calculate();
        assertEquals(1, result);

        parser = new Parser(new Lexer(new StringReader("12/4/3")));
        result = parser.calculate();
        assertEquals(1, result);
    }

    @Test
    public void powerTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("2^2")));
        int result = parser.calculate();
        assertEquals(4, result);

        parser = new Parser(new Lexer(new StringReader("2^3^4")));
        result = parser.calculate();
        assertEquals(2147483647, result);
    }

    @Test
    public void bracketsTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("(2+2)")));
        int result = parser.calculate();
        assertEquals(4, result);

        parser = new Parser(new Lexer(new StringReader("(2+3)*6")));
        result = parser.calculate();
        assertEquals(30, result);
    }

    @Test
    public void complexTest() throws Exception {
        Parser parser = new Parser(new Lexer(new StringReader("2+2*2^(2/2)")));
        int result = parser.calculate();
        assertEquals(6, result);
    }
}