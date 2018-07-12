package team.brotherhoodofmutants.mutantengine.core.exceptions;

public class MatrixException extends RuntimeException {

    public MatrixException(String message) {
        super(message);
    }

    public MatrixException(String message, Throwable cause) {
        super(message, cause);
    }
}