package com.ifc.work.servicesImpl;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.LibraryEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.repositories.BookRepository;
import com.ifc.work.repositories.LibraryRepository;
import com.ifc.work.services.LibraryService;
import com.ifc.work.utils.LibraryUtils;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<String> addObserverToLibrary(Long libraryId, UserDto userDto) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        UserEntity user = new UserEntity(userDto.name(),userDto.cpf(), userDto.birth(),userDto.gender(),userDto.registration(),true);
        library.addObserver(user);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> removeObserverFromLibrary(Long libraryId, UserDto userDto) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        UserEntity user = new UserEntity(userDto.name(),userDto.cpf(), userDto.birth(),userDto.gender(),userDto.registration(),true);
        library.removeObserver(user);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> notifyObserversInLibrary(Long libraryId, BookEntity book) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));

        library.notifyObservers(book);
        // Não é necessário salvar a biblioteca aqui, pois notifyObservers apenas notifica, não altera o estado da entidade
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> addBookToLibrary(Long libraryId, BookEntity book) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        library.addBook(book);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> removeBookFromLibrary(Long libraryId, BookEntity book) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        library.removeBook(book);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> addUserToLibrary(Long libraryId, UserEntity user) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        library.addUser(user);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> removeUserFromLibrary(Long libraryId, UserEntity user) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        library.removeUser(user);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BookWrapper>> getBooks(Long libraryId) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));

        List<BookWrapper> bookWrappers = library.getBooks().stream()
                .map(bookEntity -> new BookWrapper(bookEntity))
                .collect(Collectors.toList());

        return new ResponseEntity<>(bookWrappers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getUsers(Long libraryId) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));

        List<UserWrapper> userWrappers = library.getUsers().stream()
                .map(userEntity -> new UserWrapper(userEntity))
                .collect(Collectors.toList());

        return new ResponseEntity<>(userWrappers, HttpStatus.OK);
    }
}
