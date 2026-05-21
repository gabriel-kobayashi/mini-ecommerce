package com.gabrieltk.mini_ecommerce.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String msg) {
        super(msg);
    }
}
