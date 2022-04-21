package com.example.test.service;

import com.example.test.model.Post;
import com.example.test.model.User;
import com.example.test.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post save(Post post) {

        return postRepository.save(post);
    }

    public List<Post> findPost(User user) {

        return postRepository.getByIdPost(user);
    }

    public void delete(long id) {
        postRepository.deleteById(id);

    }

    public List<Post> search(String word) {
        if (word == null) {

            return postRepository.findAll();
        }
        return postRepository.searchAllBy(word);
    }
}
