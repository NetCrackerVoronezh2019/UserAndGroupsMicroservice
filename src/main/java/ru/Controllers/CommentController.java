package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Comment;
import ru.dto.CommentDTO;
import ru.services.CommentService;
import ru.services.PostService;
import ru.services.UserService;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/SendComment")
    public void sendComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setDate(new Date());
        comment.setPost(postService.getById(commentDTO.getPostId()));
        comment.setSender(userService.getUserById(commentDTO.getSender().getUserId()));
        comment.setText(commentDTO.getText());
        commentService.saveComment(comment);
    }

    @PutMapping("/RedactComment")
    public void redactComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.getCommentById(commentDTO.getCommentId());
        comment.setText(commentDTO.getText());
        commentService.saveComment(comment);
    }

    @DeleteMapping
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentService.getCommentById(commentId));
    }

}
