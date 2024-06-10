package com.ifc.work.servicesImpl;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.LibraryEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.repositories.BookRepository;
import com.ifc.work.repositories.LibraryRepository;
import com.ifc.work.repositories.UserRepository;
import com.ifc.work.services.LibraryService;
import com.ifc.work.services.SingletonInformação;
import com.ifc.work.utils.LibraryUtils;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import com.mysql.cj.xdevapi.SessionFactory;
import constants.LibraryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    SingletonInformação singletonInformação = SingletonInformação.getInstance("Você está no serviço das livrarias");

    public ResponseEntity<String> addObserverToLibrary(Long libraryId, String userId) {
        try{
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        Long userIdL = Long.parseLong(userId);
        UserEntity user = libraryRepository.findUserEntityByUserEntityId(userIdL);
        library.addObserver(user);
        libraryRepository.save(library);
            return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);}catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> removeObserverFromLibrary(Long libraryId, String userId) {
        try{
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        Long userIdL = Long.parseLong(userId);
        UserEntity user = libraryRepository.findUserEntityByUserEntityId(userIdL);
        library.removeObserver(user);
        libraryRepository.save(library);
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);}catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> notifyObserversInLibrary(Long libraryId, String bookId) {
        LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
        Long bookIdL = Long.parseLong(bookId);
        Optional<BookEntity> book = Optional.ofNullable(bookRepository.findById(bookIdL).orElseThrow(() -> new RuntimeException("Book not found")));
        BookEntity bookEntity = book.get();
        library.notifyObservers(bookEntity);
        // Não é necessário salvar a biblioteca aqui, pois notifyObservers apenas notifica, não altera o estado da entidade
        return LibraryUtils.getResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity<String> addBookToLibrary(Long libraryId, String bookId) {
        try {
            LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
            Long bookIdL = Long.parseLong(bookId);
            Optional<BookEntity> bookOpt = Optional.ofNullable(bookRepository.findById(bookIdL).orElseThrow(() -> new RuntimeException("Book not found")));
            BookEntity book = bookOpt.get();
            library.addBook(book);
            libraryRepository.save(library);
            return LibraryUtils.getResponseEntity("addBookToLibrary Successfully", HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> removeBookFromLibrary(Long libraryId, String bookId) {
        try {
            LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
            Long bookIdL = Long.parseLong(bookId);
            Optional<BookEntity> bookOpt = bookRepository.findById(bookIdL);
            BookEntity book = bookOpt.get();
            library.addBook(book);
            libraryRepository.save(library);
            return LibraryUtils.getResponseEntity("removeBookFromLibrary Succesfully", HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> addUserToLibrary(Long libraryId, String userId) {
        try {
            log.info(singletonInformação.value + "na parte de adicionar user na livraria");
            log.info(userId);
            Long userIdL = Long.parseLong(userId);
            LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
            Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findById(userIdL).orElseThrow(() -> new RuntimeException("User not found")));
            UserEntity user = userOpt.get();
            library.addUser(user);
            libraryRepository.save(library);
            return LibraryUtils.getResponseEntity("addUserToLibrary Succesfully", HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> removeUserFromLibrary(Long libraryId, String userId) {
        try {
            log.info(singletonInformação.value + "na parte de remover user na livraria");
            Long userIdL = Long.parseLong(userId);
            LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));
            Optional<UserEntity> userOpt = userRepository.findById(userIdL);
            UserEntity user = userOpt.get();
            library.removeUser(user);
            libraryRepository.save(library);
            return LibraryUtils.getResponseEntity(" removeUserFromLibrary Succesfully", HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<BookWrapper>> getBooks(Long libraryId) {
        try {
            LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));

            List<BookWrapper> bookWrappers = library.getBooks().stream()
                    .map(bookEntity -> new BookWrapper(bookEntity))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(bookWrappers, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<List<UserWrapper>> getUsers(Long libraryId) {
        try {
            LibraryEntity library = libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found"));

            List<UserWrapper> userWrappers = library.getUsers().stream()
                    .map(userEntity -> new UserWrapper(userEntity))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(userWrappers, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createLibrary(Long libraryId) {
        log.info("Entered createLibrary method");
        log.info("Library ID: {}", libraryId);
        try {
            Optional<LibraryEntity> libraryOpt = libraryRepository.findById(libraryId);
            log.info("Library ID: {}", libraryId);
            if (libraryOpt.isPresent()) {
                log.info("Library ID: {}", libraryId);
                LibraryEntity library = new LibraryEntity();
                library.setId(libraryId);
                library.setUsers(new HashSet<UserEntity>());
                libraryRepository.save(library);
                String successMessage = String.format("Success, created library with id: %d", libraryId);
                return LibraryUtils.getResponseEntity(successMessage, HttpStatus.OK);
            } else {
                String failureMessage = "Fail, a library with this ID already exists";
                return LibraryUtils.getResponseEntity(failureMessage, HttpStatus.CONFLICT); // Use 409 Conflict for existing resource
            }
        } catch (Exception ex) {
            log.error("Exception occurred while creating library", ex);
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeLibrary(Long libraryId) {
        try {
            Optional<LibraryEntity> library = libraryRepository.findById(libraryId);
            LibraryEntity libraryEntity = library.get();
            libraryRepository.delete(libraryEntity);
            return LibraryUtils.getResponseEntity("Success, removeLibrary library with id:" + libraryEntity.getId(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editLibrary(Set<BookEntity> books, Set<UserEntity> observers, Set<UserEntity> users, Long libraryId) {
        try {
            if (books == null || observers == null || users == null) {
                return new ResponseEntity<>("Invalid input: Books, observers, and users must not be null", HttpStatus.BAD_REQUEST);
            }

            Optional<LibraryEntity> library = libraryRepository.findById(libraryId);

            LibraryEntity libraryEntity = library.get();


            libraryEntity.setBooks(books);
            libraryEntity.setObservers(observers);
            libraryEntity.setUsers(users);

            libraryRepository.save(libraryEntity);

            return LibraryUtils.getResponseEntity("Success, editLibrary library with id:" + libraryEntity.getId(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
