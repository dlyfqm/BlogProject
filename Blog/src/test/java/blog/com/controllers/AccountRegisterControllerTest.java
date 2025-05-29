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

import blog.com.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountRegisterControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService accountService;

	// サービスクラスを使ったデータ作成
	@BeforeEach
	public void prepareData() {
		// 登録できる場合 "Akemi", "ake@test.com", "1234abcd" true
		when(accountService.createAccount("Akemi", "ake@test.com", "1234abcd")).thenReturn(true);
		// ログインが失敗： mailAddress "Ana@test.com"と等しい、 パスワードはどんな値でもいい false
		when(accountService.createAccount(any(), eq("Ana@test.com"), any())).thenReturn(false);
	}

	// 登録画面が正常表示できるテスト
	@Test
	public void testGetAccountRegisterPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/account/register");

		mockMvc.perform(request).andExpect(view().name("account_register.html"));
	}

	// ユーザーの登録が成功するかのテスト
	@Test
	public void testAccountRegisterProcess_NewAccount_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/account/register/process").param("accountName", "Akemi")
				.param("mailAddress", "ake@test.com").param("password", "1234abcd");

		mockMvc.perform(request).andExpect(view().name("account_login.html"));
	}

	// ユーザーの登録が失敗するかのテスト
	@Test
	public void testAccountRegisterProcess_ExistingMailAddress_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/account/register/process").param("accountName", "Ana").param("mailAddress", "Ana@test.com").param("password", "1234");

		mockMvc.perform(request).andExpect(view().name("account_register.html"));
	}
	
	// ログイン画面が正常表示できるテスト
	@Test
	public void testShowLoginForm_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/login");

		mockMvc.perform(request).andExpect(view().name("account_login.html"));
	}

}
