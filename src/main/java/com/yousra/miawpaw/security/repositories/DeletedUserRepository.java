package com.yousra.miawpaw.security.repositories;

import com.yousra.miawpaw.security.models.entities.DeletedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedUserRepository extends JpaRepository<DeletedUser, Long> {
}
