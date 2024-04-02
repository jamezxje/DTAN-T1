package com.capstone.app.util;

import com.capstone.app.entity.Member;
import org.springframework.ui.Model;

public class RequestUtils {

    public static Member getMemberFromModel(Model model) {
        return (Member) model.getAttribute("member");
    }
}
