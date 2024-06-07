package com.ifc.work.repositories;

import com.ifc.work.persistence.BookLoanEntity;
import com.ifc.work.persistence.LibraryEntity;
import com.ifc.work.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity,Long> {
    @Query("Select u from UserEntity u WHERE u.id = :userId")
    UserEntity findUserEntityByUserEntityId(Long userId);

    @Query("SELECT b FROM BookLoanEntity b WHERE b.user.id = :userId AND b.book.id = :bookId")
    BookLoanEntity findByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
