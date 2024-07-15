package com.libreria.literatura.service;

public interface IDataConvert {
    <T> T obtenerDatos(String Json, Class<T> clase);
}
