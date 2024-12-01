package com.bramh.pruebaCosas.Controllers;

import java.net.URI;
import java.util.List;

import com.bramh.pruebaCosas.Models.CosaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bramh.pruebaCosas.Models.Cosa;
import com.bramh.pruebaCosas.Services.CosaServiceImp;

@RestController
@RequestMapping("/cosa")
public class CosaController {

    @Autowired
    private CosaServiceImp cosaServiceImp;

    @GetMapping("/all")
    public ResponseEntity<List<CosaDTO>> getAllCosas(){

        return ResponseEntity.ok(this.cosaServiceImp.getAllCosas());
    }

    @GetMapping("/{idCosa}")
    public ResponseEntity<?> getCosaById(@PathVariable Long idCosa){
        return ResponseEntity.ok(this.cosaServiceImp.findById(idCosa));
    }

    @PostMapping("/addCosa")
    public ResponseEntity<?> addCosa(@RequestBody Cosa cosa){
        this.cosaServiceImp.addCosa(cosa);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{idCosa}")
                        .buildAndExpand(cosa.getIdCosa())
                        .toUri();
        return ResponseEntity.created(location).body(cosa);
    }

    @PutMapping("/update/{idCosa}")
    public ResponseEntity<?> updateCosa(@PathVariable Long idCosa, @RequestBody Cosa cosa){
        return ResponseEntity.ok(this.cosaServiceImp.updateCosa(idCosa, cosa));
    }

    @DeleteMapping("/delete/{idCosa}")
    public ResponseEntity<?> deleteCosa(@PathVariable Long idCosa){
        return ResponseEntity.ok(this.cosaServiceImp.deleteCosa(idCosa));
    }

    @GetMapping("/propietario")
    public List<Cosa> obtenerCosasPorPropietario(@RequestParam("propietario") String propietario) {
        return cosaServiceImp.obtenerCosasPorPropietario(propietario);
    }
}
