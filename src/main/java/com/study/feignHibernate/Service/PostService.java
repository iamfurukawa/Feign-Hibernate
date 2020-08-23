package com.study.feignHibernate.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.feignHibernate.Feign.PostFeign;
import com.study.feignHibernate.Model.Post;
import com.study.feignHibernate.Repository.PostRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PostService {

	@Autowired
	private PostFeign postFeign;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PostRepository postRepository;

	@Transactional
	public void hibernateSaveAndShowAll() {
		List<Post> posts = postFeign.getPosts();

		posts.forEach(item -> entityManager.persist(item));

		list();
	}

	@Transactional(rollbackOn = Exception.class)
	public void hibernateSaveAndShowAllWithOneErrorToRollback() throws Exception {
		List<Post> posts = postFeign.getPosts();
		int idx = 75;

		for (Post post : posts) {
			entityManager.persist(post);
			
			post.setId(post.getId() + 500);
			postRepository.save(post);

			if (idx-- == 42)
				throw new Exception("Rollback pls!");
		}
	}

	public void list() {
		log.info("List user from database.");
		List<Post> postsSaved = postRepository.findAll();
		postsSaved.forEach(item -> log.info(item.toString()));
	}

	public void feignTestGetAll() {
		List<Post> posts = postFeign.getPosts();
		posts.forEach(item -> log.info(item.toString()));
	}

	public void feignTestGetOne() {
		Post post = postFeign.getPostById(96L);
		log.info(post.toString());
	}
}
