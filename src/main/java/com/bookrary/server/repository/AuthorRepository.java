package com.bookrary.server.repository;

import com.bookrary.server.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query(value = "with adverts_of_sales as " +
            "(select author_id as authorid, count(author_id) as authorcount from advert where id in (select advert_id from sale) " +
            "group by author_id)" +
            "select * from author, adverts_of_sales where author.id = adverts_of_sales.authorid " +
            "order by adverts_of_sales.authorcount desc;", nativeQuery = true)
    List<Author> getMostSoldAuthors();
}
