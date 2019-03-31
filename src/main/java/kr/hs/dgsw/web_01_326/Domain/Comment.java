package kr.hs.dgsw.web_01_326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    public Comment(Comment c){
        this.id = c.getId();
        this.userId = c.getUserId();
        this.content = c.getContent();
        this.created = c.getCreated();
        this.modified = c.getModified();
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

}
