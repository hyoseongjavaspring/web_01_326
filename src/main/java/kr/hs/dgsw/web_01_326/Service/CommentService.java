package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import kr.hs.dgsw.web_01_326.Protocol.CommentUserNameProtocol;

import java.util.List;

public interface    CommentService {
    List<CommentUserNameProtocol> listAllComments();
    CommentUserNameProtocol add(Comment comment);
    CommentUserNameProtocol update(Comment comment);
    boolean delete(Long id);

}
