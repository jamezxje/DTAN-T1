package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "feedback_point", nullable = false)
    @Range(min = 1, max = 5)
    private Integer feedbackPoint;

    @Column(name = "feedback_content",length = 1000)
    @Size(max = 1000)
    private String feedbackContent;

    @Column(name = "feedback_date", nullable = false)
    private LocalDateTime feedbackDate;

    @Column( name = "feedback_updated_at")
    private LocalDateTime feedbackUpdatedAt;
}
