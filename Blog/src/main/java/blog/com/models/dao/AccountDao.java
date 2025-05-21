package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	// 保存処理と更新処理 insertとupdate
	Account save(Account account);

	// SELECT * FROM WHERE mail_address = ?
	// 用途:ユーザーの登録処理をするときに、同じメールアドレスがあったらば登録させないようにする
	// 1行だけしかレコードは取得できない
	Account findByMailAddress(String mailAddress);

	// SELECT * FROM account WHERE mail_address=? AND password=?
	// 用途:ログイン処理に使用。入力したメールアドレスとパスワードが一致してるときだけデータを取得する
	Account findByMailAddressAndPassword(String mailAddress, String password);
}
