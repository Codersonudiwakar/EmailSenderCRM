package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.uam.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING) 
    private CampaignStatus status;
    private String timezone;
    private Instant scheduledAt;
    private Boolean isRecurring = false;
    private String recurrenceCron;
    private Integer maxRetries = 3;
    private Instant createdAt;
    private Instant updatedAt;
    @ManyToOne 
    private User createdBy;
}