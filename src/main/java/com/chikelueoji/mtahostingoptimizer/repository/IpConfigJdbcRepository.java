package com.chikelueoji.mtahostingoptimizer.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.chikelueoji.mtahostingoptimizer.model.IpConfig;

@Repository
public class IpConfigJdbcRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<IpConfig> findInefficientHosts(String activeIPsCount)
	{
		String sql = "select hostfqdn from ipconfig where active=\'1\' group by hostfqdn having count(active) <=? "
				+ "union "
				+ "select hostfqdn from ipconfig group by hostfqdn having count(active) <=?";
		
		return jdbcTemplate.query(sql, new IpConfigRowMapper(), new Object[]{activeIPsCount, activeIPsCount});
		
	}
	
    class IpConfigRowMapper implements RowMapper < IpConfig > {

        @Override
        public IpConfig mapRow(ResultSet rs, int rowNum) throws SQLException {

        	IpConfig ipConfig = new IpConfig();

        	ipConfig.setHostfqdn(rs.getString("hostfqdn"));

            return ipConfig;
        }

    }
}
