package engine.service;

import engine.entity.SolvedQuiz;
import engine.entity.User;
import engine.exceptions.UserNotFoundException404;
import engine.repository.SolvedQuizInfoRepository;
import engine.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;


@Service
public class SolvedQuizService {
    @Autowired
    private SolvedQuizInfoRepository solvedQuizInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    public ResponseEntity<?> getPageOfCompleted(UserDetails userDetails, int numberOfPage){
        Pageable page  =  PageRequest.of(numberOfPage, 10, Sort.by("time_stamp").descending());
        User user=userInfoRepository
                .findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(UserNotFoundException404::new);
        Page<SolvedQuiz> solvedQuizPage=solvedQuizInfoRepository.
                findCompletedQuizFromUserId(user.getId(), page);
        return new ResponseEntity<>(solvedQuizPage,HttpStatus.OK);
    }

}
