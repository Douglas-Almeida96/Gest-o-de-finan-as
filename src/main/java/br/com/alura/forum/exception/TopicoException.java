package br.com.alura.forum.exception;

public class TopicoException extends Exception {

    private static final long serialVersionUID = -6304519107418735310L;

    public TopicoException() {
        super();
    }

    public TopicoException(String message) {
        super(message);
    }

    public TopicoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicoException(Throwable cause) {
        super(cause);
    }
}
