package com.justpickup.storeservice.domain.item.service;

import com.justpickup.storeservice.domain.category.exception.NotFoundStoreException;
import com.justpickup.storeservice.domain.item.dto.ItemDto;
import com.justpickup.storeservice.domain.item.dto.ItemSearch;
import com.justpickup.storeservice.domain.item.entity.Item;
import com.justpickup.storeservice.domain.item.exception.NotExistItemException;
import com.justpickup.storeservice.domain.item.repository.ItemRepository;
import com.justpickup.storeservice.domain.item.repository.ItemRepositoryCustom;
import com.justpickup.storeservice.domain.store.entity.Store;
import com.justpickup.storeservice.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositoryCustom itemRepositoryCustom;
    private final StoreRepository storeRepository;


    @Override
    public ItemDto findItemByItemId(Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotExistItemException("존재하지 않는 아이템 입니다."));

        return ItemDto.createItemDto(findItem);
    }

    @Override
    public Page<ItemDto> findItemList( Long storeId,String word, Pageable pageable) {

        Page<Item> itemList = itemRepositoryCustom.findItem(storeId,word,pageable);
        return PageableExecutionUtils.getPage(itemList.stream()
                .map(ItemDto::createWithCategoryItemDto)
                .collect(Collectors.toList()),pageable,itemList::getTotalElements);
    }


}
