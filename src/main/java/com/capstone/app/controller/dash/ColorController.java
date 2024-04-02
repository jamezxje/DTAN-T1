package com.capstone.app.controller.dash;

import com.capstone.app.entity.Color;
import com.capstone.app.entity.dto.dashboard.request.CategoryRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.CategoryResponseDTO;
import com.capstone.app.service.common.CommonDataService;
import com.capstone.app.service.dash.CategoryService;
import com.capstone.app.service.dash.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/colors")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;
    private final CommonDataService commonDataService;

    @GetMapping({"/", ""})
    public String color(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Color> colors = colorService.findAllColor(pageable);
        model.addAttribute("colors", colors);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", colors.getTotalPages());
        return "dashboard/color/list";
    }

    @GetMapping("/save")
    public String saveCategory(Model model) {
        model.addAttribute("color", new Color());
        return "dashboard/color/form";
    }

    @PostMapping("/save")
    public String saveOrUpdateCategory(@ModelAttribute Color color, Model model) {
        if (color.getColorId() != null) {
            colorService.saveColor(color);
        } else {
            colorService.saveColor(color);
            commonDataService.refreshCategories();
        }
        return "redirect:/dashboard/colors";
    }

    @GetMapping("/update/{colorId}")
    public String updateCategoryByBranId(@PathVariable("colorId") Integer colorId, Model model, RedirectAttributes redirectAttributes) {

        Color color = colorService.findColorbyColorId(colorId);

        if (color == null) {
            redirectAttributes.addFlashAttribute("error", "Category not found");
            return "redirect:/dashboard/colors";
        }

        model.addAttribute("color", color);
        return "dashboard/color/form";
    }

    @GetMapping("/delete/{colorId}")
    public String deleteBranByCategoryId(@PathVariable("colorId") Integer colorId) {
        colorService.deleteColorByColorId(colorId);
        return "redirect:/dashboard/colors";
    }
}
