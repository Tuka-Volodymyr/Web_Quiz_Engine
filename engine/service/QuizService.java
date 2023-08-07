package engine.service;
import engine.dto.AnswerDto;
import engine.dto.AnswerFeedBackDto;
import engine.entity.Answer;
import engine.entity.Question;
import engine.entity.SolvedQuiz;
import engine.entity.User;
import engine.exceptions.QuestionNotFoundException404;
import engine.exceptions.UserNotFoundException404;
import engine.dto.QuizDto;
import engine.repository.AnswerInfoRepository;
import engine.repository.QuestionInfoRepository;
import engine.repository.SolvedQuizInfoRepository;
import engine.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
public class QuizService {
    @Autowired
    private QuestionInfoRepository questionInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private SolvedQuizInfoRepository solvedQuizInfoRepository;
    @Autowired
    private AnswerInfoRepository answerInfoRepository;
    public ResponseEntity<?> addQuestion(QuizDto quizDto, UserDetails userDetails){
        User user=userInfoRepository
                .findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(UserNotFoundException404::new);
        Question question=new Question();
        Answer answer=new Answer();
        question.setTitle(quizDto.getTitle());
        question.setText(quizDto.getText());
        question.setOptions(quizDto.getOptions());
        question.setId_user(user.getId());
        questionInfoRepository.save(question);
        answer.setId_question(question.getId());
        answer.setAnswer(quizDto.getAnswer());
        answerInfoRepository.save(answer);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    public ResponseEntity<?> answer(AnswerDto answer, int idQuestion, UserDetails userDetails) {
        User user=userInfoRepository
                .findByEmailIgnoreCase(userDetails.getUsername())
                .orElseThrow(UserNotFoundException404::new);
        AnswerFeedBackDto answerFeedBackDto;
        Answer corectAnswer = answerInfoRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException404::new);
        Question question = questionInfoRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException404::new);
        if(!answer.getAnswer().equals(corectAnswer.getAnswer())){
            answerFeedBackDto = AnswerFeedBackDto.WRONG_ANSWER_DTO;
        }else {
            answerFeedBackDto = AnswerFeedBackDto.CORRECT_ANSWER_DTO;
            solvedQuizInfoRepository.save(new SolvedQuiz(user.getId(),question.getId(),LocalDateTime.now()));
        }
        return new ResponseEntity<>(answerFeedBackDto, HttpStatus.OK);
    }

    public ResponseEntity<?> getQuestionById(int idQuestion){
        Question question=questionInfoRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException404::new);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
    public ResponseEntity<?> getAllQuestion(int numberOfPage){
        Pageable paging = PageRequest.of(numberOfPage, 10);
        Page<Question> questions=questionInfoRepository.findAll(paging);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    public ResponseEntity<?> deleteQuestion(int idQuestion, UserDetails userDetails){
        Question question = questionInfoRepository
                .findById(idQuestion)
                .orElseThrow(QuestionNotFoundException404::new);
        User user=userInfoRepository
                .findById(question.getId_user())
                .orElseThrow(UserNotFoundException404::new);
        if(!userDetails.getUsername().equals(user.getEmail()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        questionInfoRepository.delete(question);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
