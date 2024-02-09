package com.ejercicio.ejercicio.service.impl;

import com.ejercicio.ejercicio.entity.PersonaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ejercicio.ejercicio.feign.ReniecClient;
import com.ejercicio.ejercicio.service.PersonaService;
import org.springframework.beans.factory.annotation.Value;
import com.ejercicio.ejercicio.repository.PersonaRepository;
import com.ejercicio.ejercicio.agreggates.response.BaseResponse;
import com.ejercicio.ejercicio.agreggates.request.PersonaRequest;
import com.ejercicio.ejercicio.agreggates.response.ReniecResponse;

import static com.ejercicio.ejercicio.agreggates.constants.Constant.BEARER;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.CODE_ERROR;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.CODE_SUCESS;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.CODE_CREATED;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.MESSAGE_ERROR;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.MESSAGE_SUCCESS;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.MESSAGE_CREATED;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    private final PersonaRepository personaRepository;
    private final ReniecClient reniecClient;

    @Value("${token.api}")
    private String tokenApi;

    @Override
    public BaseResponse crearPersona(PersonaRequest personaRequest) {
        System.out.println("num doc "+personaRequest.getNumDoc());
        ReniecResponse reniecResponse = obtenerInformacion(personaRequest.getNumDoc());
        System.out.println("reniecResponse = " + reniecResponse);

        if (Objects.isNull(reniecResponse)) {
            return BaseResponse.builder()
                    .code(CODE_ERROR)
                    .message(MESSAGE_ERROR)
                    .data(Optional.empty())
                    .build();
        }

        PersonaEntity persona = PersonaEntity.builder()
                .numDocu(reniecResponse.getNumeroDocumento())
                .nombres(reniecResponse.getNombres())
                .apePat(reniecResponse.getApellidoPaterno())
                .apeMat(reniecResponse.getApellidoMaterno())
                .estado(1)
                .usuaCrea("USER")
                .dateCreate(Timestamp.from(Instant.now()))
                .build();

        PersonaEntity personaEntity = personaRepository.save(persona);

        return BaseResponse.builder()
                .code(CODE_CREATED)
                .message(MESSAGE_CREATED)
                .data(Optional.of(personaEntity))
                .build();
    }

    @Override
    public BaseResponse getInfoReniec(String numero) {
        ReniecResponse response = obtenerInformacion(numero);

        if (Objects.isNull(response)) {
            return BaseResponse.builder()
                    .code(CODE_ERROR)
                    .message(MESSAGE_ERROR)
                    .data(Optional.empty())
                    .build();
        }

        return BaseResponse.builder()
                .code(CODE_SUCESS)
                .message(MESSAGE_SUCCESS)
                .data(Optional.of(response))
                .build();
    }

    private ReniecResponse obtenerInformacion(String numero) {
        String Authorization = BEARER.concat(tokenApi);
        return reniecClient.obtenerInformacion(numero, Authorization);
    }
}
