package com.BulkMailSender.app.controller;


import com.BulkMailSender.app.Service.CampaignService;
import com.BulkMailSender.app.dto.CampaignDTO;
import com.BulkMailSender.app.uam.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CampaignController {

    private final CampaignService campaignService;
    
    @Autowired
    private JwtUtil jwtUtils;
    
    @Autowired
    private HttpServletRequest request;


    @PostMapping("/createCamp")
    public CampaignDTO create(@RequestBody CampaignDTO dto) {
    	dto.setCreatedBy(getUserIdFromRequest());
        return campaignService.createCampaign(dto);
    }

    @GetMapping("/{id}")
    public CampaignDTO getById(@PathVariable Long id) {
        return campaignService.getCampaignById(id);
    }

    @GetMapping
    public List<CampaignDTO> getAll() {
        return campaignService.getAllCampaigns();
    }

    @PutMapping("/{id}")
    public CampaignDTO update(@PathVariable Long id, @RequestBody CampaignDTO dto) {
        return campaignService.updateCampaign(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
    }

    // 📌 Business Endpoints
//    @PutMapping("/{id}/status")
//    public CampaignDTO updateStatus(@PathVariable Long id, @RequestParam String status) {
//        return campaignService.updateCampaignStatus(id, status);
//    }

    @GetMapping("/by-user")
    public List<CampaignDTO> getByUser() {
    	System.out.println(request);
    	List<CampaignDTO> listCamp=campaignService.getAllCampaignsbyUser(getUserIdFromRequest());
        return listCamp;
    }
    
    public Long getUserIdFromRequest() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }
        String token = authHeader.substring(7);
        return jwtUtils.extractClaim(token, claims -> claims.get("userId", Long.class));
    }

}
