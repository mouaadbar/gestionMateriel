package com.crems.gestionMateriel.Controller;

import com.crems.gestionMateriel.Repository.MaterielRepository;
import com.crems.gestionMateriel.entities.Materiel;
import com.crems.gestionMateriel.exeptions.RessourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MaterielController {

    @Autowired
    MaterielRepository materielRepository;

    @GetMapping("/allMateriel")
    public ResponseEntity<List<Materiel>> allMateriel() {
        return new ResponseEntity<List<Materiel>>(materielRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addMateriel")
    public ResponseEntity<Materiel> addMateriel(@RequestBody Materiel materiel) {
        return new ResponseEntity<Materiel>(materielRepository.save(materiel), HttpStatus.CREATED);
    }

    @GetMapping("/materiel/{id}")
    public ResponseEntity<Materiel> materielById(@PathVariable Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("materie n'existe pas"));
        return ResponseEntity.ok(materiel);
    }

    @PutMapping("/materiel/{id}")
     public ResponseEntity<Materiel> updateMateriel(@PathVariable Long id, @RequestBody Materiel materielDetails){
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("materie n'existe pas"));
          materiel.setName(materielDetails.getName());
          materiel.setReference((materielDetails.getReference()));
          materiel.setQuantite(materielDetails.getQuantite());
          Materiel updateMateriel = materielRepository.save(materiel);

          return ResponseEntity.ok(updateMateriel);

    }



}