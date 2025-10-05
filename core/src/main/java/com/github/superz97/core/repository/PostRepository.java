package com.github.superz97.core.repository;

import com.github.superz97.core.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post  , Long>, JpaSpecificationExecutor<Post> {

    boolean existsByAuthorIdAndId(Long authorId, Long id);

}
