package ru.geekbrains.march.market.core.converters;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.api.ProductDto;

@Component
public class PageConverter {
    public PageDto entityToDto(Page<ProductDto> p) {
        PageDto pageDto = new PageDto();
        pageDto.setProducts(p.getContent());
        pageDto.setTotalPages(p.getTotalPages());
        pageDto.setTotalElements((int) p.getTotalElements());
        return pageDto;
    }
}
