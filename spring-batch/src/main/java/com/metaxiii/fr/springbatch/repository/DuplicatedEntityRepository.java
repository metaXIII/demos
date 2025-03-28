package com.metaxiii.fr.springbatch.repository;

import com.metaxiii.fr.springbatch.entity.DuplicatedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuplicatedEntityRepository extends JpaRepository<DuplicatedEntity, Integer> {}
