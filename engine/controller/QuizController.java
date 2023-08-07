package engine.controller;

import engine.dto.AnswerDto;
import engine.dto.QuizDto;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QuizController {
    @Autowired
    private QuizService quizService;
    @PostMapping(value = "/quizzes",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addQuestion(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody QuizDto quizDto){
        return quizService.addQuestion(quizDto,userDetails);
    }
    @PostMapping(value = "/quizzes/{id}/solve")
    public ResponseEntity<?> answer(@RequestBody AnswerDto answer, @PathVariable int id,@AuthenticationPrincipal UserDetails userDetails){
        return quizService.answer(answer,id,userDetails);
    }
    @GetMapping(value = "/quizzes/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable int id){
        return quizService.getQuestionById(id);
    }
    @GetMapping(value = "/quizzes",params = "page")
    public ResponseEntity<?> getAllQuestion(@RequestParam(defaultValue = "0") int page){
        return quizService.getAllQuestion(page);
    }
    @DeleteMapping(value = "/quizzes/{id}")
    public ResponseEntity<?> deleteQuestion(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id){
        return quizService.deleteQuestion(id,userDetails);
    }
}
