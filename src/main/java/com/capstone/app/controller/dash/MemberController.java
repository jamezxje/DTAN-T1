package com.capstone.app.controller.dash;

import com.capstone.app.entity.dto.dashboard.request.MemberRequestDTO;
import com.capstone.app.entity.dto.dashboard.response.MemberResponseDTO;
import com.capstone.app.service.dash.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/members")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class MemberController {

    private final IAdminService adminService;

    @GetMapping({"/", ""})
    public String showMemberListPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum, Model model) {
        int numOfItems = 10;
        Page<MemberResponseDTO> page = adminService.getAllMember(pageNum - 1, numOfItems);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("members", page);
        return "dashboard/member/list";
    }

    @GetMapping("/add")
    public String showAddMemberPage(Model model) {
        model.addAttribute("member", new MemberRequestDTO());
        List<String> roles = List.of("Owner", "Customer", "Admin");
        model.addAttribute("roles", roles);
        return "dashboard/member/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditMemberPage(Model model, @PathVariable(value = "id") Integer id) {
        List<String> roles = List.of("Owner", "Customer", "Admin");
        model.addAttribute("roles", roles);
        model.addAttribute("member", adminService.getMemberByID(id));
        return "dashboard/member/form";
    }

    @PostMapping("/save")
    public String saveMember(MemberRequestDTO memberRequestDTO) {
        if (memberRequestDTO.getMemberId() != null) {
            adminService.updateMember(memberRequestDTO);
        } else {
            adminService.addNewMember(memberRequestDTO);
        }
        return "redirect:/dashboard/members";
    }

    @PostMapping("/changestatus")
    public String activateOrDeactivateMember(@RequestParam(value = "id") Integer id,
                                             @RequestParam(value = "status") Boolean status) {
        adminService.changeMemberActiveStatus(id, status);
        return "redirect:/dashboard/members";
    }


//	@GetMapping("/{id}")
//	public String showDetail(@PathVariable(value = "id") Integer id, Model model){
//		adminService.getMemberByID(id);
//		return "/dashboard/members";
//	}

//    @GetMapping("/delete/{id}")
//    public String deleteMember(@PathVariable(value = "id") Integer id ){
//        adminService.deleteMember(id);
//        return "redirect:/dashboard/members";
//    }

}
