package com.ejercicio.ejercicio.service;

import com.ejercicio.ejercicio.agreggates.request.EmpresaRequest;
import com.ejercicio.ejercicio.agreggates.request.PersonaRequest;
import com.ejercicio.ejercicio.agreggates.response.BaseResponse;

public interface EmpresaService {

    BaseResponse crearEmpresa(EmpresaRequest empresaRequest);
    BaseResponse getInfoSunat(String numero);
}
