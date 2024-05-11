package com.literalura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertidora(String json, Class<T> clase) {

        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());

        }
        return null;

    }

    // public <T> T convertirDTO(Class<T> classOrigin, Class<T> classDestiny){
    //     classOrigin.


    // }






}
