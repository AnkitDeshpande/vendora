package com.project.vendora.core.repository;

import com.project.vendora.core.entity.StoreOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Long> {
}