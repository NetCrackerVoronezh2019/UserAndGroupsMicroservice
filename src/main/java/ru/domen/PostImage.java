package ru.domen;

import javax.persistence.*;

@Entity
@Table(name = "PostsImages")
public class PostImage {
    @Id
    @Column(name = "imageId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;
    @Column(name = "imageURL")
    private String imageURL;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postId")
    private Post post;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
