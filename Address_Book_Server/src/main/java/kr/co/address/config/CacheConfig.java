package kr.co.address.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

@Configuration
@EnableCaching
public class CacheConfig {
	
	 private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);
	   
	 @Autowired
	    private CacheManager cacheManager;

	    @Bean
	    public EhCacheCacheManager cacheManager() {
	        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	    }

	    @Bean
	    public EhCacheManagerFactoryBean ehCacheCacheManager() {
	        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
	        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
	        cmfb.setShared(true);
	        return cmfb;
	    }

}
