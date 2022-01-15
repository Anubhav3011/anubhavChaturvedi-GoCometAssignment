package com.ac.serviceImpl;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ac.entity.Article;
import com.ac.repository.ArticleRepository;
import com.ac.service.WebsiteCrawlerService;

@Service
public class WebsiteCrawlerServiceImpl implements WebsiteCrawlerService {

	@Autowired
	private ArticleRepository articleRepository;

	@Value("${website}")
	String url;

	@Override
	public Set<Article> getArticlesByTag(String searchTag) {

		Set<Article> articles = new LinkedHashSet<Article>();

		fetchArticlesFromUrl(searchTag, url + "/tag/" + searchTag);

		articles.addAll(fetchArticlesFromDb(searchTag));

		return articles;
	}

	private void fetchArticlesFromUrl(String searchTag, String url) {

		try {
			Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(30000).referrer("http://google.com")
					.get();
			Elements elements = document.getElementsByClass("fv ar l gq");

			for (Element element : elements) {
				if (articleRepository.getArticleByCreatorTitle(
						element.child(0).getElementsByClass("az em gt gu bz gv ca cb cc cd ce bc gw").get(0).text(),
						element.child(1).getElementsByClass(
								"az be gy gz ha bi hb hc hd bm he hf hg bq hh hi hj bu hk hl hm by bz ca cb hn cd ce bc")
								.get(0).text()) == null) {
					Article article = Article.builder().link(element.child(1)
							.getElementsByClass("ez fa fb fc fd fe ff fg fh fi fj fk fl fm fn").get(0).attr("abs:href")
							.substring(0,
									element.child(1).getElementsByClass("ez fa fb fc fd fe ff fg fh fi fj fk fl fm fn")
											.get(0).attr("abs:href").indexOf("?")))
							.title(element.child(1).getElementsByClass(
									"az be gy gz ha bi hb hc hd bm he hf hg bq hh hi hj bu hk hl hm by bz ca cb hn cd ce bc")
									.get(0).text())
							.creator(element.child(0).getElementsByClass("az em gt gu bz gv ca cb cc cd ce bc gw")
									.get(0).text())
							.blog(element.child(1).getElementsByClass("az b en eo bz ho ca cb hn cd ce fp").get(0)
									.text())
							.details(element.child(1).getElementsByClass("ae t").get(0).text()).searchTag(searchTag)
							.build();
					articleRepository.save(article);
				} else {
					Article article = articleRepository.getArticleByCreatorTitle(
							element.child(0).getElementsByClass("az em gt gu bz gv ca cb cc cd ce bc gw").get(0).text(),
							element.child(1).getElementsByClass(
									"az be gy gz ha bi hb hc hd bm he hf hg bq hh hi hj bu hk hl hm by bz ca cb hn cd ce bc")
									.get(0).text());
					article.setLink(element.child(1).getElementsByClass("ez fa fb fc fd fe ff fg fh fi fj fk fl fm fn")
							.get(0).attr("abs:href").substring(0,
									element.child(1).getElementsByClass("ez fa fb fc fd fe ff fg fh fi fj fk fl fm fn")
											.get(0).attr("abs:href").indexOf("?")));
					article.setBlog(
							element.child(1).getElementsByClass("az b en eo bz ho ca cb hn cd ce fp").get(0).text());
					article.setDetails(element.child(1).getElementsByClass("ae t").get(0).text());
					article.setSearchTag(searchTag);
					articleRepository.save(article);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Set<Article> fetchArticlesFromDb(String searchTag) {
		return articleRepository.findBySearchTagContainsAllIgnoreCase(searchTag);
	}

	@Override
	public Article fillArticle(Article article) {
		try {
			Document document = Jsoup.connect(article.getLink()).userAgent("Mozilla").timeout(30000)
					.referrer("http://google.com").get();
			Element element = document.getElementsByTag("article").get(0);
			article.setFullBlog(element.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return article;
	}

}
