package com.capstone.app.controller.dash;

import com.capstone.app.entity.Member;
import com.capstone.app.entity.Post;
import com.capstone.app.entity.dto.common.PostDTO;
import com.capstone.app.entity.dto.dashboard.request.PostRequestDTO;
import com.capstone.app.entity.dto.dashboard.request.PostFilterRequest;
import com.capstone.app.entity.type.PostStatus;
import com.capstone.app.service.dash.PostService;
import com.capstone.app.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/dashboard/posts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class PostController {

    private final PostService postService;

    @GetMapping({"", "/"})
    public String showPublishPostsPage(ModelMap map, PostFilterRequest request) {
        if (request.getPostStatus() == null) {
            request.setPostStatus(PostStatus.PUBLISH);
        }

        map.addAttribute("status", PostStatus.values());
        map.addAttribute("postItemList", postService.findPostsByFilter(request));
        map.addAttribute("filter", request);
        return "dashboard/post/list";
    }

    @GetMapping({"/add", "/add/"})
    public String showAddPostPage(ModelMap map) {
        map.addAttribute("post", new PostDTO());
        map.addAttribute("postStatus", PostStatus.values());
        return "dashboard/post/form";
    }

    @PostMapping({"/save", "/save/"})
    public String savePost(PostRequestDTO postRequestDTO, Model model, RedirectAttributes redirectAttributes) {
        Member member = RequestUtils.getMemberFromModel(model);
        Post result;
        if (postRequestDTO.getPostId() != null) {
            result = postService.updatePost(postRequestDTO);
            redirectAttributes.addFlashAttribute("success", "Post Edited successfully");
            return "redirect:/dashboard/posts/" + result.getPostId();
        } else {
            result = postService.savePost(postRequestDTO,member);
            redirectAttributes.addFlashAttribute("success", "Post saved successfully");
            return "redirect:/dashboard/posts/add";
        }
    }

    @GetMapping({"/{postId}"})
    public String showEditPostPage(ModelMap map, @PathVariable("postId") Integer postId) {
        PostDTO post = postService.getPostDTOByPostId(postId);
        map.addAttribute("postStatus", PostStatus.values());
        map.addAttribute("post", post );
        return "dashboard/post/form";
    }

    @GetMapping({"/status"})
    public String changeStatus(Integer id, PostStatus status, RedirectAttributes redirectAttributes) {
        postService.changeStatus(id, status);
        redirectAttributes.addFlashAttribute("success", "Post " + status.getStatus() + " successfully");
        return "redirect:/dashboard/posts";
    }

    @GetMapping({"/delete"})
    public String deletePost(Integer id, RedirectAttributes redirectAttributes) {
        postService.deletePost(id);
        redirectAttributes.addFlashAttribute("success", "Post deleted successfully");
        return "redirect:/dashboard/posts";
    }
}
