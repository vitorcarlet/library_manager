package com.ifc.work.entities;

import java.util.Date;

public record User(Long id,
                   String name,
                   String cpf,
                   Date birth,
                   String gender,
                   int registration,
                   boolean activeUser

                   ) {}
