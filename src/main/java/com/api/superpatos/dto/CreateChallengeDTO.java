package com.api.superpatos.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.api.superpatos.enums.Squad;
import com.api.superpatos.model.Tag;

public record CreateChallengeDTO (
    String title,
    String description_interface,
    String description,
    String difficult,
    List<Tag> tags,
    Squad squad,
    String link_img,

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date date
) {

}
