package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.domen.Comment;
import ru.dto.CommentDTO;
import ru.repos.CommentRepository;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment.getCommentId());
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).get();
    }
}
