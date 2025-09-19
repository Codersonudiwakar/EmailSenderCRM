package com.BulkMailSender.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BulkMailSender.app.model.Recipient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    List<Recipient> findByCampaignId(Long campaignId);

    @Query("SELECT r FROM Recipient r WHERE r.email = :email AND r.campaign.id = :campaignId")
    Recipient findByEmailAndCampaign(@Param("email") String email,@Param("campaignId") Long campaignId);

    @Query("SELECT COUNT(r) FROM Recipient r WHERE r.campaign.id = :campaignId")
    Long countByCampaign(@Param("campaignId") Long campaignId);

    @Query("SELECT r FROM Recipient r WHERE r.importedFrom.id = :jobId")
    List<Recipient> findByImportJob(@Param("jobId") Long jobId);
}
