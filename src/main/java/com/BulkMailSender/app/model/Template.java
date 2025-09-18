package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.uam.User;

import jakarta.persistence.*;

@Entity
@Table(name = "templates")
public class Template {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String subject;
	@Column(columnDefinition = "TEXT")
	private String bodyHtml;
	@Column(columnDefinition = "TEXT")
	private String bodyText;
	@Column(columnDefinition = "jsonb")
	private String variables;
	@ManyToOne
	private User createdBy;
	private Instant createdAt;
}