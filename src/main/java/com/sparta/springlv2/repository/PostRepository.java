package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Post;
import com.sparta.springlv2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

//    @Query(value = "select p.id, p.title,p.content, " +
//            "p.user.username, p.createdAt, p.modifiedAt from" +
//            " Post p join fetch p.user")
    List<Post> findAll();
}
