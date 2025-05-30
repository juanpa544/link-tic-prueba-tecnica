package co.juanpablancom.linkticprueba.productos.infrastructure.adapter.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.juanpablancom.linkticprueba.productos.domain.exception.PrecioInvalidoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoDuplicadoException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoNotFoundException;
import co.juanpablancom.linkticprueba.productos.domain.exception.ProductoSinNombreException;
import co.juanpablancom.linkticprueba.productos.domain.model.ProblemModel;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoDuplicadoException.class)
    public ResponseEntity<ProblemModel> handleProductoDuplicado(ProductoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ProblemModel.builder().title(ex.getMessage()).build());
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ProblemModel> handleProductoNotFound(ProductoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ProblemModel.builder().title(ex.getMessage()).build());
    }

    @ExceptionHandler(ProductoSinNombreException.class)
    public ResponseEntity<ProblemModel> handleProductoSinNombre(ProductoSinNombreException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProblemModel.builder().title(ex.getMessage()).build());
    }

    @ExceptionHandler(PrecioInvalidoException.class)
    public ResponseEntity<ProblemModel> handlePrecioInvalido(PrecioInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ProblemModel.builder().title(ex.getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemModel> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ProblemModel.builder().title("Error interno del servidor: " + ex.getMessage()).build());
    }
}