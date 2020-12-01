package com.pai.pai_demo.service;

import com.pai.pai_demo.dtos.PostDto;
import com.pai.pai_demo.model.Account;
import com.pai.pai_demo.model.Post;
import com.pai.pai_demo.repository.AccountRepository;
import com.pai.pai_demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Post addPost(PostDto postDto) {
        Optional<Account> optionalAccount = accountRepository.findById(postDto.getAuthorId());
//        return postRepository.save(new Post(postDto.getTitle(),postDto.getContent(),postDto.getCategory(),optionalAccount.get()));
        return optionalAccount.map(account ->
                postRepository.save(new Post(postDto.getTitle(), postDto.getContent(), postDto.getCategory(), account))
        ).orElse(null);
    }
}
