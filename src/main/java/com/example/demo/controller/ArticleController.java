package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;
import com.example.demo.form.ArticleForm;
import com.example.demo.form.CommentForm;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;

/**
 * 記事画面の操作を処理用のクラスです．
 * 
 * @author nhson
 *
 */
@Controller
@RequestMapping("")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	/**
	 * 記事を投稿するフォームのセットアップ.
	 * 
	 * @return
	 */
	@ModelAttribute
	public ArticleForm setUpForm() {
		return new ArticleForm();
	}
	
	
	/**
	 * コメントフォームのセットアップ.
	 * 
	 * @return
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	/**
	 * 記事一覧情報を表示される.
	 * 
	 * @param model
	 * @return　記事一覧HTMLファイル名
	 */
	@RequestMapping("")
	public String index(Model model) {
		
		List<Article> articles = articleRepository.loadAllArticle();
		
		model.addAttribute("articles", articles);
		return "article";
	}
	
	/**
	 * 記事を追加用の処理メソッドです.
	 * 
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/postArticle")
	public String insertArticle(@Validated ArticleForm form, BindingResult result, 
			RedirectAttributes redirectAttributes, Model model) {
		
		if(result.hasErrors()) {
			return index(model);
		}
		
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleRepository.insert(article);
		return "redirect:/";
	}
	
	/**
	 * コメントを追加用の処理メソッドです.
	 * 
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/postComment")
	public String insertComment(@Validated CommentForm form, BindingResult result, 
			RedirectAttributes redirectAttributes, Model model) {
		
		if(result.hasErrors()) {
			return index(model);
		}
		
		Comment comment = new Comment();
		BeanUtils.copyProperties(model, comment);
		
		commentRepository.insert(comment);
		return "redirect:/";
	}
}
