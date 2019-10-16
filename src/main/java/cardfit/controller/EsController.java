package cardfit.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cardfit.biz.EsBiz;

@RestController
public class EsController {

	@RequestMapping("/test")
    public Object test() {
		try {
			//Object a = EsBiz.cardNameSearch();
			//Object a = EsBiz.multiSearch();
			Object a = EsBiz.checkSearch();
			return a;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
}
