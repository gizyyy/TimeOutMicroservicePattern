package com.example.comment.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.comment.entity.ProductCommentDto;

@Service
public class CommentService {

	private Map<Integer, ProductCommentDto> map;

	@PostConstruct
	private void init() {

		// product 1
		ProductCommentDto ratingDto1 = ProductCommentDto
				.of(List.of(com.example.comment.entity.ReviewDto.of("gizem", "commenter", 1, "apple was bad")));

		// product 2
		ProductCommentDto ratingDto2 = ProductCommentDto
				.of(List.of(com.example.comment.entity.ReviewDto.of("gizem", "commenter", 2, "banana was good")));

		// map as db
		this.map = Map.of(1, ratingDto1, 2, ratingDto2);

	}

	public ProductCommentDto getReviewForProduct(int productId) {
		return this.map.getOrDefault(productId, new ProductCommentDto());
	}

}
