package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	
	//保存処理(登録処理)
	//もし、findByAdminEmail==nullだったら登録処理をします。
	//saveメソッドを使用して登録処理をする
	//保存ができたらtrue
	//そうでない場合、保存処理失敗 false
	
	public boolean createAccount(String accountName, String mailAddress, String password) {
		if(accountDao.findByMailAddress(mailAddress)==null) {
			accountDao.save(new Account(accountName,mailAddress,password));
			return true;
		}else {
			return false;
		}
	}
	
	//ログイン処理
	//もし、emailとpasswordがfindByAdminEmailAndPasswordを使用して存在しなかった場合==nullの場合、
	//その場合は、存在しないnullであることをコントローラークラスに知らせる
	//そうでない場合ログインしている人の情報をコントローラークラスに渡す
	
	public Account loginCheck(String mailAddress, String password) {
		Account account = accountDao.findByMailAddressAndPassword(mailAddress, password);
		if(account == null) {
			return null;
		}else {
			return account;
		}
	}
}
