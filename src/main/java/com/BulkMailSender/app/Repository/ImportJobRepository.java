package com.BulkMailSender.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BulkMailSender.app.enumdata.ImportSource;
import com.BulkMailSender.app.model.ImportJob;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImportJobRepository extends JpaRepository<ImportJob, Long> {

    List<ImportJob> findByCampaignId(Long campaignId);

    List<ImportJob> findBySource(ImportSource source);

    @Query("SELECT i FROM ImportJob i WHERE i.status = 'PENDING'")
    List<ImportJob> findPendingJobs();

    @Query("SELECT i FROM ImportJob i WHERE i.finishedAt IS NULL")
    List<ImportJob> findUnfinishedJobs();

    @Query("SELECT i FROM ImportJob i WHERE i.campaign.id = :campaignId AND i.status = :status")
    List<ImportJob> findByCampaignAndStatus(@Param("campaignId") Long campaignId,@Param("status") String status);
}
