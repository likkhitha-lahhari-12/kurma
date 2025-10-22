package com.fastflyrr.kurma.app.api.controllers;

import com.fastflyrr.kurma.app.api.dto.VertiportRequestDto;
import com.fastflyrr.kurma.app.api.dto.VertiportResponseDto;
import com.fastflyrr.kurma.app.services.VertiportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vertiports")
public class VertiportController {
    private final VertiportService vertiportService;


    public VertiportController(VertiportService vertiportService) {
        this.vertiportService = vertiportService;
    }

    @PostMapping("/add")
    public VertiportResponseDto createVertiport(@RequestBody VertiportRequestDto dto){
        return vertiportService.createVertiport(dto);
    }

    @GetMapping("/{id}")
    public VertiportResponseDto getVertiportById(@PathVariable String id){
        return vertiportService.getVertiportById(id);
    }

    @GetMapping
    public List<VertiportResponseDto> getAllVertiports(){
        return vertiportService.getAllVertiports();
    }

    @PutMapping("/{id}")
    public VertiportResponseDto updateVertiport(@PathVariable String id, @RequestBody VertiportRequestDto dto){
        return vertiportService.updateVertiport(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteVertiport(@PathVariable String id){
        vertiportService.deleteVertiport(id);
    }

}
