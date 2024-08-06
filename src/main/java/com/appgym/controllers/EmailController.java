package com.appgym.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
public class EmailController {
    @PostMapping("/nota-venta")
    public ResponseEntity enviarEmail(@RequestParam("pdf") MultipartFile pdfFile) {
        return ResponseEntity.ok("Archivo PDF recibido correctamente: " + pdfFile.getOriginalFilename());
    }
}
