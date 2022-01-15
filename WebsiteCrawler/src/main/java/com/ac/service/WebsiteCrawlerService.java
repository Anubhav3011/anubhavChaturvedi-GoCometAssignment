package com.ac.service;

import java.util.Set;

import com.ac.entity.Article;

public interface WebsiteCrawlerService {

	public Set<Article> getArticlesByTag(String searchTag);

	public Set<Article> fetchArticlesFromDb(String searchTag);

	public Article fillArticle(Article article);

}