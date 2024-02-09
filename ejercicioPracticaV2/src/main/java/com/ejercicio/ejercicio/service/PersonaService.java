package com.ejercicio.ejercicio.service;

import com.ejercicio.ejercicio.agreggates.request.PersonaRequest;
import com.ejercicio.ejercicio.agreggates.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;

public interface PersonaService {
    BaseResponse crearPersona(PersonaRequest personaRequest);
    BaseResponse getInfoReniec(String numero);

}
