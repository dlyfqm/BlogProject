package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    //account_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	
	//account_name
	private String accountName;
	
	//mail_address
	private String mailAddress;	
	
	//password
	private String password;

	//空のコンストラクタ
	public Account() {
	}

	//コンストラクタ
	public Account(String accountName, String mailAddress, String password) {
		this.accountName = accountName;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	//ゲッターとセッター
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
