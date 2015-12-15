package p.minn.oauth.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author minn
 * @QQ:3942986006
 *
 */
@Controller
public class OAuth2Controller {
	
	@RequestMapping(value="/oauth2/token", method = RequestMethod.GET)
	public Object token () {
		
		return "oauth/token";
		
	}
	
	@RequestMapping(value="/oauth2/hello", method = RequestMethod.GET)
	public Object hello () {
		
		return "/oauth/oauth";
		
	}

	

}
