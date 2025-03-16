package com.api.supremeAdmision.controller;

import com.api.supremeAdmision.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping
    public ResponseEntity<String> insertDocument(@RequestBody Map<String, String> document) {
        String uuid = document.get("uuid");
        String base64Content = document.get("base64Content");
        
        if (uuid == null || base64Content == null) {
            return ResponseEntity.badRequest().body("UUID and base64Content are required");
        }
        
        String result = documentRepository.insertDocument(uuid, base64Content);
        return ResponseEntity.ok(result);
    }
}
