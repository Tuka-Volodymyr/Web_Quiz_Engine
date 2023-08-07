package engine.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerFeedBackDto {
    public final static AnswerFeedBackDto CORRECT_ANSWER_DTO = new AnswerFeedBackDto(true, "Congratulations, you're right!");
    public final static AnswerFeedBackDto WRONG_ANSWER_DTO = new AnswerFeedBackDto(false, "Wrong answer! Please, try again.");
    private boolean success;
    private String feedback;
    private AnswerFeedBackDto(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }
}
