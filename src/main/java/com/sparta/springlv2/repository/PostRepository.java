package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

//    @Query(value = "select p.id, p.title,p.content, " +
//            "p.user.username, p.createdAt, p.modifiedAt from" +
//            " Post p join fetch p.user")
    List<Post> findAll();
}
