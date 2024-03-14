package com.example.foodthought.repository;

import com.example.foodthought.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryCustom {
    Page<Book> findByTitle(String title, Pageable pageable);
}
