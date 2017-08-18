package cn.guangtong.utils;

import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Shiro的验证类
 * @author chenjunpeng
 * @date 2016年2月22日
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private Ehcache passwordRetryCache;
	
	public CustomCredentialsMatcher(){
		URL url = CustomCredentialsMatcher.class.getResource("ehcache.xml");
		CacheManager cacheManager = CacheManager.create(url);
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}
	
	/**
	 * 	密码验证
	 */
	@Override  
	public boolean doCredentialsMatch(AuthenticationToken token,  
			AuthenticationInfo info) {  
			boolean flag = true;
			String userName = (String)token.getPrincipal();
			Element element = passwordRetryCache.get(userName);
			if(element == null) {
	            element = new Element(userName , new AtomicInteger(0));
	            passwordRetryCache.put(element);
	        }
	        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
	        if(retryCount.incrementAndGet() > 10) {
	            logger.debug("登录失败次数超过3次");
	            throw new ExcessiveAttemptsException();
	        }
			
			Object tokenCredentials = token.getCredentials();  
			Object accountCredentials = getCredentials(info);  
			flag = equals(tokenCredentials, accountCredentials);  
			if(flag){
				passwordRetryCache.remove(element.getKey());
			}
			return flag;
	}  

	

}
