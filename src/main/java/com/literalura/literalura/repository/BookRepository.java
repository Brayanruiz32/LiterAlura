package com.literalura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.literalura.literalura.model.Book;
import java.util.Optional;



public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findById(Long id);





}
