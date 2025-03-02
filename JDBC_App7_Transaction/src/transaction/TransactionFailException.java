package transaction;

@SuppressWarnings("serial")
public class TransactionFailException extends Exception{
	public TransactionFailException() {
		
	}
	public TransactionFailException(String msg) {
		super(msg);
	}
}
