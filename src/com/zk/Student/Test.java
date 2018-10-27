package com.zk.Student;

import java.util.Arrays;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

//Hibernate�����ݿ�Ĳ������һ���������Ĳ���
public class Test {
	//Query���󣬷�װHQL���Ķ���
	//Query�з�װ��ѯϸ��api
	public static void main(String[]args)
	{
		//criteria������Query�����ܺ���
		//> gt
		//< lt
		//= eq
		//>= ge
		//<= le
		//like
		//between
		    Student stu=new Student();
		    stu.setSno(1);
		    stu.setSname("a");
		    stu.setSgrade(1);
		    //Configuration���ڼ��������ļ�
		    //��configure()��������src����Ϊhibernate.cfg.xml�ļ�
		    Configuration cfg=new Configuration().configure();
		    //��������ļ�������Ĭ�ϼ��ع������ǿ��Ե���
		    //new Configuration().configure(file);ͨ��file����
		    //new Configuration().configure(path);ͨ��path����
		    //1.3����ͨ��Configuration�������ӳ���ļ�(���Ƽ�)
		    //>orm��ӳ���ļ���򵥵�����һ��
		    //>ormӳ���ļ���Ҫ��ʵ�������ͬһ����
		    //�Ƽ�hibernate.cfg.xmlʹ��mapping���Ե���ӳ���ļ�
		    //cfg.addClass(Student.class);
		    //SessionFactory����session����
		    //2.����Configuration��������Ϣ����SessionFactory
		    SessionFactory sf = cfg.buildSessionFactory();
		    //3.opensession->���һ��ȫ�µ�session
		    //getCurrentsession->��õ�ǰsession����
		    //<!-- ��Session���̰߳�=>ֻ�������˸����ã�����ʹ��getCurrentSession -->
			//<property name="hibernate.current_session_context_class">thread</property>
		    //Session�����������������ݿ�ģ����session
		    Session se = sf.openSession();
		    sf.openSession();
		    Session se2=sf.getCurrentSession();
		    Session se3=sf.getCurrentSession();
		    //System.out.println(se2==se3);
		    //Transaction tx=se.beginTransaction();
		    //Transaction��װ������Ĳ���
		    //��������
		    //��װ����
		    //�ع�����
		    Transaction tx=se.beginTransaction();
		    //�Զ��ύ��������Hibernate������autocommit
		    //����session��save�����������������ݿ�
		    //1.����insert
		    //Session�Ĳ��뷽��
		    se.save(stu);
		    //2.��ѯ select
		    //����һget
		    //Session�Ĳ�ѯ����
		    //get���������������,id��,������ʱ�����̷���sql����ѯ
		    //Student s=(Student) se.get(Student.class, 1);
		    //System.out.println(s);
		    //������load,������ʱ����������ѯ����������Ҫʹ�øö���ʱ�ſ�ʼ��ѯ
		    //Student s=(Student) se.load(Student.class, 14);
		    //System.out.println(s);
		    //�����ģ�HQL����ѯ
		    //createQuery����HQL���в�ѯ,��ѯ���е�student���е�����
		    //Query query=se.createQuery("from com.zk.Student.Student");
		    //��ѯ���
		    //List<Student> list=query.list();
		    //System.out.println(list);
		    //������:Criteria��ѯ=>Hibernate�������������Ĳ�ѯ=> �����
		    Criteria criteria=se.createCriteria(Student.class);
		    //select * from student
		    //List<Student> list=criteria.list();
		    //����һ����ѯ���
		    //criteria.add(Restrictions.eq("sno", 14));
		    //���ض����ѯ���
		    //criteria.add(Restrictions.like("sname","%h%"));
		    //Student s=(Student) criteria.uniqueResult();
		    //List<Student> l=criteria.list();
		    //System.out.println(s);
		    //System.out.println(l);
		    //����id����1���û�
		    criteria.add(Restrictions.gt("sno",1));
		    List<Student> l=criteria.list();
		    //System.out.println(l);
		    //System.out.println(list);
		    //������:ԭ����sql���,ԭ��sql��ѯ�����������Ĳ�ѯ������list������Student������
		    //SQLQuery query=se.createSQLQuery("select * from student where Sname='h'");
		    //��ҳlimit index,count;��ҳ��ƣ�����limit���
		    //�ƶ�����ӵڼ�����ʼ��
		    //������ 0��ʼ��
		    //query.setFirstResult(0);
		    //�ƶ�����ü���
		    //query.setMaxResults(1);
		    //addEntity����ѯ�����װ��������
		    //�ṹ������1��ѯ������ط�ʽһ
		    //query.addEntity(Student.class);
		    //List<Student> list=query.list();
		    //System.out.println(list);
		    //�ṹ������2��ѯ������ط�ʽ��
		    //List<Object[]> list=query.list();
		    //for(Object[] obj:list){
		    //System.out.println(Arrays.toString(obj));
		    //}
		    //3.�޸� Update
		    //Student s=(Student)se.get(Student.class, 1);
		    //s.setSname("a");
		    //s.setSgrade(1);
		    //se.update(s);
		    //4.ɾ��
		    //Student s=(Student)se.get(Student.class, 1);
		    //s.setSname("a");
		    //s.setSgrade(1);
		    //se.delete(s);
		    se.flush();
		    //se2.save(stu);
		    tx.commit();
		    //�����Ҫ�ر���Դ��ʹ��close����
		    //����ر�ʱ��ѵ�ǰsession�رղ�ɾ��
		    se.close();
		    //se2.close();
		    sf.close();
		    System.out.println("success!!!");
	}
}
