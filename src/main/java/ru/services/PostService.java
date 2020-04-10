package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Post;
import ru.repos.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post getById(Long postId) {
        return postRepository.findById(postId).get();
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
