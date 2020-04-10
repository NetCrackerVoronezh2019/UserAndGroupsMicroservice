package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Post;
import ru.domen.PostImage;
import ru.dto.PostDTO;
import ru.dto.PostImageDTO;
import ru.services.GroupService;
import ru.services.PostImageService;
import ru.services.PostService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class PostController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostImageService postImageService;

    @PostMapping("groups/makePost")
    public void makePost(@RequestBody PostDTO postDTO) {
        Post post = new Post();
        post.setDate(new Date());
        post.setGroup(groupService.getGroupById(postDTO.getGroupId()));
        post.setText(postDTO.getText());
        postService.savePost(post);
        for (PostImageDTO image :
                postDTO.getImages()) {
            PostImage postImage = new PostImage();
            postImage.setPost(post);
            postImage.setImageURL(image.getImageURL());
            postImageService.savePostImage(postImage);
        }
    }

    @PutMapping("/postSettings")
    public void postSettings(@RequestBody PostDTO postDTO) {
        Post post = postService.getById(postDTO.getPostId());
        post.setText(postDTO.getText());
        for (PostImage postImage :
             post.getImages()) {
            postImageService.deletePostImage(postImage);
        }
        post.setImages(new ArrayList<>());
        for (PostImageDTO image :
                postDTO.getImages()) {
            PostImage postImage = new PostImage();
            postImage.setPost(post);
            postImage.setImageURL(image.getImageURL());
            postImageService.savePostImage(postImage);
        }
    }

    @DeleteMapping("/deletePost")
    public void deletePost(@RequestParam Long postId) {
        Post post = postService.getById(postId);
        for (PostImage postImage :
                post.getImages()) {
            postImageService.deletePostImage(postImage);
        }
        postService.deletePost(post);
    }
}
