package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Protocol.AttachmentProtocol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface UserService {
    List<User> listAllUsers();
    User add(User user);
    User update(User user);
    boolean delete(Long id);

    User view(Long id);

    AttachmentProtocol getPathById(Long id);
}
