package com.sparta.springlv2.post.domain.entity;


import com.sparta.springlv2.auth.domain.entity.User;
import com.sparta.springlv2.post.domain.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    private List<Comment> comments;

    public Post(PostRequestDto requestDto, User user) {
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
        this.user = user;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }
}
