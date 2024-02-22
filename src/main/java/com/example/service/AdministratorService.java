package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者情報を操作するサービス.
 * 
 * @author igamasayuki
 *
 */
@Service
@Transactional
public class AdministratorService {

	private AdministratorRepository administratorRepository;

	//(Sec)PasswordEncoder インタフェース
	@Autowired
	private PasswordEncoder passwordEncoder;

	// (Sec)コンストラクタにPasswordEncoderを追加
    public AdministratorService(
			AdministratorRepository administratorRepository
			, PasswordEncoder passwordEncoder) {
        this.administratorRepository = administratorRepository;
		this.passwordEncoder = passwordEncoder;
    }

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		//(Sec)パスワードをハッシュ化してセット
		String hashedPassword = passwordEncoder.encode(administrator.getPassword());
		administrator.setPassword(hashedPassword);

		administratorRepository.insert(administrator);
	}

	/**
	 * ログインをします.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return 管理者情報 存在しない場合はnullが返ります
	 */
	public Administrator login(String mailAddress) {
		Administrator administrator = administratorRepository.findByMailAddress(mailAddress);
		return administrator;
	}
}
