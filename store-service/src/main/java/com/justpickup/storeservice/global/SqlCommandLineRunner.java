package com.justpickup.storeservice.global;

import com.justpickup.storeservice.domain.category.entity.Category;
import com.justpickup.storeservice.domain.category.repository.CategoryRepository;
import com.justpickup.storeservice.domain.favoritestore.entity.FavoriteStore;
import com.justpickup.storeservice.domain.favoritestore.repository.FavoriteStoreRepository;
import com.justpickup.storeservice.domain.item.entity.Item;
import com.justpickup.storeservice.domain.item.repository.ItemRepository;
import com.justpickup.storeservice.domain.itemoption.entity.ItemOption;
import com.justpickup.storeservice.domain.itemoption.entity.OptionType;
import com.justpickup.storeservice.domain.map.entity.Map;
import com.justpickup.storeservice.domain.store.entity.Store;
import com.justpickup.storeservice.domain.store.repository.StoreRepository;
import com.justpickup.storeservice.global.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SqlCommandLineRunner implements CommandLineRunner {

    private final StoreRepository storeRepository;
    private final FavoriteStoreRepository favoriteStoreRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Store> stores = new ArrayList<>();

        createStores(storeRepository, stores);

        createFavoriteStore(favoriteStoreRepository, stores);

        createItemAndCategories(itemRepository, categoryRepository, stores);
    }

    private void createItemAndCategories(ItemRepository itemRepository, CategoryRepository categoryRepository, List<Store> stores) {
        stores.forEach(store -> {
            Category 카페인 = categoryRepository.save(Category.of("카페인", 0, store));
            Category 디카페인 = categoryRepository.save(Category.of("디카페인", 1, store));
            Category 티 = categoryRepository.save(Category.of("티", 2, store));

            ItemOption ice = ItemOption.of(OptionType.REQUIRED, "ICE");
            ItemOption hot = ItemOption.of(OptionType.REQUIRED, "HOT");

            Item 아메리카노 = Item.of("아메리카노", 1500L, 카페인, store, List.of(ice, hot));
            Item 카페라떼 = Item.of("카페라떼", 2000L, 카페인, store, List.of(ice, hot));
            Item 카페모카 = Item.of("카페모카", 3900L, 카페인, store, List.of(ice, hot));
            Item 콜드브루 = Item.of("콜드브루", 2500L, 카페인, store, List.of(ice));
            Item 녹차라떼 = Item.of("녹차라떼", 3000L, 디카페인, store, List.of(ice, hot));
            Item 딸기라떼 = Item.of("딸기라떼", 3000L, 디카페인, store, List.of(ice, hot));
            Item 녹차 = Item.of("녹차", 3000L, 티, store, List.of(hot));
            Item 히비스커스 = Item.of("히비스커스 티", 3000L, 티, store, List.of(hot));
            itemRepository.saveAll(List.of(아메리카노, 카페라떼, 콜드브루, 녹차라떼, 딸기라떼, 녹차, 히비스커스));
        });
    }


    private void createFavoriteStore(FavoriteStoreRepository favoriteStoreRepository, List<Store> stores) {
        List<Long> userList = List.of(1L,2L,3L,4L,5L,6L,7L);
        userList.forEach(userId -> stores.forEach(store -> favoriteStoreRepository.save(FavoriteStore.of(userId, store))));
    }

    private void createStores(StoreRepository storeRepository, List<Store> stores) {
        stores.add(
                Store.of(
                        new Address("서울시", "마포구 도화동", "201-20"),
                        Map.of(37.5398271003404, 126.94769672415691),
                        1L,
                        "커피온리 마포역점"
                )
        );

        stores.add(
                Store.of(
                        new Address("서울시", "마포구 도화동", "50-10"),
                        Map.of(37.54010719003089, 126.94556661330861),
                        2L,
                        "만랩커피 마포점"
                )
        );

        stores.add(
                Store.of(
                        new Address("서울시", "마포구 도화동", "555"),
                        Map.of(37.539797393793755, 126.9453578838543),
                        3L,
                        "이디야커피 마포오벨리스크점"
                )
        );

        stores.add(
                Store.of(
                        new Address("서울시", "영등포구 도림로", "31길 2"),
                        Map.of(37.493033141569505, 126.89593667847592),
                        4L,
                        "이디야커피 대림역점"
                )
        );

        storeRepository.saveAll(stores);
    }
}