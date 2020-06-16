package com.suhyeokeeee.tistory.firstpjt.ctr;

import com.suhyeokeeee.tistory.firstpjt.domain.reply.RepliesRepository;
import com.suhyeokeeee.tistory.firstpjt.service.post.PostService;
import com.suhyeokeeee.tistory.firstpjt.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final ReplyService replyService;
    private final PostService postService;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",postService.findAllDesc());
        return "index";
    }

    @GetMapping("/post")
    public String post(){
        return "post-save";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("posts",postService.findById(id));
        model.addAttribute("reply",replyService.findAllByPostId(id));
        return "detail";
    }


    @GetMapping("/blog/update/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("posts",postService.findById(id));
        return "post-update";
    }
}
