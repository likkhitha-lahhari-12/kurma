package com.fastflyrr.kurma.controllers;

import com.fastflyrr.kurma.dto.PadRequestDto;
import com.fastflyrr.kurma.dto.PadResponseDto;
import com.fastflyrr.kurma.mappers.PadMapper;
import com.fastflyrr.kurma.models.Pad;
import com.fastflyrr.kurma.repositories.PadRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pads")
public class PadController {

    private final PadRepository padRepository;
    private final PadMapper padMapper;

    public PadController(PadRepository padRepository, PadMapper padMapper){
        this.padRepository = padRepository;
        this.padMapper = padMapper;
    }

    //create a pad
    @PostMapping
    public ResponseEntity<PadResponseDto> createPad(
            @Valid @RequestBody PadRequestDto padRequestDto){
        Pad pad= padMapper.toEntity(padRequestDto);
        Pad saved= padRepository.save(pad);
        return ResponseEntity.ok(padMapper.toDto(saved));
    }

    //get all pads
    @GetMapping
    public List<Pad> getAllPads(){
        return padRepository.findAll();
    }

}
