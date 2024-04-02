package com.capstone.app.service.front;

import com.capstone.app.entity.Feedback;
import com.capstone.app.entity.dto.front.request.FeedbackResquestDTO;

public interface FrontFeedbackService {

    Feedback saveFeedback(FeedbackResquestDTO feedbackResquestDTO);


}
