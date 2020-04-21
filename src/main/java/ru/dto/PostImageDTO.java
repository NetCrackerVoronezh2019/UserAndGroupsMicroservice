package ru.dto;

import ru.domen.PostImage;

public class PostImageDTO {
    private Long imageId;
    private Long postId;
    private String image;

    public static PostImageDTO getPostImage(PostImage postImage) {
        PostImageDTO postImageDTO = new PostImageDTO();
        postImageDTO.imageId = postImage.getImageId();
        postImageDTO.image = postImage.getImage();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
