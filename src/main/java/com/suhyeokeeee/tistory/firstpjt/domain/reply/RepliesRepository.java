package com.suhyeokeeee.tistory.firstpjt.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepliesRepository extends JpaRepository<Replies,Long> {
    List<Replies> findAllByPosts_id(Long id);
    Replies findByPosts_idAndId(Long post_id, Long reply_id);
}
