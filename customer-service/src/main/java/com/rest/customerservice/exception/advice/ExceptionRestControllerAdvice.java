package com.rest.customerservice.exception.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rest.customerservice.exception.ErrorResponse;
import com.rest.customerservice.exception.ServiceException;

//@ControllerAdvice
@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionRestControllerAdvice extends ResponseEntityExceptionHandler{
//public class ExceptionRestControllerAdvice {
	
	Logger logger = LoggerFactory.getLogger(ExceptionRestControllerAdvice.class);
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(Exception.class)
//    public void defaultExceptionHandler() {
//        // Nothing to do
//    }
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> handlerException(Exception ex){
//		System.out.println("Inside handlerException");
//		return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	
	/*	
	 * @ExceptionHandler(MyException.class)
    public ModelAndView handleMyException(MyException mex) {
 
        ModelAndView model = new ModelAndView();
        model.addObject("errCode", mex.getErrCode());
        model.addObject("errMsg", mex.getErrMsg());
        model.setViewName("error/generic_error");
        return model;
    }
    
	 * @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
 
        ModelAndView model = new ModelAndView();
        model.addObject("errMsg", "This is a 'Exception.class' message.");
        model.setViewName("error/generic_error");
        return model;
 
    }*/
	
	//Working properly 
	@ExceptionHandler(value = ServiceException.class)
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> exceptionHandler(ServiceException ex, WebRequest request){
		System.out.println("customerservice >> Inside exceptionHandler");
		System.out.println(">>>>> "+ex.getLocalizedMessage());
		
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
		System.out.println("### "+response);
		logger.error(ex.getMessage()+" >> "+response);
//		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
		return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	//Working properly 
//	@ExceptionHandler(value = {ServiceException.class})
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	public ErrorResponse exceptionHandler(ServiceException ex){
//		System.out.println("customerservice >> Inside exceptionHandler");
//		System.out.println(">>>>> "+ex.getLocalizedMessage());
//		
//		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getLocalizedMessage());
//		System.out.println("### "+response);
//		logger.error(ex.getMessage()+" >> "+response);
//		return response;
//	}
	
	
	
	
//	@ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
//        return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, ex, "Please enter a valid value"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
//        return new ResponseEntity<>(getBody(HttpStatus.BAD_REQUEST, ex, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
//        return new ResponseEntity<>(getBody(HttpStatus.FORBIDDEN, ex, ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> exception(Exception ex) {
//        return new ResponseEntity<>(getBody(HttpStatus.INTERNAL_SERVER_ERROR, ex, "Something Went Wrong"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    public Map<String, Object> getBody(HttpStatus status, Exception ex, String message) {
//
//    	logger.error(message, ex);
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("message", message);
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//        body.put("error", status.getReasonPhrase());
//        body.put("exception", ex.toString());
//
//        Throwable cause = ex.getCause();
//        if (cause != null) {
//            body.put("exceptionCause", ex.getCause().toString());
//        }
//        return body;
//    }
	
	
	
}
