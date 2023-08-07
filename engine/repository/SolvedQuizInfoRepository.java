package engine.repository;

import engine.entity.SolvedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableArgumentResolver;

public interface SolvedQuizInfoRepository extends PagingAndSortingRepository<SolvedQuiz,Integer> {
    @Query(value = "SELECT * FROM SOLVED_QUIZ WHERE user_id = :user_id",
            countQuery = "SELECT count(*) FROM Users",
            nativeQuery = true)
    Page<SolvedQuiz> findCompletedQuizFromUserId(@Param("user_id") Integer userId,
                                                 Pageable pageable);
}
