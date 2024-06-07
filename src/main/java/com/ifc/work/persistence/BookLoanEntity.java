package com.ifc.work.persistence;

import com.ifc.work.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_bookloans")
public class BookLoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @OneToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "user_id_fk")
    private UserEntity user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id_fk")
    private BookEntity book;

    @Column(name="loan_date")
    private Date loanDate;

    @Column(name="loan_duration")
    private int loanDuration;

    @Column(name="return_loan_date")
    private Date returnLoanDate;

    @Column(name="returned")
    private boolean returned;


}
