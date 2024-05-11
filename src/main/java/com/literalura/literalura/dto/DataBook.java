package com.literalura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(

        @JsonAlias("id") Long id,

        @JsonAlias("title") String title,

        @JsonAlias("languages") String language,

        @JsonAlias("download_count") int totalDownloads,

        @JsonAlias("authors") List<DataAuthor> authors

) {

}
