package com.example.product.service;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.entity.Product;
import com.example.product.entity.ProductDto;

@Service
public class ProductService {

	private Map<Integer, Product> map;

	@Autowired
	private CommentServiceClient commentServiceClient;

	@PostConstruct
	private void init() {
		this.map = Map.of(1, Product.of(1, "Apple", 12.45), 2, Product.of(2, "Banana", 12.12));
	}

	public CompletionStage<ProductDto> getProductWithComment(int productId) {
		return this.commentServiceClient.getProductCommentDto(productId).thenApply(productRatingDto -> {
			Product product = this.map.get(productId);
			return ProductDto.of(productId, product.getDescription(), product.getPrice(), productRatingDto);
		});

	}

}
