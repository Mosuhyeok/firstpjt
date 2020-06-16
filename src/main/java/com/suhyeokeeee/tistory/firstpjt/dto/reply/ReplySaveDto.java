package com.suhyeokeeee.tistory.firstpjt.dto.reply;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.post.PostsRepository;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class ReplySaveDto {
    private String reply_name;
    private String reply_content;
    private Posts posts;
    private Long id;

    public Replies toEntity(){
        return Replies.builder()
                .reply_name(reply_name)
                .reply_content(reply_content)
                .posts(posts)
                .build();
    }
}
