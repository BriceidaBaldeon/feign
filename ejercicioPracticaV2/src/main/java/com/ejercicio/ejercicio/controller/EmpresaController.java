package com.ejercicio.ejercicio.controller;

import com.ejercicio.ejercicio.agreggates.response.BaseResponse;
import com.ejercicio.ejercicio.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    @GetMapping("/{numero}")
    public BaseResponse obtenerEmpresa(@PathVariable String numero) {
        return empresaService.getInfoSunat(numero);
    }
}
