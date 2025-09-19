package com.BulkMailSender.app.Service;

import java.util.List;

import com.BulkMailSender.app.dto.TemplateDTO;

public interface TemplateService {
    TemplateDTO createTemplate(TemplateDTO dto);
    TemplateDTO updateTemplate(Long id, TemplateDTO dto);
    void deleteTemplate(Long id);
    TemplateDTO getTemplateById(Long id);
    List<TemplateDTO> getAllTemplates();
    List<TemplateDTO> getTemplatesByUser(Long userId);
}

