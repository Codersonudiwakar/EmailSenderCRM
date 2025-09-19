package com.BulkMailSender.app.Service;

import java.util.List;

import com.BulkMailSender.app.dto.RecipientDTO;

public interface RecipientService {
    RecipientDTO addRecipient(RecipientDTO dto);
    List<RecipientDTO> addRecipientsBulk(List<RecipientDTO> recipients);
    RecipientDTO updateRecipient(Long id, RecipientDTO dto);
    void deleteRecipient(Long id);
    RecipientDTO getRecipientById(Long id);
    List<RecipientDTO> getRecipientsByCampaign(Long campaignId);
}

