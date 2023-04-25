package com.bookrary.server.repository;

import com.bookrary.server.entity.City;
import com.bookrary.server.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String> {
    List<Library> findAllByCity(City city);
}
