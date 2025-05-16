package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
//blog_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	
	//account_idの設定
	private Long accountId;
	
	//category_nameの設定
	private String categoryName;
	
	//blog_titleの設定
	private String blogTitle;
	
	//blog_contentの設定
	private String blogContent;
	
	//blog_imageの設定
	private String blogImage;	

	//空のコンストラクタ
	public Blog() {
	}

	//コンストラクタ
	public Blog(Long accountId, String categoryName, String blogTitle, String blogContent, String blogImage) {
		this.accountId = accountId;
		this.categoryName = categoryName;
		this.blogTitle = blogTitle;
		this.blogContent = blogContent;
		this.blogImage = blogImage;
	}

	//ゲッターとセッター
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}
}