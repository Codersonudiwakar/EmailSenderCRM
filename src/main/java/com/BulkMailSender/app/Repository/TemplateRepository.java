package com.BulkMailSender.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BulkMailSender.app.model.Template;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    @Query("SELECT t FROM Template t WHERE t.createdBy.id = :userId")
    List<Template> findByCreatedBy(@Param("userId") Long userId);

    List<Template> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT t FROM Template t WHERE t.subject LIKE %:subject%")
    List<Template> findBySubjectLike(@Param("subject") String subject);
}

