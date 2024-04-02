package com.capstone.app.repository;

import com.capstone.app.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

}
