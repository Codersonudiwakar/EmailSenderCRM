package com.BulkMailSender.app.ServiceImpl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.BulkMailSender.app.Repository.CampaignRepository;
import com.BulkMailSender.app.Service.CampaignService;
import com.BulkMailSender.app.dto.CampaignDTO;
import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.model.Campaign;
import com.BulkMailSender.app.uam.CRMUserRepository;

public class CampaignServiceImpl implements CampaignService{
	
	
	private  CampaignRepository campaignRepository;
    private  CRMUserRepository userRepository;
	

    private CampaignDTO toDTO(Campaign c) {
        CampaignDTO dto = new CampaignDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setStatus(c.getStatus());
        dto.setTimezone(c.getTimezone());
        dto.setScheduledAt(c.getScheduledAt());
        dto.setIsRecurring(c.getIsRecurring());
        dto.setRecurrenceCron(c.getRecurrenceCron());
        dto.setMaxRetries(c.getMaxRetries());
        dto.setCreatedAt(c.getCreatedAt());
        dto.setUpdatedAt(c.getUpdatedAt());
        dto.setCreatedBy(c.getCreatedBy().getId());
        return dto;
    }
    
    private Campaign toEntity(CampaignDTO dto) {
        Campaign c = new Campaign();
        c.setId(dto.getId());
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        c.setStatus(dto.getStatus());
        c.setTimezone(dto.getTimezone());
        c.setScheduledAt(dto.getScheduledAt());
        c.setIsRecurring(dto.getIsRecurring());
        c.setRecurrenceCron(dto.getRecurrenceCron());
        c.setMaxRetries(dto.getMaxRetries());
        c.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        c.setUpdatedAt(Instant.now());
        if (dto.getCreatedBy() != null) {
            c.setCreatedBy(userRepository.findById(dto.getCreatedBy()).orElse(null));
        }
        return c;
    }

	@Override
	public CampaignDTO createCampaign(CampaignDTO dto) {
		Campaign campaign=toEntity(dto);
		campaign=campaignRepository.save(campaign); 
		return toDTO(campaign);
	}

	@Override
	public CampaignDTO updateCampaign(Long id, CampaignDTO dto) {
		// TODO Auto-generated method stub
		Campaign existing = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setStatus(dto.getStatus());
        existing.setTimezone(dto.getTimezone());
        existing.setScheduledAt(dto.getScheduledAt());
        existing.setIsRecurring(dto.getIsRecurring());
        existing.setRecurrenceCron(dto.getRecurrenceCron());
        existing.setMaxRetries(dto.getMaxRetries());
        existing.setUpdatedAt(Instant.now());
        return toDTO(campaignRepository.save(existing));
	}

	@Override
	public void deleteCampaign(Long id) {
		  campaignRepository.deleteById(id);
		
	}

	@Override
	public CampaignDTO getCampaignById(Long id) {
		return campaignRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
	}

	@Override
	public List<CampaignDTO> getAllCampaigns() {
		// TODO Auto-generated method stub
        return campaignRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());

	}

	@Override
	public CampaignDTO scheduleCampaign(Long id, Instant scheduleTime) {
		// TODO Auto-generated method stub
		 Campaign c = campaignRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Campaign not found"));
	        c.setScheduledAt(scheduleTime);
	        c.setStatus(CampaignStatus.SCHEDULED);
	        return toDTO(campaignRepository.save(c));
	}

	@Override
	public CampaignDTO updateStatus(Long id, CampaignStatus status) {
		  Campaign c = campaignRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Campaign not found"));
	        c.setStatus(status);
	        c.setUpdatedAt(Instant.now());
	        return toDTO(campaignRepository.save(c));
	}

}
