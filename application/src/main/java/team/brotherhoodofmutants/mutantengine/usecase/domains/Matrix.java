package team.brotherhoodofmutants.mutantengine.usecase.domains;

public class Matrix {

    private int lines;
    private int columns;
    private String[][]values;

    public Matrix() {
    }

    public Matrix(int lines, int columns) {
        this.lines = lines;
        this.columns = columns;
    }

    public Matrix(int lines, int columns, String[][] values) {
        this.lines = lines;
        this.columns = columns;
        this.values = values;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String[][] getValues() {
        return values;
    }

    public void setValues(String[][] values) {
        this.values = values;
    }
}
