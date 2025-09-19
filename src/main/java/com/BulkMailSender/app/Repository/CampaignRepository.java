package com.BulkMailSender.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BulkMailSender.app.enumdata.CampaignStatus;
import com.BulkMailSender.app.model.Campaign;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByStatus(CampaignStatus status);

    @Query("SELECT c FROM Campaign c WHERE c.createdBy.id = :userId")
    List<Campaign> findByCreatedBy(@Param("userId") Long userId);

    @Query("SELECT c FROM Campaign c WHERE c.scheduledAt BETWEEN :start AND :end")
    List<Campaign> findScheduledBetween(@Param("start") Instant start, @Param("end") Instant end);

    @Query("SELECT c FROM Campaign c WHERE c.isRecurring = true")
    List<Campaign> findRecurringCampaigns();
}
