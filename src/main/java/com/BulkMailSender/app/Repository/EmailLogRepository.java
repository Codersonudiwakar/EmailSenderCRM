package com.BulkMailSender.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BulkMailSender.app.enumdata.EmailStatus;
import com.BulkMailSender.app.model.EmailLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

    List<EmailLog> findByCampaignId(Long campaignId);

    List<EmailLog> findByRecipientId(Long recipientId);

    List<EmailLog> findByStatus(EmailStatus status);

    @Query("SELECT e FROM EmailLog e WHERE e.campaign.id = :campaignId AND e.status = :status")
    List<EmailLog> findByCampaignAndStatus(@Param("campaignId") Long campaignId,@Param("status") EmailStatus status);

    @Query("SELECT COUNT(e) FROM EmailLog e WHERE e.campaign.id = :campaignId AND e.status = 'DELIVERED'")
    Long countDeliveredEmails(@Param("campaignId") Long campaignId);
}
