package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.PostImage;
import ru.repos.PostImageRepository;

@Service
public class PostImageService {
    @Autowired
    private PostImageRepository postImageRepository;

    public PostImage savePostImage(PostImage postImage) {
        return postImageRepository.save(postImage);
    }

    public void deletePostImage(PostImage postImage) {
        postImageRepository.delete(postImage.getImageId());
    }

}
