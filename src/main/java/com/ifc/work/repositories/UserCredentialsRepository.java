package com.ifc.work.repositories;

import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentialsEntity, Long> {

    UserCredentialsEntity findByLogin(String login);

    UserEntity findByUserId(Long id);
}
