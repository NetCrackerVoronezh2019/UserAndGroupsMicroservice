package ru.dto;

import ru.domen.Comment;
import ru.domen.Group;
import ru.domen.Post;
import ru.domen.PostImage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDTO {
    private Long postId;
    private String text;
    private Date date;
    private List<String> images = new ArrayList<>();
    private Long groupId;

    public static PostDTO getPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.postId = post.getPostId();
        postDTO.date = post.getDate();
        postDTO.text = post.getText();
        postDTO.groupId = post.getGroup().getGroupId();
        for (PostImage postImage :
                post.getImages()) {
            postDTO.images.add(postImage.getImageURL());
        }
        return postDTO;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}