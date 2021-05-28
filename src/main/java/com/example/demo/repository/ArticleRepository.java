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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
	 * @return　IDの降順で記事一覧情報を返します。
	 */
	public List<Article> loadAllArticle(){
		
		String sql = "Select a.id, a.name, a.content, c.id, c.content, c.name "
				+ "from " + ARTICLE_TABLE +", " + COMMENT_TABLE + 
				" where a.id = c.article_id "
				+ "ORDER BY a.id DESC, c.id DESC;";
		
		List<Article> articles = template.query(sql,new ResultSetExtractor<List<Article>>(){
			@Override
			public List<Article> extractData(ResultSet rs) throws SQLException, DataAccessException {

				Map<Integer, Integer> checkedMap = new HashMap<>();
				List<Article> articles = new ArrayList<>();
				
				while(rs.next()) {
					
					Comment comment = new Comment();
					Integer articleID = rs.getInt("a.id");
					comment.setArticle_id(rs.getInt("c.article_id"));
					comment.setId(rs.getInt("c.id"));
					comment.setContent(rs.getString("c.content"));
					comment.setName(rs.getString("c.name"));
					if (checkedMap.get(articleID)==null) {
						checkedMap.put(articleID, 1);
						Article article = new Article();
						article.setId(articleID);
						article.addComment(comment);
						article.setContent(rs.getString("a.content"));
						article.setName(rs.getString("a.name"));
						articles.add(article);
					}
				}
				return articles;
			}
		});
		
		return articles;
	}
}
