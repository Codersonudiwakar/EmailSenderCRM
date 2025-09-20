package com.BulkMailSender.app.model;

import java.time.Instant;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.uam.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "recipients", uniqueConstraints = @UniqueConstraint(columnNames = { "campaign_id", "email" }))
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Recipient {
//	@Id
//	@GeneratedValue
//	private Long id;
//	@ManyToOne
//	private Campaign campaign;
//	private String email;
//	private String fullName;
//	@Column(columnDefinition = "jsonb")
//	private String metadata;
//	@ManyToOne
//	private ImportJob importedFrom;
//	private Instant createdAt;
//}

@Entity
@Table(
    name = "recipients",
    uniqueConstraints = @UniqueConstraint(columnNames = { "campaign_id", "email" })
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    private String email;
    private String fullName;

    // Use JSON instead of jsonb for MySQL
    @Column(columnDefinition = "JSON")
    private String metadata;

    @ManyToOne
    @JoinColumn(name = "imported_from_id")
    private ImportJob importedFrom;

    private Instant createdAt;
}
