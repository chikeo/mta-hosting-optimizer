package com.chikelueoji.mtahostingoptimizer.repository;

import java.util.List;

import com.chikelueoji.mtahostingoptimizer.model.IpConfig;

public interface IpConfigJdbcRepository {

	List<IpConfig> getInefficientHosts(String activeIPsCount);

}