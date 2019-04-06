package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;

import java.util.List;


public interface UserService {
    List<User> listAllUsers();
    User add(User user);
    User update(User user);
    boolean delete(Long id);

    User view(Long id);
}
