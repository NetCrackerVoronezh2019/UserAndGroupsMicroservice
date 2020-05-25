package ru.dto;

import ru.domen.Comment;
import ru.domen.Post;
import ru.domen.User;

import java.time.LocalDateTime;
import java.util.Date;


public class CommentDTO {
    private Long commentId;
    private String text;
    private Long postId;
    private UserDTO sender;
    private LocalDateTime date;

    public static CommentDTO getCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.commentId = comment.getCommentId();
        commentDTO.postId = comment.getPost().getPostId();
        commentDTO.text = comment.getText();
        commentDTO.sender = UserDTO.getUserDTO(comment.getSender());
        commentDTO.date = comment.getDate();
        return commentDTO;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
