package com.ac.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ac.entity.Article;
import com.ac.repository.ArticleRepository;
import com.ac.service.WebsiteCrawlerService;

@Controller
public class HomeController {

	@Autowired
	private WebsiteCrawlerService websiteCrawlerService;

	@Autowired
	private ArticleRepository articleRepository;

	@RequestMapping("/")
	public String list(Model model, Principal principal) {
		model.addAttribute("LoggedInUser", principal != null ? principal.getName() : null);
		return "index";
	}

	@RequestMapping("/medium")
	public String getArticlesByTag(Model model, Principal principal) {
		model.addAttribute("LoggedInUser", principal != null ? principal.getName() : null);
		return "list-articles";
	}

	@RequestMapping("/medium/search")
	public String getArticlesByTag(@RequestParam("tag") String tag, Model model, Principal principal) {
		model.addAttribute("LoggedInUser", principal != null ? principal.getName() : null);
		if (tag.trim().isEmpty()) {
			return "list-articles";
		} else {
			Set<Article> articles = websiteCrawlerService.getArticlesByTag(tag);
			model.addAttribute("Articles", articles);
			model.addAttribute("Tag", tag);
			return "list-articles";
		}
	}

	@RequestMapping("/medium/view")
	public String getArticleByLink(@RequestParam("creator") String creator, @RequestParam("title") String title,
			Model model, Principal principal) {
		model.addAttribute("LoggedInUser", principal != null ? principal.getName() : null);
		Article article = websiteCrawlerService.fillArticle(articleRepository.getArticleByCreatorTitle(creator, title));
		model.addAttribute("Article", article);
		return "view-article";
	}
}
