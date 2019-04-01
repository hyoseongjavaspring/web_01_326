package kr.hs.dgsw.web_01_326.Protocol;

import kr.hs.dgsw.web_01_326.Domain.Comment;
import lombok.Data;

@Data
public class CommentUserNameProtocol extends Comment {
    private String userName;

    public CommentUserNameProtocol(Comment c, String userName){
        super(c);
        this.userName = userName;
    }

//    @Override
//    public Comment
}
