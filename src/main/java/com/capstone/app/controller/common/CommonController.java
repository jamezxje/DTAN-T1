package com.capstone.app.controller.common;

import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.entity.type.MemberRole;
import com.capstone.app.service.dash.IAdminService;
import com.capstone.app.service.dash.IMemberService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CommonController {

	private final IAdminService adminService;
	private final IMemberService memberService;

	@GetMapping("/login")
	public String showLoginPage(Model model) {
		if(RequestUtils.getMemberFromModel(model) != null) return "redirect:/";
		return "auth/login";
	}

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		if(RequestUtils.getMemberFromModel(model) != null) return "redirect:/";
		model.addAttribute("member", new MemberRequestDTO());
		return "auth/register";
	}

	@GetMapping("/welcome")
	public String redirectWelcomePage() {
		MemberResponseDTO member = adminService.getMemberFromContext();
		if (member.getRole().equals(MemberRole.ROLE_ADMIN) || member.getRole().equals(MemberRole.ROLE_OWNER)) {
			return "redirect:/dashboard";
		} else if (member.getRole().equals(MemberRole.ROLE_CUSTOMER)) {
			return "redirect:/";
		}
		return "redirect:/";
	}

	@PostMapping("/register")
	public ModelAndView registerNewMember(@ModelAttribute MemberRequestDTO memberRequestDTO){
		ModelAndView mav = new ModelAndView();
		memberService.register(memberRequestDTO);
		mav.setViewName("auth/login");
		return mav;
	}

}
