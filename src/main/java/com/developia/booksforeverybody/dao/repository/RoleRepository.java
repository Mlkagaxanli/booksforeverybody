package com.developia.booksforeverybody.dao.repository;

import com.developia.booksforeverybody.dao.entity.RoleEntity;
import com.developia.booksforeverybody.dao.entity.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByName(RoleStatus name);
}
