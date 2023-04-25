package com.bookrary.server.repository;

import com.bookrary.server.entity.Advert;
import com.bookrary.server.entity.City;
import com.bookrary.server.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, String> {

    Page<Advert> findByTitleContainingIgnoreCaseAndSellerIn(Pageable pageable, String name, Collection<User> sellers);
}
