package com.chikelueoji.mtahostingoptimizer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chikelueoji.mtahostingoptimizer.model.IpConfig;
import com.chikelueoji.mtahostingoptimizer.repository.IpConfigJdbcRepository;

@Service
public class IpConfigServiceImpl implements IpConfigService {

	@Autowired
	IpConfigJdbcRepository ipConfigJdbcRepository;
	
	/* (non-Javadoc)
	 * @see com.chikelueoji.mtahostingoptimizer.service.IpConfigService#getInefficientHosts(java.lang.String)
	 */
	@Override
	public List<IpConfig> getInefficientHosts(String activeIPsCount)
	{
		return ipConfigJdbcRepository.getInefficientHosts(activeIPsCount);
	}
}
