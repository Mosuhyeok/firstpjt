package com.suhyeokeeee.tistory.firstpjt.service.reply;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.post.PostsRepository;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import com.suhyeokeeee.tistory.firstpjt.domain.reply.RepliesRepository;
import com.suhyeokeeee.tistory.firstpjt.dto.reply.ReplyResopnseDto;
import com.suhyeokeeee.tistory.firstpjt.dto.reply.ReplySaveDto;
import com.suhyeokeeee.tistory.firstpjt.dto.reply.ReplyUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReplyService {
    private final RepliesRepository repliesRepository;
    private final PostsRepository postsRepository;
    @Transactional
    public Replies save(ReplySaveDto dto){
        Posts posts = postsRepository.findById(dto.getId()).orElseThrow(()
        ->new IllegalArgumentException("오류"));
        dto.setPosts(posts);
        return repliesRepository.save(dto.toEntity());
    }


    public List<ReplyResopnseDto> findAllByPostId(Long id){
        List<Replies> replies = repliesRepository.findAllByPosts_id(id);
        List<ReplyResopnseDto> replyResopnseDto = new ArrayList<ReplyResopnseDto>();
        for(int i=0; i<replies.size(); i++){
            replyResopnseDto.add(new ReplyResopnseDto(replies.get(i)));
        }
        return replyResopnseDto;
    }

    @Transactional
    public void update(ReplyUpdateDto dto,Long post_id){
        Replies replies = repliesRepository.findByPosts_idAndId(post_id,dto.getId());
        replies.update(dto.getReply_name(),dto.getReply_content());
    }

    @Transactional
    public void delete(Long post_id, Long reply_id){
        Replies replies = repliesRepository.findByPosts_idAndId(post_id,reply_id);
        repliesRepository.delete(replies);
    }


}
