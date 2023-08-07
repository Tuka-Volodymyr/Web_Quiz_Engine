package engine.repository;

import engine.entity.Answer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerInfoRepository extends PagingAndSortingRepository<Answer,Integer> {
}
