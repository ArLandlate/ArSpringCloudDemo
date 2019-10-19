package org.ar.demo.springcloud.rabbitmq.sender.controller;

import com.alibaba.fastjson.JSONException;
import org.ar.demo.springcloud.core.configuration.BaseConstant;
import org.ar.demo.springcloud.core.enums.ResponseJsonTemplate;
import org.ar.demo.springcloud.core.enums.ResultContainer;
import org.ar.demo.springcloud.rabbitmq.sender.util.SendUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq/sender/test")
public class SenderController {
	
	/**
	 * @author ArLandlate
	 * function: 控制层：测试发送者
	 */

	/**
	 * container declaration
	 */
	@Autowired
	private SendUtil sendUtil;

	/**
	 * test send message
	 * http://127.0.0.1:8421/rabbitmq/sender/test/doSendMessage?message=anyway
	 */
	@GetMapping(value="/doSendMessage")
	public ResultContainer doSendMessage(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
            System.out.println("收到请求: " + message);
            sendUtil.sendMessage(message);
			return resultbody.rwData(BaseConstant.getRJT_DATA_VALIDATE_TRUE())
					.rwDescription("入队成功").compile();
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

}
