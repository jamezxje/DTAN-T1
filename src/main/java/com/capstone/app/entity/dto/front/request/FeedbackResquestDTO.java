package com.capstone.app.entity.dto.front.request;

import com.capstone.app.entity.Feedback;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeedbackResquestDTO {

    private Integer feedbackId;
    private Integer feedbackPoint;
    private String feedbackContent;

    public Feedback toEntity() {
        Feedback feedback = new Feedback();
        if (this.feedbackId != null && this.feedbackId > 0) {
            feedback.setFeedbackId(this.feedbackId);
        }
        feedback.setFeedbackPoint(this.feedbackPoint);
        feedback.setFeedbackContent(this.feedbackContent);
        return feedback;
    }
}
