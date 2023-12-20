package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
