package com.suhyeokeeee.tistory.firstpjt.reply;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.post.PostsRepository;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.RepliesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReplyTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    RepliesRepository repliesRepository;

    @Test
    public void reply_Check(){
        Posts posts = postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        Replies replies = repliesRepository.save(Replies.builder()
        .reply_name("reply_name")
        .reply_content("reply_content")
        .posts(posts)
        .build());

        assertThat(replies.getPosts().getId()).isEqualTo(posts.getId());
        assertThat(replies.getReply_name()).isEqualTo("reply_name");
        assertThat(replies.getReply_content()).isEqualTo("reply_content");
    }
}
