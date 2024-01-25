package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.maurlox21.cryptoalert.web.dto.PageableDto;

public class PageableMapper {
    public static PageableDto toDto(Page page) {
        return new ModelMapper().map(page, PageableDto.class);
    }
}
