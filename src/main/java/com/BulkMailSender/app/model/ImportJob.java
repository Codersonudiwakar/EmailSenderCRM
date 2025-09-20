package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.enumdata.ImportSource;
import com.BulkMailSender.app.uam.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "import_jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportJob {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "campaign_id", nullable = false)
	private Campaign campaign;
	@Enumerated(EnumType.STRING)
	private ImportSource source;
	private String sourceUrl;
	private String originalFilePath;
	private Integer totalRows;
	private Integer successCount;
	private Integer failureCount;
	@Column(columnDefinition = "JSON")
	private String errors;
	private String status;
	private Instant createdAt;
	private Instant finishedAt;
}