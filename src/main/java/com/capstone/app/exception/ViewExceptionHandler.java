package com.capstone.app.exception;

import com.capstone.app.config.auth.UserDetailsCustom;
import com.capstone.app.entity.Member;
import com.capstone.app.service.dash.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class ViewExceptionHandler {
    private final IMemberService memberService;
    @ModelAttribute("member")
    public Member member() {
       if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) return null;
        UserDetailsCustom userDetails = (UserDetailsCustom) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getMember();
    }

    @ModelAttribute("memberService")
    public IMemberService memberService() {
        return memberService;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException() {
        return "/auth/error-403";
    }

    @ExceptionHandler({NoResourceFoundException.class, NotFoundException.class})
    public String handleNotFoundException() {
        return "/auth/error-404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "/auth/error-500";
    }
}
