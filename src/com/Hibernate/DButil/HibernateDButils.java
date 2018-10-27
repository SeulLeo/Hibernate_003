package com.Hibernate.DButil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//完成Hibernate工具类
//封装配置文件读取操作
//封装SessionFactory创建操作
//封装session获得操作
public class HibernateDButils {
	private static SessionFactory sf=null;
	static{
		//1.加载配置
		Configuration cfg=new Configuration().configure();
		//2.根据Configuration配置信息创建SessionFactory
		sf= cfg.buildSessionFactory();
		//3.关闭虚拟机时，释放sessionfactory
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("虚拟机关闭,释放资源");
				sf.close();
			}
			
		}));
	}
	public static org.hibernate.Session openSession(){
		
		Session se = sf.openSession();
		return se;
	}
	
	public static org.hibernate.Session getCurrentSession(){
		Session se = sf.openSession();
		return se;
		
	}
	public static void main(String[]args)
	{
		System.out.println(openSession());
	}
}
