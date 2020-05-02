package life.ning.community.controller;

import life.ning.community.dto.CommentDTO;
import life.ning.community.dto.ResultDTO;
import life.ning.community.exception.CustomizeErrorCode;
import life.ning.community.mapper.CommentMapper;
import life.ning.community.model.Comment;
import life.ning.community.model.User;
import life.ning.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {



    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

       // if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
       //     return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
       // }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insertSelective(comment);
        return ResultDTO.okOf();


    }


}
