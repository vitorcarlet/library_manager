package com.ifc.work.dtos;

import com.ifc.work.decorator.book.BookDecorator;

import java.util.Date;

public record BookDto (
         Long id,

         String title,

         String author,

         int pubYear,

         int bookCode,

         String bookType,

        int quantity,

        Date registration_date

         //BookDecorator livroEdicao
){
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public int pubYear() {
        return pubYear;
    }

    @Override
    public int bookCode() {
        return bookCode;
    }

    @Override
    public String bookType() {
        return bookType;
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public Date registration_date() {
        return registration_date;
    }

//    @Override
//    public BookDecorator livroEdicao() {
//        return livroEdicao;
//    }
}
