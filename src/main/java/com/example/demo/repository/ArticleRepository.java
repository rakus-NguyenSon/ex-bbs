package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;

/**
 * 記事に関するテーブルを操作するRepositoryクラスです．
 * 
 * @author nhson
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static String ARTICLE_TABLE = "articles";

	private final static String COMMENT_TABLE = "comments";

	/**
	 * 記事一覧情報を取得します．
	 * 
	 * @return IDの降順で記事一覧情報を返します。
	 */
	public List<Article> loadAllArticle() {

		String sql = "Select a.id as article_id, a.name as article_name, a.content as article_content, c.id as comment_id, c.content as comment_content, c.name as comment_name, c.article_id as comment_id "
				+ "from " + ARTICLE_TABLE + " as a left outer join " + COMMENT_TABLE + " as c on a.id = c.article_id "
				+ "ORDER BY a.id DESC, c.id DESC;";

		List<Article> articles = template.query(sql, new ResultSetExtractor<List<Article>>() {
			@Override
			public List<Article> extractData(ResultSet rs) throws SQLException, DataAccessException {

				Map<Integer, Integer> checkedMap = new HashMap<>();
				List<Article> articles = new ArrayList<>();
//				List<Comment> comments = null;

				while (rs.next()) {

					Comment comment = new Comment();
					Integer articleID = rs.getInt("article_id");

					comment.setArticleId(rs.getInt("comment_id"));
					comment.setId(rs.getInt("comment_id"));
					comment.setContent(rs.getString("comment_content"));
					comment.setName(rs.getString("comment_name"));
					if (checkedMap.get(articleID) == null) {
						checkedMap.put(articleID, 1);
						Article article = new Article();
						article.setId(articleID);
						article.addComment(comment);
						article.setContent(rs.getString("article_content"));
						article.setName(rs.getString("article_name"));
						articles.add(article);

//						if(comment.getName()!=null) {
//							comments = new ArrayList<Comment>();
//						}
					} else {
						articles.get(articles.size() - 1).addComment(comment);
//						comments.add(comment);
//						articles.get(articles.size()-1).setCommentList(comments);

					}
				}
				return articles;
			}
		});

		return articles;
	}

	/**
	 * 新しい記事を追加するメソッドです.
	 * 
	 * @param article 記事オブジェクト
	 */
	public void insert(Article article) {
		
		String sql = "Insert Into " + ARTICLE_TABLE + " (name, content) values (:name,:content);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
	
	/**
	 * idにより記事とコメントを削除するメソッドです.
	 * 
	 * @param id 削除する記事のID
	 */
	public void deleteById(Integer id) {
		String sql = "delete from " + ARTICLE_TABLE + " where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}
}
