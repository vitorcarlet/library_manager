package com.ifc.work.repositories;

import com.ifc.work.persistence.BookStoreVisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreVisitRepository extends JpaRepository<BookStoreVisitEntity, Long> {
}