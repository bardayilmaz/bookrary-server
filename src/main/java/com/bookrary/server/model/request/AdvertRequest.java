package com.bookrary.server.model.request;

import com.bookrary.server.entity.AdvertStatus;
import com.bookrary.server.entity.BookLanguage;
import com.bookrary.server.entity.BookType;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ToString
public class AdvertRequest {

    @NotNull(message = "fiyat boş bırakılamaz")
    private double price;

    @NotNull(message = "ilan durumu boş bırakılamaz")
    private AdvertStatus advertStatus;

    @NotEmpty(message = "Kitap ismi boş bırakılamaz")
    private String title;

    @Min(1)
    @NotNull(message = "Kitap sayfa sayısı boş bırakılamaz")
    private int pageCount;

    @NotNull(message = "Kitap yayımlanma tarihi boş bırakılamaz")
    private LocalDateTime publicationDate;

    @NotNull(message = "Kitap dili boş bırakılamaz")
    private BookLanguage language;

    @NotNull(message = "Kitap tipi boş bırakılamaz")
    private BookType type;

    @NotEmpty(message = "Yayınevi ismi boş bırakılamaz")
    private String publisherName;

    @NotEmpty(message = "Yazar id boş bırakılamaz")
    private String authorId;

    private String bookPictureId;

}
