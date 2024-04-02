package com.capstone.app.controller.dash;

import com.capstone.app.entity.dto.dashboard.request.ProductRequestDTO;
import com.capstone.app.entity.dto.filter.ProductFilterRequest;
import com.capstone.app.entity.dto.front.response.ProductItemResponseDTO;
import com.capstone.app.entity.dto.front.response.RatingDTO;
import com.capstone.app.entity.dto.front.response.RatingDetailResponseDTO;
import com.capstone.app.entity.dto.pagination.PaginationRequest;
import com.capstone.app.entity.dto.pagination.PaginationResponse;
import com.capstone.app.entity.type.FuelType;
import com.capstone.app.entity.type.Status;
import com.capstone.app.entity.type.TransmissionType;
import com.capstone.app.service.dash.*;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/products")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ColorService colorService;
    private final SizeService sizeService;

//    @GetMapping("/{productId}/feedbacks")
//    public String productFeedbacks(Model model, @PathVariable("productId") Integer productId, PaginationRequest request) {
//        Integer memberId = RequestUtils.getMemberFromModel(model).getMemberId();
//        ProductItemResponseDTO productItem = productService.findProductItemByProductId(memberId, productId);
//
//        RatingDTO totalRatingAndAvgRating = productService.findTotalRatingAndAvgRatingByProductId(productId);
//
//        PaginationResponse<List<RatingDetailResponseDTO>> ratingDetailResponseDTOList = productService.findAllRatingByProductId(productId,request);
//
//        model.addAttribute("product", productItem);
//        model.addAttribute("filter", request);
//        model.addAttribute("ratingInfo", totalRatingAndAvgRating);
//        model.addAttribute("ratings", ratingDetailResponseDTOList);
//
//        return "/dashboard/product/feedbacks";
//    }

    @GetMapping()
    public String product(Model model, ProductFilterRequest request) {
        model.addAttribute("status", Status.values());
        model.addAttribute("brands", brandService.findAllBrandItemResponseDTO());
        model.addAttribute("categories", categoryService.findAllCategoryItemResponseDTO());
        model.addAttribute("colors", colorService.findAllColorItemResponseDTO());
        model.addAttribute("sizes", sizeService.findAllSizeItemResponseDTO());
        model.addAttribute("products", productService.findByFilter(request));
        model.addAttribute("filter", request);
        return "/dashboard/product/list";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product",
                ProductRequestDTO.builder().categoryId(1)
                        .productName("Product")
                        .basePrice(100.0)
                        .quantity(100)
                        .deposit(100.0)
                        .description("Very beautiful sir").build()
        );
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("colors", colorService.findAllColor());
        model.addAttribute("sizes", sizeService.findAllSize());
        return "/dashboard/product/form";
    }

    @PostMapping("/save")
    public String createProduct(ProductRequestDTO product, Model model, RedirectAttributes redirectAttributes) {
        if (product.getProductId() != null) {
            productService.editProduct(product);
            redirectAttributes.addFlashAttribute("success", "Product edited successfully");
            return "redirect:/dashboard/products/" + product.getProductId();
        } else {
            productService.addProduct(product);
            redirectAttributes.addFlashAttribute("success", "Added product successfully");
            return "redirect:/dashboard/products/add";
        }

    }

    @GetMapping({"/{productId}/details", "/{productId}", "/{productId}/"})
    public String editProduct(Model model, @PathVariable("productId") Integer productId) {

//        RatingDTO totalRatingAndAvgRating = productService.findTotalRatingAndAvgRatingByProductId(productId);

//        model.addAttribute("ratingInfo", totalRatingAndAvgRating);

        model.addAttribute("JS_FILE", "dashboard/product/form.js");
        model.addAttribute("categories", categoryService.findAllCategory());
        model.addAttribute("colors", colorService.findAllColor());
        model.addAttribute("sizes", sizeService.findAllSize());
        model.addAttribute("product", productService.findById(productId));
        return "/dashboard/product/form";
    }

    @GetMapping("/cancel/{productId}")
    public String deleteProductById(@PathVariable("productId") Integer productId, RedirectAttributes redirectAttributes) {
        productService.changeStatus(productId, Status.CANCELLED);
        redirectAttributes.addFlashAttribute("success", "Cancel product successfully");
        return "redirect:/dashboard/products";
    }
}
