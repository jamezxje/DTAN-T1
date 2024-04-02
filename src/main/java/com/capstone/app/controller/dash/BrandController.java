package com.capstone.app.controller.dash;

import com.capstone.app.entity.dto.dashboard.request.BrandRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.BrandResponseDTO;
import com.capstone.app.service.common.CommonDataService;
import com.capstone.app.service.dash.BrandService;
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
@RequestMapping("/dashboard/brands")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final CommonDataService commonDataService;

    @GetMapping({"/", ""})
    public String brand(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<BrandResponseDTO> brands = brandService.findAllBrandResponseDTO(pageable);
        model.addAttribute("brands", brands);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", brands.getTotalPages());
        return "dashboard/brand/list";
    }

    @GetMapping("/save")
    public String saveBrand(Model model) {
        model.addAttribute("brand", new BrandRequestDTO());
        return "dashboard/brand/form";
    }

    @PostMapping("/save")
    public String saveOrUpdateBrand(@ModelAttribute BrandRequestDTO brand, Model model) {
        if (brand.getBrandId() != null) {
            brandService.editBrand(brand);
        } else {
            brandService.saveBrand(brand);
            commonDataService.refreshBrands();
        }
        return "redirect:/dashboard/brands";
    }

    @GetMapping("/update/{brandId}")
    public String updateBrandByBranId(@PathVariable("brandId") Integer brandId, Model model, RedirectAttributes redirectAttributes) {

        BrandRequestDTO brandRequestDTO = brandService.findBrandDTOById(brandId);

        if (brandRequestDTO == null) {
            redirectAttributes.addFlashAttribute("error", "Brand not found");
            return "redirect:/dashboard/brands";
        }

        model.addAttribute("brand", brandRequestDTO);
        return "dashboard/brand/form";
    }

    @GetMapping("/delete/{brandId}")
    public String deleteBranByBrandId(@PathVariable("brandId") Integer brandId) {
        brandService.deleteBrandById(brandId);
        return "redirect:/dashboard/brands";
    }
}
