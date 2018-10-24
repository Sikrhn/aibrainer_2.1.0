package com.zhu.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhu.dao.DeveloperDao;
import com.zhu.dao.DeveloperDataDao;
import com.zhu.dao.DeveloperTaskDao;
import com.zhu.dao.PublicDataDao;
import com.zhu.dao.RectDataDao;
import com.zhu.dao.TaskClassesDao;
import com.zhu.dao.UserDao;
import com.zhu.dao.UserLabelDao;
import com.zhu.dao.UserTaskDao;
import com.zhu.entity.RectData;
import com.zhu.entity.UserLabel;
import com.zhu.utils.SmallTools;
import com.zhu.entity.UserLabel;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")   
public class testDemo {
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeveloperDao developerdao;
	@Autowired
	private DeveloperTaskDao dtdao;
	@Autowired
	private DeveloperDataDao dddao;
	@Autowired
	private UserLabelDao uldao;
	@Autowired
	private UserTaskDao utdao;
	@Autowired
	private TaskClassesDao tcdao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private PublicDataDao pddao;	
	@Autowired
	private RectDataDao rddao;
	@Test
	public void test1(){
//		List<String> list = rddao.getRectDataFirst("admin","汽车种类");
//		for (String item : list){
//			System.out.println(item);
//		}
		List<RectData> list = rddao.getLabelList("admin", "测试查看标注状况");
		SmallTools.exportCsv("C:\\apache-tomcat-8.5.34\\webapps\\aibrainer_2.1.0\\WEB-INF\\upload\\admin\\测试查看标注状况\\", "测试查看标注状况", list);
//		List<RectData> list = rddao.getLabelList("admin","测试标注");
//		SmallTools.exportCsv("C:\\apache-tomcat-8.5.34\\webapps\\aibrainer_2.1.0\\WEB-INF\\upload\\admin\\汽车种类\\", "汽车种类", list);
//		User user = new User("3",null,1,22,12,null,11);
//		userdao.getReward(user);
//		pddao.deleteDataByAssignment( "admin","人脸测试");
//		DeveloperTaskService dts= new DeveloperTaskServiceImpl();
//		HomeDataBody h = dts.getHomeData();
//		System.out.println(h.getAllAccuracy());
//		List<PublicData> list = pddao.getRandFive("人脸表情", "developer1");
//		System.out.println(list.size());
//		developerdao.registerDeveloper(new Developer("Unow","123",0,"2018-09-13","123@qq.com"));
//		dtdao.insertTask(new DeveloperTask("1","2","asdasd",5,"",2,2,2,"",false,2,3,false,2));
//		List<UserLabel> list = uldao.firstGetDataName("user10", "213123", "人脸表情");
//		if(list!=null){
//			System.out.println("not null"+list.size());
//			System.out.println(list == null);
//				for(UserLabel i : list){
//					System.out.println("1");
//					System.out.println(i.getDataName());
//				}
//		}else{
//			System.out.println("null");
//		}
//		System.out.println(userdao.queryUser("asdasd")==null);
//		List<DeveloperTask> item = dldao.getPublicTask();
//		for(DeveloperTask d : item){
//			System.out.println(d.getAssignment());
//		}
//		String developer = "admin";
//		String username = "user1";
//		String assignment = "test1";
//		System.out.println(utdao.getAllUserIsOver(developer, assignment));
//		System.out.println(dtdao.queryMoney(developer, assignment));
//		dtdao.payMoney(developer, assignment, 2);
//		utdao.updateIsOver("user4", "admin", "人脸表情");
//		dtdao.getAllTask(1, 2);
//		dtdao.addWokerNum(developer, assignment);
//		dtdao.getSelfTask(developer);
//		dtdao.getDetailOne(developer, assignment);
//		dtdao.getTaskNum();
//		dtdao.insertProfitTask(new DeveloperTask(developer,assignment+"3",10,"12",2,1,100,"asd",false,0,0,false));
//		dtdao.getPublicTask();
//		dtdao.insertProfitTask(new DeveloperTask(developer,assignment+"3",10,"12",2,1,100,"asd",false,0,0,false));
//		System.out.println(dtdao.getTaskNum());
//		System.out.println(dtdao.isNeedWorker(developer, assignment)!=null);
//		DeveloperTask d = dldao.getDetailOne("admin","test1");
//		System.out.println(d.getExp());
//		dddao.insertData(developer, assignment, "120.jpg");
//		System.out.println(1);
//		dddao.getDataName(developer, assignment);
//		System.out.println(2);
//		uldao.insertData(username,new DeveloperData(developer,assignment,"11.jpg",null));
//		System.out.println(3);
//		uldao.firstGetDataName(developer, assignment);
//		System.out.println(4);
//		uldao.preGetDataName(developer, assignment);
//		System.out.println(5);
//		uldao.updateLabel(new UserLabel(username,developer,assignment,"12.jpg","微笑"));
//		uldao.getRandUserLabel(username, assignment);
//		System.out.println(6);
//		utdao.getSameUserTask(developer, assignment);
//		System.out.println(7);
//		utdao.getUserTask(username);
//		System.out.println(8);
//		utdao.insertUserTask(username, developer, assignment+"2",false);
//		System.out.println(9);
//		tcdao.getLabel(developer, assignment);
//		System.out.println(10);
//		tcdao.insertLabel(new TaskClasses(developer,assignment,"哭泣"));
//		System.out.println(11);
		
//		System.out.println(new ResponseMsg(StatEnum.SUCCESS,null));
//		logger.info("list={}", new ResponseMsg(StatEnum.SUCCESS,null));
	}
}
