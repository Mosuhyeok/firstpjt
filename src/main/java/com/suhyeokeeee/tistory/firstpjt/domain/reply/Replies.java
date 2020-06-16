package com.suhyeokeeee.tistory.firstpjt.domain.reply;

import com.suhyeokeeee.tistory.firstpjt.domain.post.Posts;
import com.suhyeokeeee.tistory.firstpjt.jpaconfig.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Getter
public class Replies extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable =  false)
    private String reply_name;

    @Column(nullable = false)
    private String reply_content;

    @ManyToOne
    @JoinColumn(name ="posts_id")
    private Posts posts;



    @Builder
    public Replies(String reply_name, String reply_content,Posts posts){
        this.reply_name = reply_name;
        this.reply_content = reply_content;
        this.posts = posts;
    }

    public void update(String reply_name, String reply_content){
        this.reply_content = reply_content;
        this.reply_name = reply_name;
    }


}
