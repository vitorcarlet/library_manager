package com.ifc.work.entities;

import com.ifc.work.persistence.LibraryEntity;
import jakarta.persistence.ManyToMany;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public record Book (
         Long id,

         String title,

         String Author,

         int pubYear,

         int bookCode,

        String bookType,

        Date registration_date,


         Set<LibraryEntity> libraries
) {
}
