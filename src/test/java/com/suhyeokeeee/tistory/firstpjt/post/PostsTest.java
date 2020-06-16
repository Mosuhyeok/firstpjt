package com.suhyeokeeee.tistory.firstpjt.post;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.domain.post.PostsRepository;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsSaveRequestDto;
import com.suhyeokeeee.tistory.firstpjt.dto.post.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsTest {

    @Autowired
    private PostsRepository postsRepository;


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //통합테스트의 경우 h2 DB가 초기화가 안되서 다른 테스트에 오류 날 수 있으니 비워주기
    @After  // Junit에서 단위테스트가 끝날 때 마다 수행하는 메소드를 지정해주는 어노테이션
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void entity_Check() {
        String title = "title";
        String content = "content";

        LocalDateTime date = LocalDateTime.now();
        Posts posts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void save_test() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String author = "author";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String url = "http://localhost:" +port+"/blog/posts";

        //when
        ResponseEntity<Posts> responseEntity  = restTemplate.postForEntity(url,requestDto,Posts.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
    }
    @Test
    public void update_test() throws Exception{
        String title = "title";
        String content = "content";
        String author = "author";
        Posts posts = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());
        Long id = posts.getId();
        String change_title = "change_title";
        String change_content = "change_content";

        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                .title(change_title)
                .content(change_content)
                .build();

        String url = "http://localhost:"+port+"/blog/post/"+id;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(postsUpdateRequestDto);
        //Http 프로토콜을 이용하는 통신의 header와 body 관련 정보를 저장할 수 있도록 한다

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url,HttpMethod.PUT,requestEntity,Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Posts>list = postsRepository.findAll();

        assertThat(list.get(0).getTitle()).isEqualTo(change_title);
        assertThat(list.get(0).getContent()).isEqualTo(change_content);
    }

    @Test
    public void delete_test() {
        String title = "title";
        String content = "content";
        String author = "author";
        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        postsRepository.save(posts);
        Long id = posts.getId();
        String url = "http://localhost:" + port + "/blog/post/" + id;
        HttpEntity<Posts> requestEntity = new HttpEntity<>(posts);
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Posts>list = postsRepository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }
}
