package com.BulkMailSender.app.Service;

import java.time.Instant;
import java.util.List;

import com.BulkMailSender.app.dto.CampaignDTO;
import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.model.Campaign;

public interface CampaignService {
    CampaignDTO createCampaign(CampaignDTO dto);
    CampaignDTO updateCampaign(Long id, CampaignDTO dto);
    void deleteCampaign(Long id);
    CampaignDTO getCampaignById(Long id);
    List<CampaignDTO> getAllCampaigns();
    CampaignDTO scheduleCampaign(Long id, Instant scheduleTime);
    CampaignDTO updateStatus(Long id, CampaignStatus status);
}

