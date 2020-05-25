package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Comment;
import ru.dto.CommentDTO;
import ru.services.CommentService;
import ru.services.PostService;
import ru.services.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
        comment.setDate(LocalDateTime.now().plusHours(3));
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

    @DeleteMapping("/deleteComment")
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentService.getCommentById(commentId));
    }

    @GetMapping("/getComments")
    public List<CommentDTO> getComments(@RequestParam Long postId) {
        List<Comment> comments = postService.getById(postId).getComments().stream().sorted(Comparator.comparing(Comment::getDate).reversed()).collect(Collectors.toList());
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment :
                comments) {
            commentDTOS.add(CommentDTO.getCommentDTO(comment));
        }
        return commentDTOS;
    }

}
