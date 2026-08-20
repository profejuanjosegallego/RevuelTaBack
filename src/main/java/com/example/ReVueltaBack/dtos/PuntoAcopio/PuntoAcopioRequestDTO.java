package com.example.ReVueltaBack.dtos.PuntoAcopio;

import com.example.ReVueltaBack.modelos.PuntoAcopio;

public record PuntoAcopioRequestDTO(

        String nombre,
        String direccion,
        String ciudad,
        String horario,
        Integer capacidad,
        Boolean activo

) {

    public PuntoAcopio toEntity() {

        PuntoAcopio puntoAcopio = new PuntoAcopio();
        puntoAcopio.setNombre(nombre);
        puntoAcopio.setDireccion(direccion);
        puntoAcopio.setCiudad(ciudad);
        puntoAcopio.setHorario(horario);
        puntoAcopio.setCapacidad(capacidad);
        puntoAcopio.setActivo(activo);
        return puntoAcopio;

    }
}