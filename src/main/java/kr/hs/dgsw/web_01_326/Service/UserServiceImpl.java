package kr.hs.dgsw.web_01_326.Service;

import kr.hs.dgsw.web_01_326.Domain.User;
import kr.hs.dgsw.web_01_326.Repository.CommentRepository;
import kr.hs.dgsw.web_01_326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<User> listAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User add(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent()){
            return null;
        }
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return this.userRepository.findByEmail(user.getEmail())
                .map(found ->{
                    found.setUserName(user.getUserName());
                    return this.userRepository.save(found);
                })
                .orElse((null));
    }


    //user 삭제할 대 comment같이 삭제해야하나?
    @Override
    public boolean delete(Long id) {
        try{
            this.commentRepository.deleteCommentsByUserId(id);
            try{
                this.userRepository.deleteById(id);
                return true;
            } catch (Exception e){
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public User view(String email) {
        return this.userRepository.findByEmail(email)
                .orElse(null);
    }
}
