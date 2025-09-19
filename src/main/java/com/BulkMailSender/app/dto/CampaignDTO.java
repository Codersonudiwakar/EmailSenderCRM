package com.BulkMailSender.app.dto;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.uam.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CampaignDTO {
	  private Long id;
	    private String name;
	    private String description;
	    private CampaignStatus status;
	    private String timezone;
	    private Instant scheduledAt;
	    private Boolean isRecurring = false;
	    private String recurrenceCron;
	    private Integer maxRetries = 3;
	    private Instant createdAt;
	    private Instant updatedAt;
	    private Long createdBy;
}
