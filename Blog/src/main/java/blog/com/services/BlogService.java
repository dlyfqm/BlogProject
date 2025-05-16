package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	// 商品一覧のチェック
	// もしaccountId==null 戻り値としてnull
	// findAll内容をコントローラークラスに渡す
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}
	
	//商品の登録処理チェック
	//もし、findByBlogTitleが==nullだったら、
	//保存処理
	//true
	//そうでない場合
	//false
	public boolean createBlog(Long accountId,
			String categoryName,
			String blogTitle,
			String blogContent,
			String blogImage) {
		if(blogDao.findByBlogTitle(blogTitle)==null) {
			blogDao.save(new Blog(accountId,categoryName,blogTitle,blogContent,blogImage));
			return true;
		}else {
			return false;
		}
		
	}
	
	//編集画面を表示するときのチェック
	//もし、productId == null　null
	//そうでない場合、
	//findByProductIdの情報をコントローラークラスに渡す
	public Blog blogEditCheck(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}

	//更新処理のチェックの
	
	//もし、productId==nullだったら、更新処理はしない
	//false
	//そうでない場合、
	//更新処理をする
	//コントローラークラスからもらった、productIdを使って、編集する前の、データを取得
	//変更するべきところだけ、セッターを使用してデータの更新をする。
	//trueを返す
	public boolean blogUpdate(Long blogId,
			Long accountId,
			String categoryName,
			String blogTitle,
			String blogContent,
			String blogImage) {
		if(blogId == null) {
			return false;
		}else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setAccountId(accountId);
			blog.setCategoryName(categoryName);
			blog.setBlogTitle(blogTitle);
			blog.setBlogContent(blogContent);
			blog.setBlogImage(blogImage);			
			blogDao.save(blog);
			return true;
		}
	}
	
	//削除処理のチェック
	//もし、コントローラーからもらったproductIdがnullであれば
	//削除できないこと false
	//そうでない場合
	//deleteByProductIdを使用して商品の削除
	//true
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
