package com.ifc.work.servicesImpl;

import com.ifc.work.persistence.BookStoreVisitEntity;
import com.ifc.work.proxy.BookStoreVisitServiceProxy;
import com.ifc.work.services.BookStoreVisitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookstore-visits")
public class BookStoreVisitServiceProxyImpl implements BookStoreVisitServiceInterface {

    @Autowired
    private BookStoreVisitServiceInterface bookStoreVisitService;

    @Bean
    public BookStoreVisitServiceInterface bookStoreVisitServiceProxy() {
        return BookStoreVisitServiceProxy.createProxy(bookStoreVisitService);
    }

    @GetMapping
    public List<BookStoreVisitEntity> getAllVisits() {
        return bookStoreVisitServiceProxy().getAllVisits();
    }

    @PostMapping
    public BookStoreVisitEntity createVisit(@RequestBody BookStoreVisitEntity visit) {
        return bookStoreVisitServiceProxy().createVisit(visit);
    }

    @GetMapping("/{id}")
    public Optional<BookStoreVisitEntity> getVisitById(@PathVariable Long id) {
        return bookStoreVisitServiceProxy().getVisitById(id);
    }

    @PutMapping("/{id}")
    public Optional<BookStoreVisitEntity> updateVisit(@PathVariable Long id, @RequestBody BookStoreVisitEntity updatedVisit) {
        return bookStoreVisitServiceProxy().updateVisit(id, updatedVisit);
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        bookStoreVisitServiceProxy().deleteVisit(id);
    }
}