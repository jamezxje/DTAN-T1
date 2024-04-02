package com.capstone.app.controller.dash;

import com.capstone.app.entity.Member;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/wallet")
@PreAuthorize("hasRole('OWNER')")
@RequiredArgsConstructor
public class WalletController {

    private final MemberService memberService;
    private final TransactionService transactionService;
    @GetMapping("/list")
    public String myWallet(Model model, TransactionFilterRequest request) {
        Member member = RequestUtils.getMemberFromModel(model);
        double balance = memberService.getMemberBalance(member.getMemberId());
        request.setMemberId(member.getMemberId());
        model.addAttribute("transactions", transactionService.findByFilter(request));
        model.addAttribute("filter", request);
        model.addAttribute("balance", balance);
        return "dashboard/wallet/list";
    }

    @PostMapping("/request")
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
            return "redirect:/dashboard/wallet/list";
        } else {
            redirectAttributes.addFlashAttribute("success", "Request successfully");
        }
        return "redirect:/dashboard/wallet/list";
    }
}
