package org.ar.demo.springcloud.rabbitmq.listener.controller;

import com.alibaba.fastjson.JSONException;
import org.ar.demo.springcloud.core.configuration.BaseConstant;
import org.ar.demo.springcloud.core.enums.ResponseJsonTemplate;
import org.ar.demo.springcloud.core.enums.ResultContainer;
import org.ar.demo.springcloud.rabbitmq.listener.util.ListenUtil;
import org.ar.demo.springcloud.rabbitmq.listener.util.MessageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping("/rabbitmq/listener/test")
public class ListenerController {
	
	/**
	 * @author ArLandlate
	 * function: 控制层：测试监听者
	 */

	/**
	 * container declaration
	 */
	@Autowired
	private ListenUtil listenUtil;

	/**
	 * test listener message
	 * (simple send and listen test)
	 * test address: http://127.0.0.1:8411/rabbitmq/listener/test/feignHandleAllMessageInTheQueue
	 */
	@GetMapping(value="/feignHandleAllMessageInTheQueue")
	public ResultContainer feignHandleAllMessageInTheQueue(){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
			LinkedList<String> messages = MessageCache.messageList;
            System.out.println("----------以下是该队列中的所有消息: ----------");
            while(0< messages.size()){
				System.out.println(messages.removeFirst());
			}
			return resultbody.rwData(BaseConstant.getRJT_DATA_VALIDATE_TRUE())
					.rwDescription("消息导出成功，详情请查看控制台").compile();
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

}
