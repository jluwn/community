package life.ning.community.mapper;

import life.ning.community.model.Question;
import life.ning.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);
    // Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    // List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}