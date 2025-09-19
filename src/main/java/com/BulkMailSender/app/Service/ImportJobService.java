package com.BulkMailSender.app.Service;

import java.util.List;

import com.BulkMailSender.app.dto.ImportJobDTO;

public interface ImportJobService {
    ImportJobDTO createImportJob(ImportJobDTO dto);
    ImportJobDTO updateImportJob(Long id, ImportJobDTO dto);
    ImportJobDTO markAsCompleted(Long id, int successCount, int failureCount, String errors);
    ImportJobDTO getImportJobById(Long id);
    List<ImportJobDTO> getImportJobsByCampaign(Long campaignId);
}
