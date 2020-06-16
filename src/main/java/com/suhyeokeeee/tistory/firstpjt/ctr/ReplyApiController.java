package com.suhyeokeeee.tistory.firstpjt.ctr;

import com.suhyeokeeee.tistory.firstpjt.domain.reply.Replies;
import com.suhyeokeeee.tistory.firstpjt.dto.reply.ReplySaveDto;
import com.suhyeokeeee.tistory.firstpjt.dto.reply.ReplyUpdateDto;
import com.suhyeokeeee.tistory.firstpjt.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("/reply/save")
    public Replies save(@RequestBody ReplySaveDto dto){
        return replyService.save(dto);
    }

    @DeleteMapping("/reply/{post_id}/{reply_id}")
    public void delete(@PathVariable Long reply_id, @PathVariable Long post_id){
        replyService.delete(post_id,reply_id);
    }
}
