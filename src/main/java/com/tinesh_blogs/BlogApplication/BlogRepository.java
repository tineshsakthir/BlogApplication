package com.tinesh_blogs.BlogApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog ,Integer> {
    public List<Blog> findByUsername(String username) ;
}