package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class CommentForm {
	
	private Integer articleId;
	
	@NotBlank(message = "コメント者の名前を入力してください")
	@Length(min=0,max=50,message = "名前は50文字以下にしてください")
	private String name;
	
	@NotBlank(message = "コメント内容を入力してください")
	private String content;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
}
