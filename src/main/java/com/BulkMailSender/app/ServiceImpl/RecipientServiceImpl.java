package com.BulkMailSender.app.ServiceImpl;


import com.BulkMailSender.app.Repository.*;
import com.BulkMailSender.app.Service.RecipientService;
import com.BulkMailSender.app.dto.RecipientDTO;
import com.BulkMailSender.app.model.Recipient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipientServiceImpl implements RecipientService {

    private final RecipientRepository recipientRepository;
    private final CampaignRepository campaignRepository;
    private final ImportJobRepository importJobRepository;

    private RecipientDTO toDTO(Recipient r) {
        RecipientDTO dto = new RecipientDTO();
        dto.setId(r.getId());
        dto.setCampaignId(r.getCampaign().getId());
        dto.setEmail(r.getEmail());
        dto.setFullName(r.getFullName());
        dto.setMetadata(r.getMetadata());
        dto.setImportedFromId(r.getImportedFrom() != null ? r.getImportedFrom().getId() : null);
        dto.setCreatedAt(r.getCreatedAt());
        return dto;
    }

    private Recipient toEntity(RecipientDTO dto) {
        Recipient r = new Recipient();
        r.setId(dto.getId());
        r.setCampaign(campaignRepository.findById(dto.getCampaignId()).orElse(null));
        r.setEmail(dto.getEmail());
        r.setFullName(dto.getFullName());
        r.setMetadata(dto.getMetadata());
        r.setImportedFrom(dto.getImportedFromId() != null ?
                importJobRepository.findById(dto.getImportedFromId()).orElse(null) : null);
        r.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        return r;
    }

    @Override
    public RecipientDTO addRecipient(RecipientDTO dto) {
        return toDTO(recipientRepository.save(toEntity(dto)));
    }

    @Override
    public List<RecipientDTO> addRecipientsBulk(List<RecipientDTO> recipients) {
        List<Recipient> entities = recipients.stream().map(this::toEntity).collect(Collectors.toList());
        return recipientRepository.saveAll(entities).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public RecipientDTO updateRecipient(Long id, RecipientDTO dto) {
        Recipient existing = recipientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));
        existing.setEmail(dto.getEmail());
        existing.setFullName(dto.getFullName());
        existing.setMetadata(dto.getMetadata());
        return toDTO(recipientRepository.save(existing));
    }

    @Override
    public void deleteRecipient(Long id) {
        recipientRepository.deleteById(id);
    }

    @Override
    public RecipientDTO getRecipientById(Long id) {
        return recipientRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));
    }

    @Override
    public List<RecipientDTO> getRecipientsByCampaign(Long campaignId) {
        return recipientRepository.findByCampaignId(campaignId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
}
