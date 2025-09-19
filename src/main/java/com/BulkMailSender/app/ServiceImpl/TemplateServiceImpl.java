package com.BulkMailSender.app.ServiceImpl;


import com.BulkMailSender.app.Repository.*;
import com.BulkMailSender.app.Service.TemplateService;
import com.BulkMailSender.app.dto.TemplateDTO;
import com.BulkMailSender.app.model.Template;
import com.BulkMailSender.app.uam.CRMUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final CRMUserRepository userRepository;

    private TemplateDTO toDTO(Template t) {
        TemplateDTO dto = new TemplateDTO();
        dto.setId(t.getId());
        dto.setName(t.getName());
        dto.setSubject(t.getSubject());
        dto.setBodyHtml(t.getBodyHtml());
        dto.setBodyText(t.getBodyText());
        dto.setVariables(t.getVariables());
        dto.setCreatedById(t.getCreatedBy() != null ? t.getCreatedBy().getId() : null);
        dto.setCreatedAt(t.getCreatedAt());
        return dto;
    }

    private Template toEntity(TemplateDTO dto) {
        Template t = new Template();
        t.setId(dto.getId());
        t.setName(dto.getName());
        t.setSubject(dto.getSubject());
        t.setBodyHtml(dto.getBodyHtml());
        t.setBodyText(dto.getBodyText());
        t.setVariables(dto.getVariables());
        t.setCreatedBy(dto.getCreatedById() != null ?
                userRepository.findById(dto.getCreatedById()).orElse(null) : null);
        t.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        return t;
    }

    @Override
    public TemplateDTO createTemplate(TemplateDTO dto) {
        return toDTO(templateRepository.save(toEntity(dto)));
    }

    @Override
    public TemplateDTO updateTemplate(Long id, TemplateDTO dto) {
        Template existing = templateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
        existing.setName(dto.getName());
        existing.setSubject(dto.getSubject());
        existing.setBodyHtml(dto.getBodyHtml());
        existing.setBodyText(dto.getBodyText());
        existing.setVariables(dto.getVariables());
        return toDTO(templateRepository.save(existing));
    }

    @Override
    public void deleteTemplate(Long id) {
        templateRepository.deleteById(id);
    }

    @Override
    public TemplateDTO getTemplateById(Long id) {
        return templateRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    @Override
    public List<TemplateDTO> getAllTemplates() {
        return templateRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TemplateDTO> getTemplatesByUser(Long userId) {
        return templateRepository.findByCreatedBy(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }
}

