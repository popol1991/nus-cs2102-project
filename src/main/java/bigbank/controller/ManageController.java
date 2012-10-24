package bigbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/manage")
public class ManageController {

	@RequestMapping(method = RequestMethod.GET)
	public String doNothing() {
		return "listAccounts";
	}
}
