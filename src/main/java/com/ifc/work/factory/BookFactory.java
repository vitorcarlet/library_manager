package com.ifc.work.factory;

import com.ifc.work.persistence.BookEntity;

public abstract class BookFactory {
     public BookFactory() {
     }

     public abstract BookEntity createBook(int idLivro, String titulo, String autor, String codigo, int quantidade);
}
