package com.capstone.app.controller.front;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.entity.dto.filter.TransactionFilterRequest;
import com.capstone.app.entity.type.TransactionType;
import com.capstone.app.service.dash.MemberService;
import com.capstone.app.service.dash.TransactionService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class FrontMemberController {

    private final MemberService memberService;
    private final TransactionService transactionService;

    @GetMapping("/profile")
    public String editProfile(Model model, RedirectAttributes redirectAttributes) {

        MemberRequestDTO memberRequestDTO = memberService.findMemberRequestDTO();

        if (memberRequestDTO == null) {
            redirectAttributes.addFlashAttribute("error", "User not found");
            return "redirect:/";
        }

        model.addAttribute("member", memberService.findMemberRequestDTO());
        model.addAttribute("today", LocalDateTime.now());
        return "frontend/member/edit-profile";
    }

    @PostMapping("/profile/save")
    public String editProfile(@ModelAttribute MemberRequestDTO member, RedirectAttributes redirectAttributes) {
        memberService.editMember(member);
        redirectAttributes.addFlashAttribute("success", "Edit profile successfully");
        return "redirect:/profile";
    }

    @GetMapping("/wallet")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'OWNER')")
    public String myWallet(Model model, TransactionFilterRequest request) {
        Member member = RequestUtils.getMemberFromModel(model);
        double balance = memberService.getMemberBalance(member.getMemberId());
        request.setMemberId(member.getMemberId());
        model.addAttribute("transactions", transactionService.findByFilter(request));
        model.addAttribute("filter", request);
        model.addAttribute("balance", balance);
        return "frontend/member/wallet";
    }

    @PostMapping("/wallet/request")
    public String requestWithdraw(double amount, boolean isDeposit, Model model, RedirectAttributes redirectAttributes) {
        Member member = RequestUtils.getMemberFromModel(model);
        boolean success;
        if (isDeposit) {
            success = transactionService.createRequestTransaction(member, amount, TransactionType.REQUEST_IN);
        } else {
            success = transactionService.createRequestTransaction(member, amount, TransactionType.REQUEST_OUT);
        }

        if (!success) {
            redirectAttributes.addFlashAttribute("error", "Not enough money");
            return "redirect:/wallet";
        } else {
            redirectAttributes.addFlashAttribute("success", "Request successfully");
        }
        return "redirect:/wallet";
    }
}
