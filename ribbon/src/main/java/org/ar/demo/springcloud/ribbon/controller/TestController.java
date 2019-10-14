package org.ar.demo.springcloud.ribbon.controller;

import com.alibaba.fastjson.JSONException;
import org.ar.demo.springcloud.core.configuration.BaseConstant;
import org.ar.demo.springcloud.core.enums.ResponseJsonTemplate;
import org.ar.demo.springcloud.core.enums.ResultContainer;
import org.ar.demo.springcloud.ribbon.configuration.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ribbon/test")
public class TestController {
	
	/**
	 * @author ArLandlate
	 * useful: 控制层：测试控制器
	 */

	/**
	 * container declaration
	 */
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ServiceInfo serviceInfo;

	/**
	 * test
	 * test address:
	 * http://127.0.0.1:8443/ribbon/test/doTest?message=anyway
	 */
	@GetMapping(value="/doTest")
	public ResultContainer doTest(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
            System.out.println("收到请求: " + message);
			return resultbody.rwData(BaseConstant.getRJT_DATA_VALIDATE_TRUE()).rwDescription("响应请求: " + message).compile();
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

	/**
	 * test
	 * test address:
	 * http://127.0.0.1:8443/ribbon/test/doGetPower?message=anyway
	 */
	@GetMapping(value="/doGetPower")
	public ResultContainer doGetPower(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
			String url = "http://" + serviceInfo.POWER_NAME + "/power/test/doGetPower?message=" + (null!=message?message:"");
			return restTemplate.getForObject(url, ResultContainer.class);
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

	/**
	 * test
	 * test address:
	 * http://127.0.0.1:8443/ribbon/test/doGetOrder?message=anyway
	 */
	@GetMapping(value="/doGetOrder")
	public ResultContainer doGetOrder(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
			String url = "http://" + serviceInfo.ORDER_NAME + "/order/test/doGetOrder?message=" + (null!=message?message:"");
			return restTemplate.getForObject(url, ResultContainer.class);
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}
	
}
