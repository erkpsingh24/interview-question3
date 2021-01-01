package com.example.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 Reply Entity representing the Reply Table in the persistence layer
 */
@Data
@Entity
@Table(name = "TBL_REPLY")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity {

    /**
     Primary key for the Reply object/table
     */
    @Id
    @GeneratedValue
    private long replyId;

    /**
     Author field/column in Reply object/table
     */
    @Column(name = "author")
    private String author;


    /**
     Message field/column in Reply object/table
     */
    @Column(name = "message")
    private String message;

    /**
     QuestionId foreign key field/column in Reply object/table
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;
}
