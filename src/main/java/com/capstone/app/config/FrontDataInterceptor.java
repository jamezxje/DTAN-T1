package com.capstone.app.config;

import com.capstone.app.entity.dto.front.response.BrandItemResponseDTO;
import com.capstone.app.entity.dto.front.response.CategoryItemResponseDTO;
import com.capstone.app.entity.dto.front.response.ColorItemResponseDTO;
import com.capstone.app.entity.dto.front.response.SizeItemResponseDTO;
import com.capstone.app.service.common.CommonDataService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FrontDataInterceptor implements HandlerInterceptor {
	private final ApplicationStartupRunner applicationStartupRunner;
	private final CommonDataService commonDataService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		if (modelAndView != null && !request.getRequestURI().startsWith("/dashboard/")) {

			List<BrandItemResponseDTO> brands = commonDataService.getBrands();
			List<CategoryItemResponseDTO> categories = commonDataService.getCategories();
			List<ColorItemResponseDTO> colors = commonDataService.getColors();
			List<SizeItemResponseDTO> sizes = commonDataService.getSizes();

			modelAndView.addObject("brands", brands);
			modelAndView.addObject("categories", categories);
			modelAndView.addObject("colors", colors);
			modelAndView.addObject("sizes", sizes);
			modelAndView.addObject("options", applicationStartupRunner.getOptionMap());
		}
	}
}
