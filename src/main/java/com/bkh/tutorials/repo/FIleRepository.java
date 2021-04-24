package com.bkh.tutorials.repo;

import com.bkh.tutorials.repo.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FIleRepository extends JpaRepository<FileEntity, Integer> {
}
