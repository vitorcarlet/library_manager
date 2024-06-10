package com.ifc.work.servicesImpl;

import com.ifc.work.persistence.BookStoreVisitEntity;
import com.ifc.work.repositories.BookStoreVisitRepository;
import com.ifc.work.services.BookStoreVisitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreVisitService implements BookStoreVisitServiceInterface {

    @Autowired
    private BookStoreVisitRepository repository;

    public List<BookStoreVisitEntity> getAllVisits() {
        return repository.findAll();
    }

    public Optional<BookStoreVisitEntity> getVisitById(Long id) {
        return repository.findById(id);
    }

    public BookStoreVisitEntity createVisit(BookStoreVisitEntity visit) {
        return repository.save(visit);
    }

    public Optional<BookStoreVisitEntity> updateVisit(Long id, BookStoreVisitEntity updatedVisit) {
        return repository.findById(id).map(visit -> {
            visit.setVisitDate(updatedVisit.getVisitDate());
            visit.setNumberOfPeople(updatedVisit.getNumberOfPeople());
            return repository.save(visit);
        });
    }

    public void deleteVisit(Long id) {
        repository.deleteById(id);
    }
}