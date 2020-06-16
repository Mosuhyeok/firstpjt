package com.suhyeokeeee.tistory.firstpjt.domain.post;

import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import com.suhyeokeeee.tistory.firstpjt.jpaconfig.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    // 추후에 누가 글을 올렸는지도 알 수 있게 해보기
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    //게시글 삭제 할 시 댓글도 삭제하기 위해서 cascade ALL
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "posts")
    private List<Replies>  replies = new ArrayList<Replies>();
    @Builder
    public Posts(String title, String content,String author){
        this.title = title;
        this.content=content;
        this.author=author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content=content;
    }
}
