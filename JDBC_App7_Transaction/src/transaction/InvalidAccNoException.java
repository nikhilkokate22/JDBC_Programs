package transaction;

@SuppressWarnings("serial")
public class InvalidAccNoException extends Exception{
	public InvalidAccNoException() {
		
	}
	public InvalidAccNoException(String msg) {
		super(msg);
	}
}
