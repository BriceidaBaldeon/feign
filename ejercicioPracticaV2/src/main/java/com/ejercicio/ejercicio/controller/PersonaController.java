package com.ejercicio.ejercicio.controller;

import lombok.RequiredArgsConstructor;
import com.ejercicio.ejercicio.service.PersonaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ejercicio.ejercicio.agreggates.response.BaseResponse;
import com.ejercicio.ejercicio.agreggates.request.PersonaRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/persona")
public class PersonaController {

    private final PersonaService service;

    @GetMapping("/{numero}")
    public BaseResponse obtenerPersona(@PathVariable String numero) {
        return service.getInfoReniec(numero);
    }

    @PostMapping("/grabar")
    public BaseResponse crearPersona(@RequestBody PersonaRequest personaRequest) {
        System.out.println("personaRequest.getNumDoc() = " + personaRequest.getNumDoc());
        return service.crearPersona(personaRequest);
    }

}
