package com.suhyeokeeee.tistory.firstpjt.dto.post;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {  // 응답을 할 때 쓰는 dto임
    private String title;
    private String content;
    private String author;
    private Long id;
    public PostsResponseDto(Posts posts){    // 생성자로 posts를 받아서 뿌려줌줌
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
        this.id = posts.getId();
    }
}
