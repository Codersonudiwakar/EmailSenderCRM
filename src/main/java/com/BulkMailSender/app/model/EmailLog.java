package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.EmailStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "email_logs")
public class EmailLog {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Campaign campaign;
	@ManyToOne
	private Recipient recipient;
	@ManyToOne
	private Template template;
	@Enumerated(EnumType.STRING)
	private EmailStatus status;
	private String providerMessageId;
	@Column(columnDefinition = "jsonb")
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