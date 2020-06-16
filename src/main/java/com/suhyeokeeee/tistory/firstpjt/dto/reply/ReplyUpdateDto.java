package com.suhyeokeeee.tistory.firstpjt.dto.reply;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import lombok.Getter;

@Getter
public class ReplyUpdateDto {
    private String reply_name;
    private String reply_content;
    private Posts posts;
    private Long id;


    public ReplyUpdateDto(Replies entity){
        this.reply_name = entity.getReply_name();
        this.reply_content = entity.getReply_content();
        this.posts = entity.getPosts();
    }
}
