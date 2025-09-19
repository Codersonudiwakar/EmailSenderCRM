package com.BulkMailSender.app.Service;

import java.util.List;

import com.BulkMailSender.app.dto.EmailLogDTO;
import com.BulkMailSender.app.enumdata.EmailStatus;

public interface EmailLogService {
    EmailLogDTO logEmail(EmailLogDTO dto);
    EmailLogDTO updateEmailStatus(Long id, EmailStatus status, String response, String failureReason);
    List<EmailLogDTO> getLogsByCampaign(Long campaignId);
    List<EmailLogDTO> getLogsByRecipient(Long recipientId);
    EmailLogDTO getEmailLogById(Long id);
}

