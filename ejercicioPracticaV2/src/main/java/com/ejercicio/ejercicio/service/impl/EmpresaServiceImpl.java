package com.ejercicio.ejercicio.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ejercicio.ejercicio.feign.SunatClient;
import com.ejercicio.ejercicio.entity.EmpresaEntity;
import com.ejercicio.ejercicio.service.EmpresaService;
import org.springframework.beans.factory.annotation.Value;
import com.ejercicio.ejercicio.repository.EmpresaRepository;
import com.ejercicio.ejercicio.agreggates.request.EmpresaRequest;
import com.ejercicio.ejercicio.agreggates.response.BaseResponse;
import com.ejercicio.ejercicio.agreggates.response.SunatResponse;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import static com.ejercicio.ejercicio.agreggates.constants.Constant.BEARER;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.CODE_ERROR;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.CODE_SUCESS;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.CODE_CREATED;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.MESSAGE_ERROR;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.MESSAGE_SUCCESS;
import static com.ejercicio.ejercicio.agreggates.constants.Constant.MESSAGE_CREATED;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final SunatClient sunatClient;

    @Value("${token.api}")
    private String token;

    @Override
    public BaseResponse crearEmpresa(EmpresaRequest empresaRequest) {
//        SunatResponse sunatResponse=getInfoEmpresa(empresaRequest.getNumDocu());
//        if (Objects.isNull(sunatResponse)) {
//            return BaseResponse.builder()
//                    .code(CODE_ERROR)
//                    .message(MESSAGE_ERROR)
//                    .data(Optional.empty())
//                    .build();
//        }
//
//        EmpresaEntity empresa = EmpresaEntity.builder()
//                .razonSocial(sunatResponse.getRazonSocial())
//                .tipoDocumento(sunatResponse.getTipoDocumento())
//                .numeroDocumento(sunatResponse.getNumeroDocumento())
//                .estado(sunatResponse.getEstado())
//                .condicion(sunatResponse.getCondicion())
//                .direccion(sunatResponse.getDireccion())
//                .ubigeo(sunatResponse.getUbigeo())
//                .viaTipo(sunatResponse.getViaTipo())
//                .viaNombre(sunatResponse.getViaNombre())
//                .zonaCodigo(sunatResponse.getZonaCodigo())
//                .zonaTipo(sunatResponse.getZonaTipo())
//                .numero(sunatResponse.getNumero())
//                .interior(sunatResponse.getInterior())
//                .lote(sunatResponse.getLote())
//                .dpto(sunatResponse.getDpto())
//                .manzana(sunatResponse.getManzana())
//                .kilometro(sunatResponse.getKilometro())
//                .distrito(sunatResponse.getDistrito())
//                .provincia(sunatResponse.getProvincia())
//                .departamento(sunatResponse.getDepartamento())
//                .EsAgenteRetencion(sunatResponse.getEsAgenteRetencion())
//                .usuaCrea("USER")
//                .dateCreate(Timestamp.from(Instant.now()))
//                .build();
//
//        EmpresaEntity empresaEntity = empresaRepository.save(empresa);
//
//        return BaseResponse.builder()
//                .code(CODE_CREATED)
//                .message(MESSAGE_CREATED)
//                .data(Optional.of(empresaEntity))
//                .build();
        return null;
    }

    @Override
    public BaseResponse getInfoSunat(String numero) {
        SunatResponse sunatResponse = getInfoEmpresa(numero);

        if (Objects.isNull(sunatResponse)) {
            return BaseResponse.builder()
                    .code(CODE_ERROR)
                    .message(MESSAGE_ERROR)
                    .data(Optional.empty())
                    .build();
        }

        return BaseResponse.builder()
                .code(CODE_SUCESS)
                .message(MESSAGE_SUCCESS)
                .data(Optional.of(sunatResponse))
                .build();
    }

    private SunatResponse getInfoEmpresa(String numero){
        String Authorization = BEARER.concat(token);
        return sunatClient.obtenerInfoEmpresa(numero, Authorization);
    }
}

