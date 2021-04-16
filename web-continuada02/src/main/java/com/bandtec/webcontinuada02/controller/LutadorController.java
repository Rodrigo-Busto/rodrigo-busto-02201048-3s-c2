package com.bandtec.webcontinuada02.controller;

import com.bandtec.webcontinuada02.dominio.Golpe;
import com.bandtec.webcontinuada02.dominio.Lutador;
import com.bandtec.webcontinuada02.repository.LutadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository lutadorRepository;

    @PostMapping("/")
    public ResponseEntity postLutador(@RequestBody @Valid Lutador novoLutador){
        lutadorRepository.save(novoLutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/")
    public ResponseEntity getLutadores(){
        return ResponseEntity.status(200).body( lutadorRepository.findByOrderByForcaGolpeAsc() );
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getCountVivos(){
        return ResponseEntity.status(200).body( lutadorRepository.countByVivoTrue() );
    }

    @PostMapping("/{id}/concentrar")
    public ResponseEntity postConcentracao(@PathVariable Integer id){
        if (!lutadorRepository.existsById(id)){
            return ResponseEntity.status(404).build();
        }else{
            Lutador lutador = lutadorRepository.findById(id).get();
            try{
                lutador.concentrar();
            }catch (RuntimeException ex){
                return ResponseEntity.status(400).body(ex.getMessage());
            }
            lutadorRepository.save(lutador);
        }
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/golpe")
    public ResponseEntity postGolpe(@RequestBody @Valid Golpe novoGolpe){
        Lutador lutadorApanha;
        Lutador lutadorBate;
        if (!lutadorRepository.existsById( novoGolpe.getIdLutadorBate() ) ||
                !lutadorRepository.existsById( novoGolpe.getIdLutadorApanha() )){
            return ResponseEntity.status(404).build();
        }else {
            lutadorApanha = lutadorRepository.findById( novoGolpe.getIdLutadorApanha() ).get();
            lutadorBate = lutadorRepository.findById( novoGolpe.getIdLutadorBate() ).get();

            if (!lutadorApanha.isVivo() || !lutadorBate.isVivo()){
                return ResponseEntity.status(400).body("Ambos os lutadores devem estar vivos!");
            }else{
                lutadorApanha.apanha( lutadorBate.getForcaGolpe() );
                lutadorRepository.save( lutadorApanha );
            }
        }
        return ResponseEntity.status(201).body(new Lutador[]{lutadorApanha, lutadorBate});
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos(){
        return ResponseEntity.status(200).body( lutadorRepository.findByVivoFalse() );
    }
}
