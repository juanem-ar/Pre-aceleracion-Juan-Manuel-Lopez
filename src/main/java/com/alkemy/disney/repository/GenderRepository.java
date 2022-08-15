package com.alkemy.disney.repository;

import com.alkemy.disney.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}
