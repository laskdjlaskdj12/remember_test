package com.remember.inhoeku.remember_test.dao;

import com.remember.inhoeku.remember_test.dao.Mapper.DriverAccountMapper;
import com.remember.inhoeku.remember_test.domain.dto.RegisterDTO;
import com.remember.inhoeku.remember_test.domain.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DriverAccountDAO implements AccountDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	@Nullable
	public AccountVO getAccountByEmail(String email){
		String sql = "SELECT * FROM driverAccount WHERE email = ?";
		List<AccountVO> accountVOList = jdbcTemplate.query(sql, new DriverAccountMapper(), email);

		if(accountVOList.isEmpty()){
			return null;
		}
		return accountVOList.get(0);
	}

	@Override
	public int register(RegisterDTO registerDTO){
		String sql = "INSERT INTO driverAccount (email, password) VALUES (?, ?)";
		return jdbcTemplate.update(sql, registerDTO.getEmail(), registerDTO.getPassword());
	}
}
