package com.project.vendora.admin.service.impl;

import com.project.vendora.admin.dto.response.ProductResponseDto;
import com.project.vendora.admin.dto.response.StoreResponseDto;
import com.project.vendora.admin.model.request.ProductRequest;
import com.project.vendora.admin.model.request.StoreRequest;
import com.project.vendora.admin.repository.CategoryRepository;
import com.project.vendora.admin.repository.ProductRepository;
import com.project.vendora.admin.repository.StoreRepository;
import com.project.vendora.admin.service.FlywayService;
import com.project.vendora.admin.service.StoreService;
import com.project.vendora.core.entity.Category;
import com.project.vendora.core.entity.Product;
import com.project.vendora.core.entity.Store;
import com.project.vendora.core.entity.User;
import com.project.vendora.core.repository.UserRepository;
import com.project.vendora.core.utility.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final Mapper mapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FlywayService flywayService;

    @Override
    public StoreResponseDto addStore(StoreRequest storeRequest) {
        Optional<User> userOpt = userRepository.findById(storeRequest.getOwnerId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Owner account not found.");
        }

        Store store = mapper.convert(storeRequest, Store.class);
        store.setOwner(userOpt.get());

        Store savedStore = storeRepository.save(store);
        flywayService.createTenant(savedStore.getStoreName().replace(" ", "_").toLowerCase());
        return mapper.convert(savedStore, StoreResponseDto.class);
    }

    @Override
    @Transactional
    public ProductResponseDto addProduct(ProductRequest productRequest) {
        Optional<Store> storeOpt = storeRepository.findById(productRequest.getStoreId());
        if (storeOpt.isEmpty()) {
            throw new RuntimeException("Store not found.");
        }

        Optional<Category> categoryOpt = categoryRepository.findById(productRequest.getCategoryId());
        if (categoryOpt.isEmpty()) {
            throw new RuntimeException("Category not found.");
        }

        Product product = mapper.convert(productRequest, Product.class);
        product.setStore(storeOpt.get());
        product.setCategory(categoryOpt.get());

        Product savedProduct = productRepository.save(product);
        return mapper.convert(savedProduct, ProductResponseDto.class);
    }

}
