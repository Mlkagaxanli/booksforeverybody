package com.developia.booksforeverybody.service.impl;

import com.developia.booksforeverybody.dao.entity.BookEntity;
import com.developia.booksforeverybody.dao.entity.BookStatus;
import com.developia.booksforeverybody.dao.repository.BookRepository;
import com.developia.booksforeverybody.exception.NotFoundException;
import com.developia.booksforeverybody.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findALLByStatusIsNot(BookStatus.DELETED);

    }
    @Override
    public BookEntity getByBookId(Long id) {
        return bookRepository.findByIdAndStatusIsNot(id, BookStatus.DELETED).orElseThrow(
                ()->{
                    throw new NotFoundException("Book not found!");
                }
        );
    }
}
