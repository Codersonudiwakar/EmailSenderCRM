package com.BulkMailSender.app.controller;


import com.BulkMailSender.app.Service.EmailLogService;
import com.BulkMailSender.app.dto.EmailLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email-logs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EmailLogController {

    private final EmailLogService emailLogService;

    @PostMapping
    public EmailLogDTO create(@RequestBody EmailLogDTO dto) {
        return emailLogService.logEmail(dto);
    }

    @GetMapping("/{id}")
    public EmailLogDTO getById(@PathVariable Long id) {
        return emailLogService.getEmailLogById(id);
    }

//    @GetMapping
//    public List<EmailLogDTO> getAll() {
//        return emailLogService.get
//    }

    @PutMapping("/{id}")
    public EmailLogDTO update(@PathVariable Long id, @RequestBody EmailLogDTO dto) {
        return emailLogService.updateEmailStatus(id, dto.getStatus(),dto.getProviderResponse(), dto.getFailureReason());
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        emailLogService.del
//    }

    // 📌 Business Endpoints
    @GetMapping("/campaign/{campaignId}")
    public List<EmailLogDTO> getByCampaign(@PathVariable Long campaignId) {
        return emailLogService.getLogsByCampaign(campaignId);
    }

    @GetMapping("/recipient/{recipientId}")
    public List<EmailLogDTO> getByRecipient(@PathVariable Long recipientId) {
        return emailLogService.getLogsByRecipient(recipientId);
    }
}

