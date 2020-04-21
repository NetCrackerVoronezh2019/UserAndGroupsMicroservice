package ru.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.domen.Post;
import ru.domen.PostImage;
import ru.dto.AmazonModel;
import ru.dto.PostDTO;
import ru.services.CommentService;
import ru.services.GroupService;
import ru.services.PostImageService;
import ru.services.PostService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class PostController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostImageService postImageService;
    @Autowired
    private CommentService commentService;

    @PostMapping("groups/makePost")
    public void makePost(@RequestBody PostDTO postDTO) {
        Post post = new Post();
        post.setDate(new Date());
        post.setGroup(groupService.getGroupById(postDTO.getGroupId()));
        post.setText(postDTO.getText());
        post = postService.savePost(post);
        int i = 0;
        for (String image :
                postDTO.getImages()) {
            String key = "post_" + post.getPostId() + "image_" + i;
            PostImage postImage = new PostImage();
            postImage.setPost(post);
            postImage.setImage(key);
            postImageService.savePostImage(postImage);
            AmazonModel amazonModel = new AmazonModel(key,image);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<AmazonModel> amazonModelHttpEntity = new HttpEntity<>(amazonModel);
            restTemplate.exchange("http://localhost:1234/groups/uploadFile", HttpMethod.POST,amazonModelHttpEntity,Object.class);
            i++;
        }
    }

    @PutMapping("/postSettings")
    public void postSettings(@RequestBody PostDTO postDTO) {
        Post post = postService.getById(postDTO.getPostId());
        post.setText(postDTO.getText());
        postService.savePost(post);
    }

    @DeleteMapping("/deletePost")
    public void deletePost(@RequestParam Long postId) {
        Post post = postService.getById(postId);
        postService.deletePost(post);
    }

    @GetMapping("groups/getPosts")
    public List<PostDTO> getGroupPosts(@RequestParam Long groupId) {
        List<Post> posts = groupService.getGroupById(groupId).getPosts().stream().sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post :
                posts) {
            postDTOS.add(PostDTO.getPostDTO(post));
        }
        return postDTOS;
    }
}
