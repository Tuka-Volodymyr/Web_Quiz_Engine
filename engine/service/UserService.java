package engine.service;
import engine.entity.Question;
import engine.entity.User;
import engine.exceptions.QuestionNotFoundException404;
import engine.exceptions.UserNotFoundException404;
import engine.repository.QuestionInfoRepository;
import engine.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private QuestionInfoRepository questionInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseEntity<?> registerUser(User user){
        Optional<User> existUser=userInfoRepository.findByEmailIgnoreCase(user.getEmail());
        if(existUser.isPresent())throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userInfoRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
