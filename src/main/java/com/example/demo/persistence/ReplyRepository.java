package com.example.demo.persistence;

import com.example.demo.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 Persistence layer to add Replies
 */
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

}
