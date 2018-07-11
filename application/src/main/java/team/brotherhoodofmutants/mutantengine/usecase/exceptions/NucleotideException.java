package team.brotherhoodofmutants.mutantengine.usecase.exceptions;

public class NucleotideException extends RuntimeException {

    public NucleotideException(String message) {
        super(message);
    }

    public NucleotideException(String message, Throwable cause) {
        super(message, cause);
    }
}