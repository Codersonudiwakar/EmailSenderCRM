package com.BulkMailSender.app.ServiceImpl;


import com.BulkMailSender.app.Repository.CampaignRepository;
import com.BulkMailSender.app.Repository.EmailLogRepository;
import com.BulkMailSender.app.Repository.RecipientRepository;
import com.BulkMailSender.app.Repository.TemplateRepository;
import com.BulkMailSender.app.Service.EmailLogService;
import com.BulkMailSender.app.dto.EmailLogDTO;
import com.BulkMailSender.app.enumdata.EmailStatus;
import com.BulkMailSender.app.model.EmailLog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailLogServiceImpl implements EmailLogService {

    private final EmailLogRepository emailLogRepository;
    private final CampaignRepository campaignRepository;
    private final RecipientRepository recipientRepository;
    private final TemplateRepository templateRepository;

    private EmailLogDTO toDTO(EmailLog e) {
        EmailLogDTO dto = new EmailLogDTO();
        dto.setId(e.getId());
        
        dto.setCampaignId(e.getCampaign().getId());
        dto.setRecipientId(e.getRecipient().getId());
        dto.setTemplateId(e.getTemplate().getId());
        dto.setStatus(e.getStatus());
        dto.setProviderMessageId(e.getProviderMessageId());
        dto.setProviderResponse(e.getProviderResponse());
        dto.setAttempts(e.getAttempts());
        dto.setLastAttemptAt(e.getLastAttemptAt());
        dto.setQueuedAt(e.getQueuedAt());
        dto.setSentAt(e.getSentAt());
        dto.setDeliveredAt(e.getDeliveredAt());
        dto.setOpenedAt(e.getOpenedAt());
        dto.setClickedAt(e.getClickedAt());
        dto.setBouncedAt(e.getBouncedAt());
        dto.setFailureReason(e.getFailureReason());
        dto.setCreatedAt(e.getCreatedAt());
        return dto;
    }

    private EmailLog toEntity(EmailLogDTO dto) {
        EmailLog e = new EmailLog();
        e.setId(dto.getId());
        e.setCampaign(campaignRepository.findById(dto.getCampaignId()).orElse(null));
        e.setRecipient(recipientRepository.findById(dto.getRecipientId()).orElse(null));
        e.setTemplate(templateRepository.findById(dto.getTemplateId()).orElse(null));
        e.setStatus(dto.getStatus());
        e.setProviderMessageId(dto.getProviderMessageId());
        e.setProviderResponse(dto.getProviderResponse());
        e.setAttempts(dto.getAttempts());
        e.setLastAttemptAt(dto.getLastAttemptAt());
        e.setQueuedAt(dto.getQueuedAt());
        e.setSentAt(dto.getSentAt());
        e.setDeliveredAt(dto.getDeliveredAt());
        e.setOpenedAt(dto.getOpenedAt());
        e.setClickedAt(dto.getClickedAt());
        e.setBouncedAt(dto.getBouncedAt());
        e.setFailureReason(dto.getFailureReason());
        e.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        return e;
    }

    @Override
    public EmailLogDTO logEmail(EmailLogDTO dto) {
        EmailLog e = toEntity(dto);
        return toDTO(emailLogRepository.save(e));
    }

    @Override
    public EmailLogDTO updateEmailStatus(Long id, EmailStatus status, String response, String failureReason) {
        EmailLog e = emailLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmailLog not found"));
        e.setStatus(status);
        e.setProviderResponse(response);
        e.setFailureReason(failureReason);
        e.setLastAttemptAt(Instant.now());
        return toDTO(emailLogRepository.save(e));
    }

    @Override
    public List<EmailLogDTO> getLogsByCampaign(Long campaignId) {
        return emailLogRepository.findByCampaignId(campaignId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<EmailLogDTO> getLogsByRecipient(Long recipientId) {
        return emailLogRepository.findByRecipientId(recipientId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmailLogDTO getEmailLogById(Long id) {
        return emailLogRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("EmailLog not found"));
    }
}
