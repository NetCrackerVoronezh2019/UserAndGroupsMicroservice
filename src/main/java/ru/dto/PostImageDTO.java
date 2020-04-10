package ru.dto;

import ru.domen.PostImage;

public class PostImageDTO {
    private Long imageId;
    private Long postId;
    private String imageURL;

    public static PostImageDTO getPostImage(PostImage postImage) {
        PostImageDTO postImageDTO = new PostImageDTO();
        postImageDTO.imageId = postImage.getImageId();
        postImageDTO.imageURL = postImage.getImageURL();
        postImageDTO.postId = postImage.getPost().getPostId();
        return postImageDTO;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
