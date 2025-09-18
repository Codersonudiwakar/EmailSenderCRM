package com.BulkMailSender.app.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name = "recipients", uniqueConstraints = @UniqueConstraint(columnNames = { "campaign_id", "email" }))
public class Recipient {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Campaign campaign;
	private String email;
	private String fullName;
	@Column(columnDefinition = "jsonb")
	private String metadata;
	@ManyToOne
	private ImportJob importedFrom;
	private Instant createdAt;
}