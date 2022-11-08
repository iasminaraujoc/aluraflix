package br.com.aluratube.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException notValid){
        BindingResult bindingResult = notValid.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder("Os campos seguintes são obrigatórios: ");

        for (FieldError f: fieldErrors){
            stringBuilder.append( " | ");
            stringBuilder.append( " -> ");
            stringBuilder.append(f.getField());
            stringBuilder.append( " <- ");
        }

        MessageExceptionHandler erro = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), stringBuilder.toString());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
