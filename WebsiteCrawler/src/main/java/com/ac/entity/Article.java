package com.ac.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private int id;

	@Column(name = "article_link")
	private String link;

	@Column(name = "article_creator")
	private String creator;

	@Column(name = "article_title")
	private String title;

	@Column(name = "article_blog")
	private String blog;

	@Column(name = "article_details")
	private String details;

	@Column(name = "article_fullBlog")
	private String fullBlog;

	@Column(name = "article_searchtag")
	private String searchTag;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "articles_tags", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "articles_responses", joinColumns = @JoinColumn(name = "article_id"), inverseJoinColumns = @JoinColumn(name = "respnse_id"))
	private List<Response> responses;

}
