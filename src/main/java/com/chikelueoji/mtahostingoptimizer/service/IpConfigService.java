package com.chikelueoji.mtahostingoptimizer.service;

import java.util.List;

import com.chikelueoji.mtahostingoptimizer.model.IpConfig;

public interface IpConfigService {

	List<IpConfig> getInefficientHosts(String activeIPsCount);

}