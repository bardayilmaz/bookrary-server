package com.bookrary.server.model.response;

import com.bookrary.server.entity.BookType;
import com.bookrary.server.entity.Sale;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserStatsResponse {

    private int totalSale;
    private int totalPurchased;
    private int totalDonated;
    private List<BookType> mostBoughtBookTypes;
    private List<SaleResponse> purchaseDonationHistory;
    private List<BookType> mostSoldBookTypes;

    public static UserStatsResponse fromStats(int totalSale, int totalPurchased, int totalDonated, List<BookType> mostBoughtBookTypes,
                                              List<Sale> purchaseDonationHistory, List<BookType> mostSoldBookTypes) {
        return UserStatsResponse.builder()
                .totalSale(totalSale)
                .totalPurchased(totalPurchased)
                .totalDonated(totalDonated)
                .purchaseDonationHistory(purchaseDonationHistory.stream().map(SaleResponse::fromEntity).collect(Collectors.toList()))
                .mostBoughtBookTypes(mostBoughtBookTypes)
                .mostSoldBookTypes(mostSoldBookTypes)
                .build();
    }
}
