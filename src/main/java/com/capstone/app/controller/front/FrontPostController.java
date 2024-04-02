package com.capstone.app.controller.front;

import com.capstone.app.entity.dto.common.PostDTO;
import com.capstone.app.service.dash.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class FrontPostController {

    private final PostService postService;

    @GetMapping("/posts/{slug}")
    public String showPostDetails(@PathVariable("slug") String slug, ModelMap map) {
        PostDTO post = postService.getPublishPostDTOByPostSlug(slug);
        if (post == null) {
            return "redirect:/404";
        }
        map.addAttribute("post", post);
        return "frontend/post/post-details";
    }

}
