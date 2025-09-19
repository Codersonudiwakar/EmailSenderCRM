package com.BulkMailSender.app.ServiceImpl;
import com.BulkMailSender.app.Repository.CampaignRepository;
import com.BulkMailSender.app.Repository.ImportJobRepository;
import com.BulkMailSender.app.Service.ImportJobService;
import com.BulkMailSender.app.dto.ImportJobDTO;
import com.BulkMailSender.app.model.ImportJob;

import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportJobServiceImpl implements ImportJobService {

    private final ImportJobRepository importJobRepository;
    private final CampaignRepository campaignRepository;

    private ImportJobDTO toDTO(ImportJob job) {
        ImportJobDTO dto = new ImportJobDTO();
        dto.setId(job.getId());
        dto.setCampaignId(job.getCampaign().getId());
        dto.setSource(job.getSource());
        dto.setSourceUrl(job.getSourceUrl());
        dto.setOriginalFilePath(job.getOriginalFilePath());
        dto.setTotalRows(job.getTotalRows());
        dto.setSuccessCount(job.getSuccessCount());
        dto.setFailureCount(job.getFailureCount());
        dto.setErrors(job.getErrors());
        dto.setStatus(job.getStatus());
        dto.setCreatedAt(job.getCreatedAt());
        dto.setFinishedAt(job.getFinishedAt());
        return dto;
    }

    private ImportJob toEntity(ImportJobDTO dto) {
        ImportJob job = new ImportJob();
        job.setId(dto.getId());
        job.setCampaign(campaignRepository.findById(dto.getCampaignId()).orElse(null));
        job.setSource(dto.getSource());
        job.setSourceUrl(dto.getSourceUrl());
        job.setOriginalFilePath(dto.getOriginalFilePath());
        job.setTotalRows(dto.getTotalRows());
        job.setSuccessCount(dto.getSuccessCount());
        job.setFailureCount(dto.getFailureCount());
        job.setErrors(dto.getErrors());
        job.setStatus(dto.getStatus());
        job.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        job.setFinishedAt(dto.getFinishedAt());
        return job;
    }

    @Override
    public ImportJobDTO createImportJob(ImportJobDTO dto) {
        return toDTO(importJobRepository.save(toEntity(dto)));
    }

    @Override
    public ImportJobDTO updateImportJob(Long id, ImportJobDTO dto) {
        ImportJob existing = importJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Import job not found"));
        existing.setSource(dto.getSource());
        existing.setSourceUrl(dto.getSourceUrl());
        existing.setOriginalFilePath(dto.getOriginalFilePath());
        existing.setStatus(dto.getStatus());
        existing.setTotalRows(dto.getTotalRows());
        existing.setSuccessCount(dto.getSuccessCount());
        existing.setFailureCount(dto.getFailureCount());
        existing.setErrors(dto.getErrors());
        existing.setFinishedAt(dto.getFinishedAt());
        return toDTO(importJobRepository.save(existing));
    }

    @Override
    public ImportJobDTO markAsCompleted(Long id, int successCount, int failureCount, String errors) {
        ImportJob job = importJobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Import job not found"));
        job.setSuccessCount(successCount);
        job.setFailureCount(failureCount);
        job.setErrors(errors);
        job.setStatus("COMPLETED");
        job.setFinishedAt(Instant.now());
        return toDTO(importJobRepository.save(job));
    }

    @Override
    public ImportJobDTO getImportJobById(Long id) {
        return importJobRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Import job not found"));
    }

    @Override
    public List<ImportJobDTO> getImportJobsByCampaign(Long campaignId) {
        return importJobRepository.findByCampaignId(campaignId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
}

