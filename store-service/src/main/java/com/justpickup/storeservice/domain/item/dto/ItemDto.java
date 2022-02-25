package com.justpickup.storeservice.domain.item.dto;

import com.justpickup.storeservice.domain.category.dto.CategoryDto;
import com.justpickup.storeservice.domain.item.entity.Item;
import com.justpickup.storeservice.global.entity.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    private Long id;

    private String name;

    private Yn salesYn;

    private Long price;

    private CategoryDto categoryDto;

    /*
     private PhotoDto photoDto;
     private StoreDto storeDto;
     private List<ItemOptionDto> itemOptionDtoList;
    */

    // == 생성 메소드 == //
    public ItemDto(Long id, String name, Yn salesYn, Long price) {
        this.id = id;
        this.name = name;
        this.salesYn = salesYn;
        this.price = price;
    }

    public static ItemDto createItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .salesYn(item.getSalesYn())
                .build();
    }

    public static ItemDto createWithCategoryItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .categoryDto(new CategoryDto(item.getCategory()))
                .price(item.getPrice())
                .salesYn(item.getSalesYn())
                .build();
    }

    // TODO: 2022/02/03 queryDsl 쿼리 생성 시 구현 필요
//    public static ItemDto createFullItemDto(Item item) {
//        return null
//    }

}