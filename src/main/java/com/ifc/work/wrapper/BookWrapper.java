package com.ifc.work.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifc.work.decorator.book.BookDecorator;
import com.ifc.work.persistence.BookEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookWrapper {
    private Long id;

    private String title;

    private String author;

    private int pubYear;

    private int bookCode;

    private String bookType;

    private int quantity;

    private Date registration_date;

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private BookDecorator livroEdicao;

    public BookWrapper(Long id, String title, String author, int pubYear, int bookCode, String bookType, int quantity, Date registration_date, BookDecorator livroEdicao) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
        this.bookCode = bookCode;
        this.bookType = bookType;
        this.quantity = quantity;
        this.registration_date = registration_date;
        //this.livroEdicao = livroEdicao;
    }

    public BookWrapper(BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        this.pubYear = bookEntity.getPubYear();
        this.bookCode = bookEntity.getBookCode();
        this.bookType = bookEntity.getBookType();
        this.quantity = bookEntity.getQuantity();
        this.registration_date = bookEntity.getRegistration_date();
       // this.livroEdicao = bookEntity.getLivroEdicao();
    }
}
