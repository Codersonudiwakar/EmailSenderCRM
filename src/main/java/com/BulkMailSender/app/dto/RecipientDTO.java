package com.BulkMailSender.app.dto;

import java.time.Instant;

import com.BulkMailSender.app.model.Campaign;
import com.BulkMailSender.app.model.ImportJob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipientDTO {
	private Long id;
	private Long campaignId;
	private String email;
	private String fullName;
	private String metadata;
	private Long importedFromId;
	private Instant createdAt;
	
	

}
