package com.capstone.app.controller.dash;


import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.service.dash.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@RequestMapping("/dashboard/members")
@PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final MemberService memberService;

    @GetMapping("/profile")
    public String editProfile(Model model) {
        MemberRequestDTO memberRequestDTO = memberService.findMemberRequestDTO();

        if (memberRequestDTO == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("profile", memberRequestDTO);
        model.addAttribute("today", LocalDateTime.now());
        return "dashboard/member/edit-profile";
    }

    @PostMapping("/profile/save")
    public String editProfile(@ModelAttribute MemberRequestDTO member, RedirectAttributes redirectAttributes) {
        memberService.editMember(member);
        redirectAttributes.addFlashAttribute("success", "Edit profile successfully");
        return "redirect:/dashboard/members/profile";
    }
}
