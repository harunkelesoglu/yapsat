package argedor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6356419813014051404L;

	public ConflictException(String exception) {
		super(exception);
	}
}
