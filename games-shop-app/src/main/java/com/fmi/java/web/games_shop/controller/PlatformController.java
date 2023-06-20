package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.PlatformDto;
import com.fmi.java.web.games_shop.service.PlatformService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games-shop/platforms")
@CrossOrigin(origins = "http://localhost:4200")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(final PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    List<PlatformDto> getAllPlatforms() {
        return platformService.getAllPlatforms().stream().map(platform -> new PlatformDto(platform.name())).toList();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PlatformDto> createPlatform(@RequestBody final PlatformDto platformDto) {

        return new ResponseEntity<>(platformService.addPlatform(platformDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deletePlatform(@PathVariable final String name) {
        platformService.deletePlatform(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }
}
