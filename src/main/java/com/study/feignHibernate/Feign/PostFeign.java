package com.study.feignHibernate.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.study.feignHibernate.Model.Post;

@FeignClient(name = "posts", url = "https://jsonplaceholder.typicode.com/")
public interface PostFeign {
	
	@GetMapping(value = "/posts")
	List<Post> getPosts();
	
	@GetMapping(value = "/posts/{postId}", produces = "application/json")
	Post getPostById(@PathVariable("postId") Long postId);
}
