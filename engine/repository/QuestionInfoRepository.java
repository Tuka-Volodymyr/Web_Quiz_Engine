package engine.repository;

import engine.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionInfoRepository extends JpaRepository<Question,Integer>,
        PagingAndSortingRepository<Question,Integer> {
//    @Query(value = "SELECT id, title, text, options FROM question, question_options ",
//            nativeQuery = true)
     Page<Question> findAll(Pageable page);
//    Optional<Question> findById(Integer id);

}
