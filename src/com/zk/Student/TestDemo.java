package com.zk.Student;

import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import com.Hibernate.DButil.HibernateDButils;

//对象的三种状态
public class TestDemo {

	@Test
	//演示三种状态
	//持久状态,我们用 hibernate主要是为了持久化我们的数据
	//对于对象的状态，我们期望我们需要同步到数据库的数据，都被转换成持久状态
	//持久化数据的特点：hibernate将数据同步到数据库中
	//瞬时->持久
	//瞬时->游离
	//瞬时:没有关联 游离:没有关联，有sno(与数据库中sno对应)
	//持久->瞬时
	//持久:有关联，有id 瞬时:无关联，无id
	//持久->游离
	
	public void test() {
		Session session=HibernateDButils.getCurrentSession();
		session.beginTransaction();
		//---------------------------------------
		Student stu=new Student();//瞬时状态	
		stu.setSname("zl");//瞬时状态
		//stu.setSno(1);//瞬时状态  //与session关联的id不允许修改 ，
		stu.setSgrade(100); //瞬时状态
		//session.save(stu); //持久状态，调用完save方法，数据库中没有对应记录，但是最终会被同步到数据库中，save方法会使用主键生成策略，为User指定id
		//主键自增
		session.persist(stu);
		session.getTransaction().commit();//持久状态
		List<Student> l=session.createSQLQuery("select * from student").list();
		
		List<Student> l2=session.createSQLQuery("select * from student").addEntity(Student.class).list();
		Student s=(Student) session.get(Student.class, 1);
		//session.update(stu);
		//瞬时->持久，调用save方法时，持久化，save方法使用主键生成策略，为student指定	
		//session.refresh(stu);
		//调用save方法后，数据库中没有对应记录
		//session.getTransaction().commit();//持久状态
		//通过session.get()获取一个持久状态对象
		//发送select语句，从数据库中取出数据记录，并封装成对象，持久化状态对象=>存入缓存中
		Student stu1=(Student) session.get(Student.class, 1);//持久化
		//session.update(stu1);
		//保证一级缓存与数据库的数据一致
		//session.flush();
		//再次查询时，会从缓存中查找，不会发送select语句
		Student stu2=(Student)session.get(Student.class,1);//持久化
		//session.save(stu);//持久状态
		session.saveOrUpdate(stu2);
		
		System.out.println(stu1);
		System.out.println(stu2);
		//返回结果true
		System.out.println(stu1==stu2);
		//将session对象与stu关联
		session.evict(stu);//调用evict方法可以将对象变为游离状态 
		//commit方法
		session.close();//游离状态
		//---------------------------------------
	}

}
