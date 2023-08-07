package engine.controller;

import engine.service.SolvedQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SolvedQuizController {
    @Autowired
    private SolvedQuizService solvedQuizService;
    @GetMapping(value = "/quizzes/completed",params = "page")
    public ResponseEntity<?> getPageOfCompleted(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(defaultValue = "0") int page){
        return solvedQuizService.getPageOfCompleted(userDetails,page);

    }

}
