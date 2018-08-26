package clientME;

public class SocketConnectionException extends Exception {
	
	public SocketConnectionException() {

        super("Deconnexion du socket");

    }
    public SocketConnectionException(String message) {

        super(message.toString());
    }

}
