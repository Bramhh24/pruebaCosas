package com.bramh.pruebaCosas.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bramh.pruebaCosas.Models.CosaDTO;
import com.bramh.pruebaCosas.Models.PersonaDTO;
import com.bramh.pruebaCosas.Repositories.PersonaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bramh.pruebaCosas.Exception.CosaNotFoundException;
import com.bramh.pruebaCosas.Models.Cosa;
import com.bramh.pruebaCosas.Repositories.CosaRepository;
import com.bramh.pruebaCosas.Utils.ApiResponse;
import com.bramh.pruebaCosas.Utils.Message;

@Service
public class CosaServiceImp {

    @Autowired
    private CosaRepository cosaRepository;
    @Autowired
    private PersonaClient personaClient;

    public List<CosaDTO> getAllCosas(){
        List<Cosa> cosas = (List<Cosa>) this.cosaRepository.findAll();

        return cosas.stream().map(cosa -> {
            PersonaDTO persona = personaClient.getPersonaId(cosa.getPropietario()).getBody();
            return CosaDTO.fromEntity(cosa, persona);
        }).collect(Collectors.toList());
    }

    public CosaDTO findById(Long id){

        Cosa cosa = findCosaByIdOrThrow(id);
        PersonaDTO persona = personaClient.getPersonaId(cosa.getPropietario()).getBody();
        return CosaDTO.fromEntity(cosa, persona);
    }

    public ApiResponse addCosa(Cosa cosa){
        this.cosaRepository.save(cosa);
        return new ApiResponse(Message.COSA_SAVE_SUCCESSFULLY, HttpStatus.CREATED.value(), HttpStatus.CREATED, LocalDateTime.now());
    }

    public ApiResponse updateCosa(Long id, Cosa cosa){

        Cosa existingCosa = findCosaByIdOrThrow(id);

        existingCosa.setNombre(cosa.getNombre());
        existingCosa.setDescripcion(cosa.getDescripcion());
        existingCosa.setTipo(cosa.getTipo());
        existingCosa.setPropietario(cosa.getPropietario());
        existingCosa.setStatus(cosa.getStatus());

        cosaRepository.save(existingCosa);

        return new ApiResponse(Message.COSA_UPDATE_SUCCESSFULLY, HttpStatus.OK.value(), HttpStatus.OK, LocalDateTime.now());
    }

    public ApiResponse deleteCosa(Long id){

        Cosa cosa = findCosaByIdOrThrow(id);
        cosaRepository.delete(cosa);

        return new ApiResponse(Message.COSA_DELETE_SUCCESSFULLY, HttpStatus.NO_CONTENT.value(),
                HttpStatus.NO_CONTENT, LocalDateTime.now());
    }

    private Cosa findCosaByIdOrThrow(Long id) {
        return cosaRepository.findById(id)
                .orElseThrow(() -> new CosaNotFoundException(
                        Message.COSA_NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND, LocalDateTime.now()));
    }

    public List<Cosa> obtenerCosasPorPropietario(String propietario) {
        return cosaRepository.findByPropietario(propietario);
    }
}
