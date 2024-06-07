package com.ifc.work.restImpl;

import com.ifc.work.dtos.UserCredentialsDto;
import com.ifc.work.dtos.UserDto;
import com.ifc.work.dtos.UserPermissionsDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.requests.library.LibraryAddRequest;
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
    public ResponseEntity<String> addLibrary(LibraryAddRequest libraryAddRequest) {
        try {
            // Extract DTOs from the request object
            UserDto userDto = request.getUserDto();
            UserCredentialsDto userCredentialsDto = request.getUserCredentialsDto();
            UserPermissionsDto userPermissionsDto = request.getUserPermissionsDto();

            // Call the userService to process the sign-up
            return userService.signUp(userDto, userCredentialsDto, userPermissionsDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> editLibrary(LibraryAddRequest libraryAddRequest) {
        try {
            // Extract DTOs from the request object
            UserDto userDto = request.getUserDto();
            UserCredentialsDto userCredentialsDto = request.getUserCredentialsDto();
            UserPermissionsDto userPermissionsDto = request.getUserPermissionsDto();

            // Call the userService to process the sign-up
            return userService.signUp(userDto, userCredentialsDto, userPermissionsDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeLibrary(Long libraryId) {
        try {
            // Extract DTOs from the request object
            UserDto userDto = request.getUserDto();
            UserCredentialsDto userCredentialsDto = request.getUserCredentialsDto();
            UserPermissionsDto userPermissionsDto = request.getUserPermissionsDto();

            // Call the userService to process the sign-up
            return userService.signUp(userDto, userCredentialsDto, userPermissionsDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> addObserverToLibrary(Long libraryId, Long userId) {
        try {
            libraryService.addObserverToLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeObserverFromLibrary(Long libraryId, Long userId) {
        try {
            libraryService.removeObserverFromLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> notifyObserversInLibrary(Long libraryId, Long bookId ) {
        try {
            libraryService.notifyObserversInLibrary(libraryId, bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addBookToLibrary(Long libraryId, Long bookId) {
        try {
            libraryService.addBookToLibrary(libraryId, bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeBookFromLibrary(Long libraryId, Long bookId) {
        try {
            libraryService.removeBookFromLibrary(libraryId, bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addUserToLibrary(Long libraryId, Long userId) {
        try {
            libraryService.addUserToLibrary(libraryId, userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeUserFromLibrary(Long libraryId, Long userId) {
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
