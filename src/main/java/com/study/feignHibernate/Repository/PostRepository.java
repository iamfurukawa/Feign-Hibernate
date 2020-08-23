package com.study.feignHibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.feignHibernate.Model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
