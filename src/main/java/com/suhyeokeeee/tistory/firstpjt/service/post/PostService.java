package com.suhyeokeeee.tistory.firstpjt.service.post;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.post.PostsRepository;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsListResponseDto;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsResponseDto;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsSaveRequestDto;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor    // Autowired 대신 주입해줌
public class PostService {
    private final PostsRepository postsRepository;    // service에선 레파지토리를 받아와서 사용

    // 맨 처음에 해야 할 거 글등록
    @Transactional
    public Posts save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity());
    }

    @Transactional
    public void update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {    // 몇번째 글인지랑 수정하는 글을 요청받음
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시물이 없습니다. id = "+id));
        posts.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("게시물이 없습니다"+id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()
        ->new IllegalArgumentException("해당 게시물이 없다 id = "+id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // 스트림을 Dto형식으로 바꿔줌
                .collect(Collectors.toList());
    }
}
