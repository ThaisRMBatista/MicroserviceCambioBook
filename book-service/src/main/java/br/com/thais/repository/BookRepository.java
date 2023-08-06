package br.com.thais.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.thais.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
