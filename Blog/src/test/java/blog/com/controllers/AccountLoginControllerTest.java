package blog.com.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;
import blog.com.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountLoginControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	// サービスクラスを使ったデータ作成
	@BeforeEach
	public void prepareData() {
		// ログインが成功： mailAddress "ake@test.com"、 password "1234abcd" true
		Account validAccount = new Account();
		validAccount.setMailAddress("ake@test.com");
		validAccount.setPassword("1234abcd");
		when(accountService.loginCheck("ake@test.com", "1234abcd")).thenReturn(validAccount);
		// ログインが失敗： mailAddress "Ana@test.com"と等しい、 パスワードはどんな値でもいい false
		when(accountService.loginCheck(eq("Ana@test.com"), any())).thenReturn(null);
	}

	// ログインページを正しく取得するテスト
	@Test
	public void testGetAccountLoginPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/account/login");

		mockMvc.perform(request).andExpect(view().name("account_login.html"));
	}

	// ログインが成功した場合のテスト
	// ログインが成功したら「redirect:/blog/list」に遷移して、入力された値が渡されているかのテスト
	@Test
	public void testAccountLoginProcess_Account_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/account/login/process")
				.param("mailAddress", "ake@test.com").param("password", "1234abcd");

		mockMvc.perform(request).andExpect(view().name("redirect:/blog/list"));
	}

	// ログインが失敗した場合のテスト
	@Test
	public void testAccountLoginProcess_Account_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/account/login/process").param("mailAddress", "Ana@test.com").param("password",
				"1234");

		mockMvc.perform(request).andExpect(view().name("account_login.html"));
	}
	
	// 登録ページを正しく取得するテスト
	@Test
	public void testShowRegistrationForm_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/register");

		mockMvc.perform(request).andExpect(view().name("account_register.html"));
	}

}
