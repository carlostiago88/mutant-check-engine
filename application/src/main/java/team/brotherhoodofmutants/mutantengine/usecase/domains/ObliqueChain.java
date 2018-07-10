package team.brotherhoodofmutants.mutantengine.usecase.domains;

public class ObliqueChain {
    private int sequencial;
    private int line;
    private String nucleotide;

    public ObliqueChain(int sequencial, int line, String nucleotide) {
        this.sequencial = sequencial;
        this.line = line;
        this.nucleotide = nucleotide;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getNucleotide() {
        return nucleotide;
    }

    public void setNucleotide(String nucleotide) {
        this.nucleotide = nucleotide;
    }
}
