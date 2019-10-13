package org.ar.demo.springcloud.order.controller;

import com.alibaba.fastjson.JSONException;
import org.ar.demo.springcloud.order.configuration.BaseConstant;
import org.ar.demo.springcloud.order.enums.ResponseJsonTemplate;
import org.ar.demo.springcloud.order.enums.ResultContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/test")
public class TestController {
	
	/**
	 * @author ArLandlate
	 * useful: 控制层：测试控制器
	 */

	/**
	 * container declaration
	 */
//	@Autowired
//	private RestTemplate restTemplate;

	/**
	 * test get order
	 */
	@GetMapping(value="/doGetOrder")
	public ResultContainer doGetOrder(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
            System.out.println("收到请求: " + message);
			return resultbody.rwData(BaseConstant.getRJT_DATA_VALIDATE_TRUE())
					.rwDescription("响应请求: " + message + "\n来自" + "order-node01" + "的响应").compile();
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

}
