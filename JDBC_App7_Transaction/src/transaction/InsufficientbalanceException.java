package transaction;

@SuppressWarnings("serial")
public class InsufficientbalanceException extends Exception{
	public InsufficientbalanceException() {
		
	}
	public InsufficientbalanceException(String msg) {
		super(msg);
	}
}
