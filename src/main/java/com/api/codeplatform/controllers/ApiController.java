package com.api.codeplatform.controllers;

import com.api.codeplatform.model.PieceOfCode;
import com.api.codeplatform.services.PieceOfCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final PieceOfCodeService pieceOfCodeService;

    @Autowired
    public ApiController(PieceOfCodeService pieceOfCodeService) {
        this.pieceOfCodeService = pieceOfCodeService;
    }

    @GetMapping("/code/{id}")
    public PieceOfCode getCodeById(@PathVariable UUID id) {
        return pieceOfCodeService.getCodeById(id);
    }

    @GetMapping("/code/latest")
    public List<PieceOfCode> getLatestCodes() {
        return pieceOfCodeService.getLatest();
    }

    @PostMapping("/code/new")
    public ResponseEntity<Object> postNewCode(@RequestBody PieceOfCode pieceOfCode) {
        UUID id = this.pieceOfCodeService.addCode(pieceOfCode);
        Map<String, String> body = new HashMap<>();
        body.put("id", id.toString());

        return new ResponseEntity<Object>(body, HttpStatus.OK);
    }
}
