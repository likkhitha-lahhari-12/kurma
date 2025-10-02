package com.fastflyrr.kurma.controllers;

import com.fastflyrr.kurma.dto.PadRequestDto;
import com.fastflyrr.kurma.dto.PadResponseDto;
import com.fastflyrr.kurma.mappers.PadMapper;
import com.fastflyrr.kurma.models.Pad;
import com.fastflyrr.kurma.repositories.PadRepository;
import com.fastflyrr.kurma.services.PadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pads")
public class PadController {

    private final PadRepository padRepository;
   // private final PadMapper padMapper;
    private final PadService padService;

    public PadController(PadRepository padRepository, PadMapper padMapper, PadService padService){
        this.padRepository = padRepository;
        //this.padMapper = padMapper;
        this.padService = padService;
    }

    //create a pad
    @PostMapping("/add")
    public ResponseEntity<Pad> createPad(@RequestBody Pad pad){
        Pad createdPad= padService.createPad(pad);
        return ResponseEntity.ok(createdPad);
    }

    //get all pads
    @GetMapping
    public List<Pad> getAllPads(){
        return padRepository.findAll();
    }

}
