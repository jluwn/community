package life.ning.community.mapper;
import life.ning.community.model.Comment;



public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}