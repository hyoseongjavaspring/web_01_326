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
                    found.setStoredPath(user.getStoredPath());
                    found.setOriginalName(user.getOriginalName());
                    return this.userRepository.save(found);
                })
                .orElse((null));
    }

    @Override
    public boolean delete(Long id) {
        try{
            this.userRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public User view(Long id) {
        return this.userRepository.findById(id)
                .orElse(null);
    }
}
