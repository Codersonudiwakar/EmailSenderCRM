package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.enumdata.EmailStatus;
import com.BulkMailSender.app.uam.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private Campaign campaign;

	@ManyToOne
	@JoinColumn(name = "recipient_id", nullable = false)
	private Recipient recipient;

	@ManyToOne
	@JoinColumn(name = "template_id", nullable = false)
	private Template template;
	@Enumerated(EnumType.STRING)
	private EmailStatus status;
	private String providerMessageId;
	@Column(columnDefinition = "JSON")
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