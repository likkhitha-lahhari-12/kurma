package com.fastflyrr.kurma.app.api.controllers;

import com.fastflyrr.kurma.app.api.dto.PadRequestDto;
import com.fastflyrr.kurma.app.api.dto.PadResponseDto;
import com.fastflyrr.kurma.app.services.PadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pads")
public class PadController {

    private final PadService padService;

    public PadController(PadService padService){
        this.padService = padService;
    }

    //create a pad
    @PostMapping("/add")
    public ResponseEntity<PadResponseDto> createPad(@RequestBody PadRequestDto padRequestDto){
        return ResponseEntity.ok(padService.createPad(padRequestDto));
    }

    //get all pads
    @GetMapping
    public ResponseEntity<List<PadResponseDto>> getAllPads(){
        return ResponseEntity.ok(padService.getAllPads());
    }

    // get pad by id
    @GetMapping("/{id}")
    public ResponseEntity<PadResponseDto> getPadById(@PathVariable String id){
        return ResponseEntity.ok(padService.getPadById(id));
    }

    // update pad
    @PutMapping("/{id}")
    public ResponseEntity<PadResponseDto> updatePad(@PathVariable String id, @RequestBody PadRequestDto request){
        return ResponseEntity.ok(padService.updatePad(id, request));
    }

    // delete pad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePad(@PathVariable String id){
        padService.deletePad(id);
        return ResponseEntity.noContent().build();
    }
}
