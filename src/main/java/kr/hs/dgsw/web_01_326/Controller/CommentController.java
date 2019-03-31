package kr.hs.dgsw.web_01_326.Controller;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Protocol.CommentUserNameProtocol;
import kr.hs.dgsw.web_01_326.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public List<CommentUserNameProtocol> list(){
        return this.commentService.listAllComments();
    }

    @PostMapping("/comment")
    public CommentUserNameProtocol add(@RequestBody Comment comment){
        return this.commentService.add(comment);
    }

    @PutMapping("/comment")
    public CommentUserNameProtocol update(@RequestBody Comment comment){
        return this.commentService.update(comment);
    }

    @DeleteMapping("/comment/{id}")
    public boolean delete(@PathVariable Long id){
        return this.commentService.delete(id);
    }
}
