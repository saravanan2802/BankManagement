package com.proj.bankmanagement.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.proj.bankmanagement.config.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AccountNotFound(AccountNotFound ex) {
		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No Account Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> cannotChangeToSameAccountType(CannotChangeToSameAccountType ex) {
		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("Cannot Change To Same Account Type");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noAccountTypeFound(NoAccountTypeFound ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No Account Type Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noUserFound(UserNotFound ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No User Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noBranchFound(BranchNotFound ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No Branch Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noManagerFound(ManagerNotFound ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No Manager Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noAddressFound(AddressNotFound ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No Address Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> noBankFound(BankNotFound ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("No Bank Found");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);

	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> passwordMismatch(PasswordIncorrect ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("Password Mismatch");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> cannotMoveAccountToSameBranch(UserInSameBranch ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("Not Possible To Change Account to Same Branch");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> maintainMinimumBalanace(MinimumBalanceLimitExceeds ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("Minimum Balance Limit Exceeds");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> minimumTransactionAmount(TransactionAmount ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData("Minimum Transaction Amount should be Rs.1");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> SameAccountTransaction(CannotSendMoneyToOwnAccount ex) {

		ResponseStructure<String> rs = new ResponseStructure<>();

		rs.setData("Transaction Between Own Account Not possible");
		rs.setMsg(ex.getMessage());
		rs.setStatus(HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> errors = ex.getAllErrors();

		HashMap<String, String> map = new HashMap<>();

		for (ObjectError objectError : errors) {
			String message = objectError.getDefaultMessage();
			String fieldName = ((FieldError) objectError).getField();
			map.put(message, fieldName);
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}

}
