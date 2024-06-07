package com.ifc.work.repositories;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.BookLoanEntity;
import com.ifc.work.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoanEntity,Long> {
    @Query("SELECT b FROM BookLoanEntity b WHERE b.user.id = :userId AND b.book.id = :bookId")
    BookLoanEntity findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);

    List<BookLoanEntity> findByBookAndUser(BookEntity book, UserEntity user);


    //BookLoanEntity findByUserAndBook(UserEntity user, BookEntity book);
}
