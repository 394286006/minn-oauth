package p.minn.security.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import p.minn.common.exception.WebPrivilegeException;
import p.minn.common.utils.ConstantCommon;
import p.minn.security.service.IAccountService;
import p.minn.vo.User;


/**
 * 
 * @author minn
 * @QQ:3942986006
 *
 */
@RestController
public class LoginController {
	
	@Autowired
	private IAccountService accountService;
    @Autowired
    private LocaleResolver localeResolver;
	@RequestMapping(value="login")
	public Object Login(HttpServletRequest req,HttpServletResponse rep,@RequestParam(required=false, defaultValue="zh") String lang){
		Object entity = null;
		try {
			String userName = null;
			User user=null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof User) {
				user=(User)principal;
				userName = user.getUsername();
				if(user.getLanguage()!=null&&!user.getLanguage().equals(""))
				  lang=user.getLanguage().split("_")[0];
				 Locale local=new Locale(lang);
		         localeResolver.setLocale(req, rep, local);
			} else {
				userName = principal.toString();
			}
			if(!userName.equals(ConstantCommon.ANONYMOUSUSER)){
				req.getSession().setAttribute(ConstantCommon.LOGINUSER,user);
				entity=user;
			}else{
				throw new WebPrivilegeException("login fail");
			}
		 } catch (Exception e) {
				entity = new WebPrivilegeException(e.getMessage());
			}
			return entity;
	}

	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public Object logout (HttpServletRequest request, HttpServletResponse response) {
		Object entity = null;
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null){    
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			Map<String,String> rs=new HashMap<String,String>();
			rs.put("info", "logout success");
			entity=rs;
		}catch(Exception e){
			entity = new WebPrivilegeException(e.getMessage());
		}
		
		return entity ;
	}
	
	@RequestMapping(value="/logoutHandler", method = RequestMethod.GET)
	public Object logoutHandler (HttpServletRequest request, HttpServletResponse response) {
		Object entity = null;
		try{
			request.getSession().invalidate();
			Map<String,String> rs=new HashMap<String,String>();
			rs.put("info", "logout success");
			entity=rs;
		}catch(Exception e){
			entity = new WebPrivilegeException(e.getMessage());
		}
		
		return entity ;
	}
	
	@RequestMapping(value="/qrcodeLogin", method = RequestMethod.POST)
    public Object qrcodeLogin (@RequestParam String key,HttpServletRequest request, HttpServletResponse response) {
        Object entity = null;
        try{
            Object user= request.getSession().getAttribute(ConstantCommon.LOGINUSER);
          //  accountService.updateKey(((User)user).getUsername(),key);
            Map<String,String> rs=new HashMap<String,String>();
            rs.put("info", "qrcode set success");
            entity=rs;
        }catch(Exception e){
            entity = new WebPrivilegeException(e.getMessage());
        }
        
        return entity ;
    }

}
