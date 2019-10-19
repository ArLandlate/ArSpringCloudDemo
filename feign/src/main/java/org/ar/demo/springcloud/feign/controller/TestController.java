package org.ar.demo.springcloud.feign.controller;

import com.alibaba.fastjson.JSONException;
import org.ar.demo.springcloud.core.configuration.BaseConstant;
import org.ar.demo.springcloud.core.enums.ResponseJsonTemplate;
import org.ar.demo.springcloud.core.enums.ResultContainer;
import org.ar.demo.springcloud.feign.client.OrderTestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/test")
public class TestController {
	
	/**
	 * @author ArLandlate
	 * function: 控制层：测试控制器
	 */

	/**
	 * container declaration
	 */
	@Autowired
	private OrderTestClient orderTestClient;

	/**
	 * test get order
	 * test address:
	 * http://127.0.0.1:8431/feign/test/doGetOrder?message=anyway
	 */
	@GetMapping(value="/doGetOrder")
	public ResultContainer doGetOrder(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
            return orderTestClient.doGetOrder(message);
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

}
