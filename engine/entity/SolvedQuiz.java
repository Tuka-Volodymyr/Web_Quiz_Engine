package engine.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class SolvedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer quizId;
    @Basic
    private LocalDateTime timeStamp;
    public SolvedQuiz(Integer userId,Integer quizId, LocalDateTime timeStamp) {
        this.userId = userId;
        this.quizId=quizId;
        this.timeStamp = timeStamp;
    }
    public SolvedQuiz() {
    }
    @JsonIgnore
    public Integer getId() {
        return id;
    }
    @JsonIgnore
    public Integer getUserId() {
        return userId;
    }
    @JsonProperty("id")
    public Integer getQuizId() {
        return quizId;
    }
    @JsonProperty("completedAt")
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
