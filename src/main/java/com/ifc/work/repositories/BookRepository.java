package com.ifc.work.repositories;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.BookLoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
    //List<Optional<BookEntity>> findByIds(List<Long> booksIds);
}
