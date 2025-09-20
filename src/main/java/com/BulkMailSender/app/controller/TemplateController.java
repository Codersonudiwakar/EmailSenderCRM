package com.BulkMailSender.app.controller;


import com.BulkMailSender.app.Service.TemplateService;
import com.BulkMailSender.app.dto.TemplateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    public TemplateDTO create(@RequestBody TemplateDTO dto) {
        return templateService.createTemplate(dto);
    }

    @GetMapping("/{id}")
    public TemplateDTO getById(@PathVariable Long id) {
        return templateService.getTemplateById(id);
    }

    @GetMapping
    public List<TemplateDTO> getAll() {
        return templateService.getAllTemplates();
    }

    @PutMapping("/{id}")
    public TemplateDTO update(@PathVariable Long id, @RequestBody TemplateDTO dto) {
        return templateService.updateTemplate(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        templateService.deleteTemplate(id);
    }

    // 📌 Business Endpoints
    @GetMapping("/by-user/{userId}")
    public List<TemplateDTO> getByUser(@PathVariable Long userId) {
        return templateService.getTemplatesByUser(userId);
    }

//    @GetMapping("/search")
//    public List<TemplateDTO> searchByName(@RequestParam String name) {
//        return templateService.
//    }
}
