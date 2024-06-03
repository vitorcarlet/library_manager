package com.ifc.work.restImpl;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.requests.library.UserObserverRequest;
import com.ifc.work.rest.LibraryRest;
import com.ifc.work.services.LibraryService;
import com.ifc.work.utils.LibraryUtils;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryRestImpl implements LibraryRest {

    @Autowired
    private LibraryService libraryService;

    @Override
    public ResponseEntity<Void> addObserverToLibrary(Long libraryId, Long userId) {
        try {
            libraryService.addObserverToLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> removeObserverFromLibrary(Long libraryId, Long userId) {
        try {
            libraryService.removeObserverFromLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> notifyObserversInLibrary(Long libraryId, Long bookId ) {
        try {
            libraryService.notifyObserversInLibrary(libraryId, bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> addBookToLibrary(Long libraryId, Long bookId) {
        try {
            libraryService.addBookToLibrary(libraryId, bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> removeBookFromLibrary(Long libraryId, Long bookId) {
        try {
            libraryService.removeBookFromLibrary(libraryId, bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> addUserToLibrary(Long libraryId, Long userId) {
        try {
            libraryService.addUserToLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> removeUserFromLibrary(Long libraryId, Long userId) {
        try {
            libraryService.removeUserFromLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<BookWrapper>> getBooks(Long libraryId) {
        try {
            return libraryService.getBooks(libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getUsers(Long libraryId) {
        try {
            return libraryService.getUsers(libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
