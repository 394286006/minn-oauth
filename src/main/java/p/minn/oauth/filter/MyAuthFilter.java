package p.minn.oauth.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.LocaleResolver;

import p.minn.oauth.service.MyAuthService;

/**
 * 
 * @author minn
 * @QQ:3942986006
 *
 */
public class MyAuthFilter implements Filter {

	private MyAuthService myAuthService;
	
    private LocaleResolver localeResolver;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
       ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()); 
       myAuthService=(MyAuthService) ctx.getBean("myAuthService");
       localeResolver=(LocaleResolver) ctx.getBean("localeResolver");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8"); 
        String accessKey = httpRequest.getParameter("accessKey");
        String secretKey=httpRequest.getParameter("secretKey");
        String id=httpRequest.getParameter("id");
        String userName=httpRequest.getParameter("userName");
   
        if(StringUtils.isBlank(accessKey)) {
        	  throw new RuntimeException("accessKey can not be null");
        }
        Map logininfo=myAuthService.getSub(secretKey, accessKey);
        String lang =logininfo.get("language").toString().split("_")[0];
        boolean isAuth=myAuthService.checkToken(logininfo, id, userName, lang);
        if(isAuth) {
          	 Locale local=new Locale(lang);
	         localeResolver.setLocale(httpRequest, httpResponse, local);
			chain.doFilter(httpRequest, httpResponse);
		}else {
			 throw new RuntimeException("auth fail");
		}
        
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
