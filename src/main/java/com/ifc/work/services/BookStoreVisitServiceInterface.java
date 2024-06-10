package com.ifc.work.services;

import com.ifc.work.persistence.BookStoreVisitEntity;
import com.ifc.work.repositories.BookStoreVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface BookStoreVisitServiceInterface {

     List<BookStoreVisitEntity> getAllVisits();

     Optional<BookStoreVisitEntity> getVisitById(Long id);

     BookStoreVisitEntity createVisit(BookStoreVisitEntity visit);

     Optional<BookStoreVisitEntity> updateVisit(Long id, BookStoreVisitEntity updatedVisit);

     void deleteVisit(Long id);
}
