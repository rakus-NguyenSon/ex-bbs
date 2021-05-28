package com.example.demo.domain;

/**
 * コメント情報を表すドメインクラスです．
 * 
 * @author nhson
 *
 */
public class Comment {
	
	/**　ID */
	private Integer id;
	
	/**　コメント投稿者名 */
	private String name;
	
	/**　内容 */
	private String content;
	
	/**　コメントした記事のID */
	private Integer articleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticle_id(Integer articleId) { 
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", article_id=" + articleId + "]";
	}
	
	
}
