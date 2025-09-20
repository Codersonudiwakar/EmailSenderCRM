package com.BulkMailSender.app.controller;


import com.BulkMailSender.app.Service.ImportJobService;
import com.BulkMailSender.app.dto.ImportJobDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/import-jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ImportJobController {

    private final ImportJobService importJobService;

    @PostMapping
    public ImportJobDTO create(@RequestBody ImportJobDTO dto) {
        return importJobService.createImportJob(dto);
    }

    @GetMapping("/{id}")
    public ImportJobDTO getById(@PathVariable Long id) {
        return importJobService.getImportJobById(id);
    }

//    @GetMapping
//    public List<ImportJobDTO> getAll() {
//        return importJobService.get
//    }

    @PutMapping("/{id}")
    public ImportJobDTO update(@PathVariable Long id, @RequestBody ImportJobDTO dto) {
        return importJobService.updateImportJob(id, dto);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        importJobService.deleteImportJob(id);
//    }

    // 📌 Business Endpoints
    @GetMapping("/campaign/{campaignId}")
    public List<ImportJobDTO> getByCampaign(@PathVariable Long campaignId) {
        return importJobService.getImportJobsByCampaign(campaignId);
    }
//
//    @PutMapping("/{id}/status")
//    public ImportJobDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
//        return importJobService.updateImportJobStatus(id, status);
//    }
}
