package com.capstone.app.controller.dash;

import com.capstone.app.entity.dto.dashboard.request.CategoryRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.CategoryResponseDTO;
import com.capstone.app.service.common.CommonDataService;
import com.capstone.app.service.dash.CategoryService;
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
@RequestMapping("/dashboard/categories")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CommonDataService commonDataService;

    @GetMapping({"/", ""})
    public String category(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<CategoryResponseDTO> categories = categoryService.findAllCategoryResponseDTO(pageable);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categories.getTotalPages());
        return "dashboard/category/list";
    }

    @GetMapping("/save")
    public String saveCategory(Model model) {
        model.addAttribute("category", new CategoryRequestDTO());
        return "dashboard/category/form";
    }

    @PostMapping("/save")
    public String saveOrUpdateCategory(@ModelAttribute CategoryRequestDTO category, Model model) {
        if (category.getCategoryId() != null) {
            categoryService.editCategory(category);
        } else {
            categoryService.saveCategory(category);
            commonDataService.refreshCategories();
        }
        return "redirect:/dashboard/categories";
    }

    @GetMapping("/update/{categoryId}")
    public String updateCategoryByBranId(@PathVariable("categoryId") Integer categoryId, Model model, RedirectAttributes redirectAttributes) {

        CategoryRequestDTO categoryRequestDTO = categoryService.findCategoryDTOById(categoryId);

        if (categoryRequestDTO == null) {
            redirectAttributes.addFlashAttribute("error", "Category not found");
            return "redirect:/dashboard/categories";
        }

        model.addAttribute("category", categoryRequestDTO);
        return "dashboard/category/form";
    }

    @GetMapping("/delete/{categoryId}")
    public String deleteBranByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/dashboard/categories";
    }
}
