package com.chikelueoji.mtahostingoptimizer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chikelueoji.mtahostingoptimizer.model.IpConfig;
import com.chikelueoji.mtahostingoptimizer.repository.IpConfigJdbcRepository;

@RestController
@RequestMapping("/hosts")
public class IpConfigRestController {

	@Autowired
	IpConfigJdbcRepository ipConfigJdbcRepository;
	
	@Value("${X}")
	private String activeIPsCount;
	
	@RequestMapping("/inefficient")
	public @ResponseBody List<IpConfig> getInefficientHosts()
	{
		return ipConfigJdbcRepository.findInefficientHosts(activeIPsCount);
	}
}
