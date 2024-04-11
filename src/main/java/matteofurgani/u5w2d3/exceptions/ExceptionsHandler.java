package matteofurgani.u5w2d3.exceptions;


import matteofurgani.u5w2d3.payloads.ErrorsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponseDTO handleBadRequest(BadRequestException ex){
       if(ex.getErrorList() != null){
           String message = ex.getErrorList().stream().map(objectError ->
                   objectError.getDefaultMessage()).collect(Collectors.joining(". "));
           return new ErrorsResponseDTO(message, LocalDateTime.now());

       } else {
           return new ErrorsResponseDTO(ex.getMessage(), LocalDateTime.now());
       }
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleNotFound(NotFoundException ex){
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGenericErrors(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayload("Problema dovuto dal Server. Risolveremo il prima possibile!", LocalDateTime.now());
    }
}
