package com.example.test.repository;

import com.example.test.model.Post;
import com.example.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post getById(long id);

    @Query("select (u) from Post u where u.user =?1")
    List<Post> getByIdPost(User user);

    @Query("select (u) from Post u where u.header=?1 or u.type=?1 or u.phone=?1")
    List<Post> searchAllBy (String word);

}
