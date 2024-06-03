package com.ifc.work.repositories;


import com.ifc.work.persistence.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {
    AddressEntity findByCep(int cep);
}
