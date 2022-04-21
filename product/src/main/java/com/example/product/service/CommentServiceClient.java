package com.example.product.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.product.entity.ProductCommentDto;
import com.example.product.entity.ReviewDto;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class CommentServiceClient {

	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${comment.service.endpoint}")
	private String commentService;

	@TimeLimiter(name = "commentService", fallbackMethod = "getDefault")
	public CompletionStage<ProductCommentDto> getProductCommentDto(int productId) {
		Supplier<ProductCommentDto> supplier = () -> this.restTemplate
				.getForEntity(this.commentService + productId, ProductCommentDto.class).getBody();
		return CompletableFuture.supplyAsync(supplier);
	}

	private CompletionStage<ProductCommentDto> getDefault(int productId, Throwable throwable) {
		System.out.println("Default method run");
		return CompletableFuture.supplyAsync(() -> ProductCommentDto
				.of(List.of(ReviewDto.of("positive", "commenter", productId, "product was so nice"))));
	}

}
