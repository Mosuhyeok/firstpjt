package com.suhyeokeeee.tistory.firstpjt.ctr;


import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsSaveRequestDto;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsUpdateRequestDto;
import com.suhyeokeeee.tistory.firstpjt.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {
    private final PostService postService;    //컨트롤러에서 service 주입받고 사용

    //컨트롤러 단에서 RequestBody를 받아와서 service로 넘겨주기
    @PostMapping("/blog/posts")
    public Posts save(@RequestBody PostsSaveRequestDto postsSaveRequestDto){
        return postService.save(postsSaveRequestDto);
    }

    @PostMapping("/view_cnt")   // 조회수기능
    public void view_increase(@RequestBody Long id){
        postService.viewIncrease(id);
    }


    @PutMapping("/blog/post/{id}")      // 수정기능
    public void update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
         postService.update(id, postsUpdateRequestDto);
    }

    @DeleteMapping("/blog/post/{id}")   //삭제기능
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }
}
