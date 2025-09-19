package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.uam.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipients", uniqueConstraints = @UniqueConstraint(columnNames = { "campaign_id", "email" }))
@Data
@AllArgsConstructor
@NoArgsConstructor
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