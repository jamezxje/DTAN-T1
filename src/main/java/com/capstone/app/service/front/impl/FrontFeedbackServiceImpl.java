package com.capstone.app.service.front.impl;

import com.capstone.app.entity.Feedback;
import com.capstone.app.entity.dto.front.request.FeedbackResquestDTO;
import com.capstone.app.service.front.FrontFeedbackService;

public class FrontFeedbackServiceImpl implements FrontFeedbackService {
    @Override
    public Feedback saveFeedback(FeedbackResquestDTO feedbackResquestDTO) {
        Feedback feedback = feedbackResquestDTO.toEntity();
        return feedback;
    }


}
