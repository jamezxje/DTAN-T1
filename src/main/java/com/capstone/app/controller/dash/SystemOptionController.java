package com.capstone.app.controller.dash;

import com.capstone.app.entity.SystemOption;
import com.capstone.app.service.common.SystemOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard/setting")
@RequiredArgsConstructor
public class SystemOptionController {

    private final SystemOptionService systemOptionService;

    // List all system options
    @GetMapping({"/", ""})
    public String getAll(Model model) {
        List<SystemOption> systemOptions = systemOptionService.getAllSystemOption();
        model.addAttribute("systemOptions", systemOptions);
        return "dashboard/setting/form";
    }

    // Update system options
    @PostMapping("/update")
    public String updateSystemOptions(@RequestParam Map<String, String> formParams) {
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            String optionKey = entry.getKey();
            String optionValue = entry.getValue();
            systemOptionService.updateSystemOption(optionKey, optionValue);
        }
        return "redirect:/dashboard/setting";
    }

}
