package com.example.admin.content.controller;

import com.example.admin.content.dto.ContentRequest;
import com.example.admin.content.dto.ContentResponse;
import com.example.admin.content.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity createContent(@RequestBody ContentRequest contentRequest) {
        ContentResponse content = contentService.createContent(contentRequest);
        return ResponseEntity.created(URI.create("/contents/" + content.getId())).body(content);
    }

    @GetMapping
    public ResponseEntity<List<ContentResponse>> findAllContents() {
        return ResponseEntity.ok(contentService.findContents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> findContentById(@PathVariable Long id) {
        return ResponseEntity.ok(contentService.findContentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateContent(@PathVariable Long id, @RequestBody ContentRequest contentRequest) {
        contentService.updateContent(id, contentRequest);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
