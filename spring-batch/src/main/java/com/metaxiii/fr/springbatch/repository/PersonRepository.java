package com.metaxiii.fr.springbatch.repository;

import com.metaxiii.fr.springbatch.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}
