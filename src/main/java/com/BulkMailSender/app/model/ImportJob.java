package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.ImportSource;

import jakarta.persistence.*;

@Entity
@Table(name = "import_jobs")
public class ImportJob {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Campaign campaign;
	@Enumerated(EnumType.STRING)
	private ImportSource source;
	private String sourceUrl;
	private String originalFilePath;
	private Integer totalRows;
	private Integer successCount;
	private Integer failureCount;
	@Column(columnDefinition = "jsonb")
	private String errors;
	private String status;
	private Instant createdAt;
	private Instant finishedAt;
}