package com.zk.Student;

import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import com.Hibernate.DButil.HibernateDButils;

//���������״̬
public class TestDemo {

	@Test
	//��ʾ����״̬
	//�־�״̬,������ hibernate��Ҫ��Ϊ�˳־û����ǵ�����
	//���ڶ����״̬����������������Ҫͬ�������ݿ�����ݣ�����ת���ɳ־�״̬
	//�־û����ݵ��ص㣺hibernate������ͬ�������ݿ���
	//˲ʱ->�־�
	//˲ʱ->����
	//˲ʱ:û�й��� ����:û�й�������sno(�����ݿ���sno��Ӧ)
	//�־�->˲ʱ
	//�־�:�й�������id ˲ʱ:�޹�������id
	//�־�->����
	
	public void test() {
		Session session=HibernateDButils.getCurrentSession();
		session.beginTransaction();
		//---------------------------------------
		Student stu=new Student();//˲ʱ״̬	
		stu.setSname("zl");//˲ʱ״̬
		//stu.setSno(1);//˲ʱ״̬  //��session������id�������޸� ��
		stu.setSgrade(100); //˲ʱ״̬
		//session.save(stu); //�־�״̬��������save���������ݿ���û�ж�Ӧ��¼���������ջᱻͬ�������ݿ��У�save������ʹ���������ɲ��ԣ�ΪUserָ��id
		//��������
		session.persist(stu);
		session.getTransaction().commit();//�־�״̬
		List<Student> l=session.createSQLQuery("select * from student").list();
		
		List<Student> l2=session.createSQLQuery("select * from student").addEntity(Student.class).list();
		Student s=(Student) session.get(Student.class, 1);
		//session.update(stu);
		//˲ʱ->�־ã�����save����ʱ���־û���save����ʹ���������ɲ��ԣ�Ϊstudentָ��	
		//session.refresh(stu);
		//����save���������ݿ���û�ж�Ӧ��¼
		//session.getTransaction().commit();//�־�״̬
		//ͨ��session.get()��ȡһ���־�״̬����
		//����select��䣬�����ݿ���ȡ�����ݼ�¼������װ�ɶ��󣬳־û�״̬����=>���뻺����
		Student stu1=(Student) session.get(Student.class, 1);//�־û�
		//session.update(stu1);
		//��֤һ�����������ݿ������һ��
		//session.flush();
		//�ٴβ�ѯʱ����ӻ����в��ң����ᷢ��select���
		Student stu2=(Student)session.get(Student.class,1);//�־û�
		//session.save(stu);//�־�״̬
		session.saveOrUpdate(stu2);
		
		System.out.println(stu1);
		System.out.println(stu2);
		//���ؽ��true
		System.out.println(stu1==stu2);
		//��session������stu����
		session.evict(stu);//����evict�������Խ������Ϊ����״̬ 
		//commit����
		session.close();//����״̬
		//---------------------------------------
	}

}
