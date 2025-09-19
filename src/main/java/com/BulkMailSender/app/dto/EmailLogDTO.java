package com.BulkMailSender.app.dto;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.EmailStatus;
import com.BulkMailSender.app.model.Campaign;
import com.BulkMailSender.app.model.Recipient;
import com.BulkMailSender.app.model.Template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailLogDTO {
	private Long id;
	private Long campaignId;
	private Long recipientId;
	private Long templateId;
	private EmailStatus status;
	private String providerMessageId;
	private String providerResponse;
	private Integer attempts;
	private Instant lastAttemptAt;
	private Instant queuedAt;
	private Instant sentAt;
	private Instant deliveredAt;
	private Instant openedAt;
	private Instant clickedAt;
	private Instant bouncedAt;
	private String failureReason;
	private Instant createdAt;

}
