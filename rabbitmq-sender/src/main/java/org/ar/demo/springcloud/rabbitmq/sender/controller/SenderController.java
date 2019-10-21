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

import java.util.UUID;

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
	 * (simple send and listen test)
	 * test address: http://127.0.0.1:8421/rabbitmq/sender/test/doSendMessage?message=anyway
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

	/**
	 * test send message and handle the case where the call to server fails
	 *
	 * note: 消息发送失败分两种情况: 1-从服务器到交换机(exchange)的过程中失败、2-从交换机(exchange)向队列路由的过程中失败
	 * 	情况1只会在开启发送方确认的时候返回boolean值；情况2才会进行失败回调，处理这两种情况都需要开启发送方确认模式
	 * note: There are two cases of failure: 1-in process from server to exchange, 2-in process from exchange to message queues
	 * test address: http://127.0.0.1:8421/rabbitmq/sender/test/doSendMessageAndHandleReturnCallBack?message=anyway
	 */
	@GetMapping(value="/doSendMessageAndHandleReturnCallBack")
	public ResultContainer doSendMessageAndHandleReturnCallBack(String message){
		ResultContainer resultbody = ResultContainer.getSuccessInstance();
		try {
			String messageId = UUID.randomUUID().toString();

            System.out.println("收到请求: " + message);
			System.out.println("创建id: " + messageId);

            // send a routing key that does not exist for test
            sendUtil.sendMessageOpenPublisherConfirms(message, "nothing", messageId);

			return resultbody.rwData(BaseConstant.getRJT_DATA_VALIDATE_TRUE())
					.rwDescription("消息发送成功，等待入队").compile();
		}catch (JSONException e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR301).rwDescription(e.getMessage()).compile();
		}catch (Exception e) {
			return resultbody.transform(ResponseJsonTemplate.ERROR500).rwDescription(e.getMessage()).compile();
		}
	}

}
