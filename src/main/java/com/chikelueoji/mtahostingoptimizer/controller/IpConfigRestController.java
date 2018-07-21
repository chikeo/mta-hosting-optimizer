package com.chikelueoji.mtahostingoptimizer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chikelueoji.mtahostingoptimizer.model.IpConfig;
import com.chikelueoji.mtahostingoptimizer.service.IpConfigService;

@RestController
@RequestMapping("/hosts")
public class IpConfigRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IpConfigRestController.class);
	
	@Autowired
	IpConfigService ipConfigService;
	
	@Value("${X}")
	private String activeIPsCount;
	
	@RequestMapping("/inefficient")
	public List<IpConfig> getInefficientHosts()
	{
		LOGGER.debug("Retrieving the list of inefficient IP address hosts...");
		return ipConfigService.getInefficientHosts(activeIPsCount);
	}
}
