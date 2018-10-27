package com.Hibernate.DButil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//���Hibernate������
//��װ�����ļ���ȡ����
//��װSessionFactory��������
//��װsession��ò���
public class HibernateDButils {
	private static SessionFactory sf=null;
	static{
		//1.��������
		Configuration cfg=new Configuration().configure();
		//2.����Configuration������Ϣ����SessionFactory
		sf= cfg.buildSessionFactory();
		//3.�ر������ʱ���ͷ�sessionfactory
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("������ر�,�ͷ���Դ");
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
