package com.sap.datacloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.spring.CamelEndpointFactoryBean;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.datacloud.exception.DataCloudEndpointCreationExcepton;
import com.sap.datacloud.exception.RouteStartException;
import com.sap.datacloud.exception.RouteStopException;
import com.sap.datacloud.model.CamelAPIResponse;
import com.sap.datacloud.model.DataProvider;
import com.sap.datacloud.model.RouteDefinition;
import com.sap.datacloud.service.RouteCreator;
import com.sap.datacloud.util.OnboardAppUtil;

@RestController
@RequestMapping("/onboard")
public class CamelRouteMonitorController {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private RouteCreator routeCreater;

	@Value("${route.create.message}")
	private String routeCreateMessage;

	@Value("${route.stop.message}")
	private String routeStopMessage;

	@Value("${route.start.message}")
	private String routeStartMessage;

	@RequestMapping(value = "/endpoints", method = RequestMethod.GET)
	public List<RouteDefinition> getEndpointDetails() {

		List<RouteDefinition> routeList = new ArrayList<>();

		camelContext.getRoutes().forEach(route -> {
			RouteDefinition def = new RouteDefinition();
			def.setRouteId(route.getId());
			def.setEndpoint(route.getEndpoint().getEndpointUri());
			def.setStatus(camelContext.getRouteStatus(route.getId()).toString());
			routeList.add(def);
		});

		return routeList;

	}

	@RequestMapping(value = "/route/start/{routeID}", method = RequestMethod.GET)
	public CamelAPIResponse startRoute(@PathVariable String routeID) {

		CamelAPIResponse response = new CamelAPIResponse();
		try {
			camelContext.startRoute(routeID);
			response.setStatusCode(String.valueOf(HttpStatus.SC_OK));
			response.setStatusMessage(routeStartMessage);
		} catch (Exception e) {

			throw new RouteStartException(e.getMessage(), e);
		}

		return response;
	}

	@RequestMapping(value = "/route/stop/{routeID}", method = RequestMethod.GET)
	public CamelAPIResponse stopRoute(@PathVariable String routeID) {

		CamelAPIResponse response = new CamelAPIResponse();

		try {
			camelContext.stopRoute(routeID);
			response.setStatusCode(String.valueOf(HttpStatus.SC_OK));
			response.setStatusMessage(routeStopMessage);
		} catch (Exception e) {

			throw new RouteStopException(e.getMessage(), e);
		}

		return response;
	}

	@RequestMapping(value = "/create/routes", method = RequestMethod.POST)
	public CamelAPIResponse addRoutes(@RequestBody String providerInfo) {

		CamelAPIResponse response = new CamelAPIResponse();
		try {
			DataProvider[] dataProviderInfo = OnboardAppUtil.getObjectFromJSON(providerInfo);
			routeCreater.createRoutes(dataProviderInfo);
			response.setStatusCode(String.valueOf(HttpStatus.SC_OK));
			response.setStatusMessage(routeCreateMessage);

		} catch (Exception e) {

			throw new DataCloudEndpointCreationExcepton(e.getMessage(), e);

		}

		return response;

	}

}
