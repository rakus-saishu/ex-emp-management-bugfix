package com.example.form;


import com.example.validation.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message = "#{NotBlank.name}")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "#{NotBlank.mailAddress}")
	@Email(message = "#{Email.mailAddress}")
	@UniqueEmail(message = "#{UniqueEmail.mailAddress}")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "#{NotBlank.password}")
	@Size(min = 8, max = 16, message = "#{Size.password}")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
