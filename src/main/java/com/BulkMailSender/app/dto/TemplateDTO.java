package com.BulkMailSender.app.dto;

import java.time.Instant;

import com.BulkMailSender.app.uam.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDTO {
	private Long id;
	private String name;
	private String subject;
	private String bodyHtml;
	private String bodyText;
	private String variables;
	private Long createdById;
	private Instant createdAt;
}
