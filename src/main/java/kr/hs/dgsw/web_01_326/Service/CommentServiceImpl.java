package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.CommentUserNameProtocol;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements  CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    //@PostConstruct
    private void init(){
        User u = this.userRepository.save(new User("abcd", "abcd@dgsw"));
        this.userRepository.save(u);
        this.commentRepository.save(new Comment(u.getId(), "Hello11"));
        this.commentRepository.save(new Comment(u.getId(), "Hello22"));
        this.commentRepository.save(new Comment(u.getId(), "Hello33"));
    }

    public CommentUserNameProtocol view(Comment comment){
        Optional<User> found = this.userRepository.findById(comment.getUserId());
        String userName = (found.isPresent()) ? found.get().getUserName() : null;
        return new CommentUserNameProtocol(comment, userName);
    }

    @Override
    public List<CommentUserNameProtocol> listAllComments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUserNameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            cupList.add(view(comment));
        });
        return cupList;
    }

    @Override
    public CommentUserNameProtocol add(Comment comment) {
        return this.userRepository.findById(comment.getUserId())
            .map(found -> new CommentUserNameProtocol(this.commentRepository.save(comment), found.getUserName()))
            .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try{
            this.commentRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public CommentUserNameProtocol update(Comment comment) {
        return this.commentRepository.findById(comment.getId())
                .map(found ->{
                    found.setContent(comment.getContent());
                    return view(this.commentRepository.save(found));
                })
                .orElse(null);
    }
}
