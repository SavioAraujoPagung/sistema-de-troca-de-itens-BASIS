package com.colatina.sgt.service.builder;

public interface Customizacao<E> {

    void executar(E entidade);
}