package com.literalura.literalura;

import com.literalura.literalura.service.ConexionAPI;

public class Principal {

    ConexionAPI connection = new ConexionAPI();


    public void logicaPrincipal() {
        System.out.println(connection.obtenerDatos());
    }

}
