package com.bookrary.server.model.request;

import com.bookrary.server.entity.Advert;
import com.bookrary.server.entity.SaleStatus;
import com.bookrary.server.entity.User;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class SaleRequest {

    @NotEmpty(message = "satıcı id boş olamaz")
    private String sellerId;

    @NotEmpty(message = "alıcı id boş olamaz")
    private String buyerId;

    @NotEmpty(message = "ilan id boş olamaz")
    private String advertId;

    @NotNull(message = "Satış durumu boş bırakılamaz")
    private SaleStatus saleStatus;

}
