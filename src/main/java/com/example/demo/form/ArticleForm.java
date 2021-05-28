package com.example.demo.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * 記事内容入力用のフォームクラスです．
 * 
 * @author nhson
 *
 */
public class ArticleForm {
	
	/**
	 * 投稿者名
	 */
	@NotBlank(message = "投稿者名の名前を入力してください")
	@Length(min=0,max=50,message = "名前は50文字以下にしてください")
	private String name;
	
	/**
	 * 内容
	 */
	@NotBlank(message = "内容を入力してください")
	private String content;
	
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
}
