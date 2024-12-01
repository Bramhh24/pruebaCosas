package com.bramh.pruebaCosas.Repositories;

import com.bramh.pruebaCosas.Models.PersonaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PersonasApi", url = "http://localhost:8080/api/v1", path = "/persona")
public interface PersonaClient {

    @GetMapping("/{personaId}")
    public ResponseEntity<PersonaDTO> getPersonaId(@PathVariable String personaId);
}
