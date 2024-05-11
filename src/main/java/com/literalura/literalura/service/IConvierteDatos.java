package com.literalura.literalura.service;

public interface IConvierteDatos {

    <T> T convertidora(String json, Class<T> clase);

}
