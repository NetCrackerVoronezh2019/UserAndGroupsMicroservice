package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Comment;
import ru.domen.Post;
import ru.domen.PostImage;
import ru.repos.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;
    @Autowired PostImageService postImageService;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post getById(Long postId) {
        return postRepository.findById(postId).get();
    }

    public void deletePost(Post post) {
        for (PostImage postImage :
                post.getImages()) {
            postImageService.deletePostImage(postImage);
        }
        for (Comment comment :
                post.getComments()) {
            commentService.deleteComment(comment);
        }
        postRepository.delete(post.getPostId());
    }
}
