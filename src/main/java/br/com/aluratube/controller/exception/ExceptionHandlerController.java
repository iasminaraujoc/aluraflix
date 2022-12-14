package br.com.aluratube.controller.exception;

import br.com.aluratube.config.security.TokenInvalidoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> itemNaoEncontrado(ItemNotFoundException itemNotFoundException){
        MessageExceptionHandler erro = new MessageExceptionHandler(new Date(), HttpStatus.BAD_REQUEST.value(), "Item não encontrado");
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

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

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageExceptionHandler> impossivelDeletar(DataIntegrityViolationException dataIntegrityViolationException){
        MessageExceptionHandler erro = new MessageExceptionHandler(new Date(), HttpStatus.METHOD_NOT_ALLOWED.value(),
                "Impossível deletar! A categoria está associada a algum vídeo.");
        return new ResponseEntity<>(erro, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<MessageExceptionHandler> tokenInvalido(TokenInvalidoException tokenInvalidoException){
        MessageExceptionHandler erro = new MessageExceptionHandler(new Date(), HttpStatus.FORBIDDEN.value(),
                "Credenciais inválidas!");
        return new ResponseEntity<>(erro, HttpStatus.FORBIDDEN);
    }
}
