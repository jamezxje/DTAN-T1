package com.capstone.app.controller.dash;

import com.capstone.app.entity.Size;
import com.capstone.app.service.common.CommonDataService;
import com.capstone.app.service.dash.SizeService;
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
@RequestMapping("/dashboard/sizes")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;
    private final CommonDataService commonDataService;

    @GetMapping({"/", ""})
    public String size(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Size> sizes = sizeService.findAllSize(pageable);
        model.addAttribute("sizes", sizes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", sizes.getTotalPages());
        return "dashboard/size/list";
    }

    @GetMapping("/save")
    public String saveCategory(Model model) {
        model.addAttribute("size", new Size());
        return "dashboard/size/form";
    }

    @PostMapping("/save")
    public String saveOrUpdateCategory(@ModelAttribute Size size, Model model) {
        if (size.getSizeId() != null) {
            sizeService.saveSize(size);
        } else {
            sizeService.saveSize(size);
            commonDataService.refreshCategories();
        }
        return "redirect:/dashboard/sizes";
    }

    @GetMapping("/update/{sizeId}")
    public String updateCategoryByBranId(@PathVariable("sizeId") Integer sizeId, Model model, RedirectAttributes redirectAttributes) {

        Size size = sizeService.findSizebySizeId(sizeId);

        if (size == null) {
            redirectAttributes.addFlashAttribute("error", "Category not found");
            return "redirect:/dashboard/sizes";
        }

        model.addAttribute("size", size);
        return "dashboard/size/form";
    }

    @GetMapping("/delete/{sizeId}")
    public String deleteBranByCategoryId(@PathVariable("sizeId") Integer sizeId) {
        sizeService.deleteSizeBySizeId(sizeId);
        return "redirect:/dashboard/sizes";
    }
}
