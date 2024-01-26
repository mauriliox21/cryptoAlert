package com.maurlox21.cryptoalert.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.maurlox21.cryptoalert.web.dto.PageDto;

public class PageMapper {
    public static PageDto toDto(Page page) {
        return new ModelMapper().map(page, PageDto.class);
    }
}
