package com.example.demo.model.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 Question Entity representing Question object stored in the database
 */
@Data
@Entity
@Table(name = "TBL_QUESTION")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionEntity {

    /**
     Primary Key for Question object/table
     */
    @Id
    @GeneratedValue
    private long questionId;

    /**
     Author field/column in Question object/table
     */
    @Column(name = "author")
    private String author;

    /**
     Message field/column in Question object/table
     */
    @Column(name = "message")
    private String message;

    /**
     Replies field/column in Question object/table
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyEntity> replies;

}
