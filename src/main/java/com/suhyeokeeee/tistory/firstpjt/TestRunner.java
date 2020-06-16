package com.suhyeokeeee.tistory.firstpjt;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.post.PostsRepository;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.RepliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestRunner implements ApplicationRunner {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    RepliesRepository repliesRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Posts tmp = postsRepository.save(Posts.builder()
        .author("1")
        .content("1")
        .title("1")
        .build());

        Posts tmp2 = postsRepository.save(Posts.builder()
                .author("2")
                .content("2")
                .title("2")
                .build());

        Replies rep1 = repliesRepository.save(Replies.builder()
        .reply_content("re_1")
        .reply_name("re_1")
        .posts(tmp)
        .build());

        Replies rep2 = repliesRepository.save(Replies.builder()
                .reply_content("re_2")
                .reply_name("re_2")
                .posts(tmp)
                .build());


    }
}
