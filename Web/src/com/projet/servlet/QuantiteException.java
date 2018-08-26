package com.projet.servlet;


public class QuantiteException extends Exception {

	private static final long serialVersionUID = 1L;
	public QuantiteException() {
        super("Erreur lors du calcul de la nouvelle quantité");
    }

	public QuantiteException( String message ) {
        super( message );
    }
}

