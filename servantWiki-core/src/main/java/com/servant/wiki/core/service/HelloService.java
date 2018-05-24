package com.servant.wiki.core.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servant.wiki.common.config.Global;
import com.servant.wiki.common.util.JsonUtils;
import com.servant.wiki.core.config.SpringConfig;
import com.servant.wiki.core.dao.HelloDao;
import com.servant.wiki.core.dao.HqDao;
import com.servant.wiki.core.entity.Demo;
import com.servant.wiki.core.entity.HqDemo;

/**
 * 
 * @author lijia
 *
 */
@Service
public class HelloService {

	Logger logger = LoggerFactory.getLogger(HelloService.class);
	
	@Autowired
	private HelloDao helloDao;
	
	@Autowired
	private HqDao hqDao;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	SpringConfig springConfig;
	
	@SuppressWarnings("unchecked")
	public void sayHello(){
		
		logger.info("----------hello service----"+Global.getConfig("env")+"-----");
		List<Demo> demos = helloDao.findListByContent("test");
		for(Demo demo : demos){
			logger.info("=======demo content: {}",demo.getContent());
		}
//		List<HqDemo> hqDemos = hqDao.getDate();
		
		Query q = em.createNamedQuery("HqDemo.getDataById");
		q.setParameter(1, 2);
		List<HqDemo> hqDemos = q.getResultList();
		for(HqDemo demo : hqDemos){
			logger.info("=======Hqdemo content: {}",JsonUtils.toJson(demo));
		}
	}
}