package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 記事情報を表すドメインクラスです．
 * 
 * @author nhson
 *
 */
public class Article {
	
	/**　ID */
	private Integer id;
	
	/**　投稿者名 */
	private String name;
	
	/**　内容 */
	private String content;
	
	/**　コメントリスト */
	private List<Comment> commentList;

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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	/**
	 * コメントを追加する処理.
	 * 
	 * @param comment
	 */
	public void addComment(Comment comment) {
		if (this.commentList==null) {
			List<Comment> commentList = new ArrayList<>();
			if(comment.getName()!=null) {
				commentList.add(comment);
			}
			this.commentList = commentList;
			return;
		}
	
		this.commentList.add(comment);
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}
	
	
}
