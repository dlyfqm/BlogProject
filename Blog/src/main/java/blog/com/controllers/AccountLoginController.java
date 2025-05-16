package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLoginController {
	@Autowired
	private AccountService accountService;
	
	//Sessionが使えるように宣言
	@Autowired
	private HttpSession session;
	
	//ログイン画面の表示
	@GetMapping("/admin/login")
	public String getAccountLoginPage() {
		return "account_login.html";
	}
	
	//ログイン処理
	@PostMapping("/admin/login/process")
	public String accountLoginProcess(@RequestParam String mailAddress, @RequestParam String password) {
		//loginCheckメソッドを呼び出してその結果をaccountという変数に格納
		Account account = accountService.loginCheck(mailAddress, password);
		//もし、account==nullログイン画面にとどまります。
		//そうでない場合は、sessionにログイン情報に保存
		//商品一覧画面にリダイレクトする/blog/list
		
		if(account == null) {
			return "account_login.html";
		}else {
			session.setAttribute("loginAccountInfo", account);
			return "redirect:/blog/list";			
		}
	}
	
	//登録画面へ
	@GetMapping("/register")
    public String showRegistrationForm() {
        return "account_register.html";
    }
}
