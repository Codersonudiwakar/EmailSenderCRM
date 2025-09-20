package com.BulkMailSender.app.controller;


import com.BulkMailSender.app.Service.RecipientService;
import com.BulkMailSender.app.dto.RecipientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RecipientController {

    private final RecipientService recipientService;

    @PostMapping("/addBulk")
    public List<RecipientDTO> addRecipientsBulk(@RequestBody List<RecipientDTO> dtoList) {
        return recipientService.addRecipientsBulk(dtoList);
    }

    @GetMapping("/{id}")
    public RecipientDTO getById(@PathVariable Long id) {
        return recipientService.getRecipientById(id);
    }

//    @GetMapping
//    public List<RecipientDTO> getAll() {
//        return recipientService.getAllRecipients();
//    }

    @PutMapping("/{id}")
    public RecipientDTO update(@PathVariable Long id, @RequestBody RecipientDTO dto) {
        return recipientService.updateRecipient(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recipientService.deleteRecipient(id);
    }

    // 📌 Business Endpoints
    @GetMapping("/campaign/{campaignId}")
    public List<RecipientDTO> getByCampaign(@PathVariable Long campaignId) {
        return recipientService.getRecipientsByCampaign(campaignId);
    }

//    @GetMapping("/email")
//    public RecipientDTO getByEmail(@RequestParam String email) {
//        return recipientService.getRecipientByEmail(email);
//    }
}

