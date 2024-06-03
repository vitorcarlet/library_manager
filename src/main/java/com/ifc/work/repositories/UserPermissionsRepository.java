package com.ifc.work.repositories;

import com.ifc.work.persistence.UserCredentialsEntity;
import com.ifc.work.persistence.UserPermissionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionsRepository extends JpaRepository<UserPermissionsEntity, Long> {

    //UserPermissionsEntity findByLogin(String login);


    UserCredentialsEntity findByLogin(String login);

    UserPermissionsEntity findByUserCredentialsId(Long id);
}
