package ru.nsu.ccfit.skokova.calculator;

public class Lexeme {
    private String lexemeString;
    private LexemeType type;

    public Lexeme(String lexemeString, LexemeType type) {
        this.lexemeString = lexemeString;
        this.type = type;
    }

    public LexemeType getType() {
        return type;
    }

    public String getLexemeString() {
        return lexemeString;
    }

    @Override
    public String toString() {
        return lexemeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lexeme lexeme = (Lexeme) o;

        //if (lexemeString != null ? !lexemeString.equals(lexeme.lexemeString) : lexeme.lexemeString != null)
          //  return false;
        return type == lexeme.type;
    }

    @Override
    public int hashCode() {
        int result = lexemeString != null ? lexemeString.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
