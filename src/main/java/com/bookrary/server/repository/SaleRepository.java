package com.bookrary.server.repository;

import com.bookrary.server.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {
    boolean existsByAdvert(Advert advert);

    int countBySeller(User user);

    int countByBuyerAndSaleStatus(User user, SaleStatus saleStatus);

    List<Sale> findAllByBuyer(User user);

    @Query(value = "select book_type from advert where advert.id in " +
            "(select advert_id from sale where sale.buyer_id = :buyerId) " +
            "group by book_type order by count(book_type) desc;",
            nativeQuery = true)
    List<BookType> mostPurchasedDonatedGenres(String buyerId);

    @Query(value = "select book_type from advert where advert.id in " +
            "(select advert_id from sale where sale.seller_id = :sellerId) " +
            "group by book_type order by count(book_type) desc;",
            nativeQuery = true)
    List<BookType> mostSoldGenresOfSeller(String sellerId);

    @Query(value = "select book_type from advert where advert.id in " +
            "(select advert_id from sale) " +
            "group by book_type order by count(book_type) desc;",
            nativeQuery = true)
    List<BookType> getMostSoldGenres();

    List<Sale> findFirst10ByBuyerOrderByCreatedDesc(User user);
}
