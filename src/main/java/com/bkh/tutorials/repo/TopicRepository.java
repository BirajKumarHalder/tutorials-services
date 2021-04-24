package com.bkh.tutorials.repo;

import com.bkh.tutorials.repo.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer> {
}
