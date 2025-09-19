package com.BulkMailSender.app.dto;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.ImportSource;
import com.BulkMailSender.app.model.Campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportJobDTO {
	private Long id;
	private Long campaignId;
	private ImportSource source;
	private String sourceUrl;
	private String originalFilePath;
	private Integer totalRows;
	private Integer successCount;
	private Integer failureCount;
	private String errors;
	private String status;
	private Instant createdAt;
	private Instant finishedAt;
}
