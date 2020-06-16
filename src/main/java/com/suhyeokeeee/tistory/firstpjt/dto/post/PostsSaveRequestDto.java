package com.suhyeokeeee.tistory.firstpjt.dto.post;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본생성자 왜있어야 할까? vega님 : 없어도 된다.
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author = author;
    }   // Test를 위해서 만든 빌더

    public Posts toEntity(){    //toEntity엔 매개변수 x
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
