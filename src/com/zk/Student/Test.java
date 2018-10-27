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

//Hibernate把数据库的操作变成一个面向对象的操作
public class Test {
	//Query对象，封装HQL语句的对象
	//Query中封装查询细节api
	public static void main(String[]args)
	{
		//criteria对象与Query对象功能很像
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
		    //Configuration用于加载配置文件
		    //用configure()方法加载src下名为hibernate.cfg.xml文件
		    Configuration cfg=new Configuration().configure();
		    //如果配置文件不符合默认加载规则，我们可以调用
		    //new Configuration().configure(file);通过file加载
		    //new Configuration().configure(path);通过path加载
		    //1.3可以通过Configuration对象加载映射文件(不推荐)
		    //>orm的映射文件与简单的类名一致
		    //>orm映射文件需要与实体的类在同一包下
		    //推荐hibernate.cfg.xml使用mapping属性导入映射文件
		    //cfg.addClass(Student.class);
		    //SessionFactory创建session工厂
		    //2.根据Configuration的配置信息创建SessionFactory
		    SessionFactory sf = cfg.buildSessionFactory();
		    //3.opensession->获得一个全新的session
		    //getCurrentsession->获得当前session对象
		    //<!-- 将Session与线程绑定=>只有配置了该配置，才能使用getCurrentSession -->
			//<property name="hibernate.current_session_context_class">thread</property>
		    //Session对象是用来操作数据库的，获得session
		    Session se = sf.openSession();
		    sf.openSession();
		    Session se2=sf.getCurrentSession();
		    Session se3=sf.getCurrentSession();
		    //System.out.println(se2==se3);
		    //Transaction tx=se.beginTransaction();
		    //Transaction封装了事物的操作
		    //开启事务
		    //封装事务
		    //回滚事务
		    Transaction tx=se.beginTransaction();
		    //自动提交，还需在Hibernate中配置autocommit
		    //调用session的save方法保存对象进入数据库
		    //1.插入insert
		    //Session的插入方法
		    se.save(stu);
		    //2.查询 select
		    //方法一get
		    //Session的查询方法
		    //get方法（对象的类名,id）,被调用时，立刻发送sql语句查询
		    //Student s=(Student) se.get(Student.class, 1);
		    //System.out.println(s);
		    //方法二load,被调用时，不立即查询，当我们需要使用该对象时才开始查询
		    //Student s=(Student) se.load(Student.class, 14);
		    //System.out.println(s);
		    //方法四，HQL语句查询
		    //createQuery传入HQL进行查询,查询所有的student表中的数据
		    //Query query=se.createQuery("from com.zk.Student.Student");
		    //查询结果
		    //List<Student> list=query.list();
		    //System.out.println(list);
		    //方法三:Criteria查询=>Hibernate独创的面向对象的查询=> 无语句
		    Criteria criteria=se.createCriteria(Student.class);
		    //select * from student
		    //List<Student> list=criteria.list();
		    //返回一个查询结果
		    //criteria.add(Restrictions.eq("sno", 14));
		    //返回多个查询结果
		    //criteria.add(Restrictions.like("sname","%h%"));
		    //Student s=(Student) criteria.uniqueResult();
		    //List<Student> l=criteria.list();
		    //System.out.println(s);
		    //System.out.println(l);
		    //查找id大于1的用户
		    criteria.add(Restrictions.gt("sno",1));
		    List<Student> l=criteria.list();
		    //System.out.println(l);
		    //System.out.println(list);
		    //方法四:原生的sql语句,原生sql查询不是面向对象的查询，所以list并不是Student的类型
		    //SQLQuery query=se.createSQLQuery("select * from student where Sname='h'");
		    //分页limit index,count;分页设计，生成limit语句
		    //制定结果从第几个开始拿
		    //索引从 0开始算
		    //query.setFirstResult(0);
		    //制定结果拿几个
		    //query.setMaxResults(1);
		    //addEntity将查询结果封装到对象中
		    //结构化代码1查询结果返回方式一
		    //query.addEntity(Student.class);
		    //List<Student> list=query.list();
		    //System.out.println(list);
		    //结构化代码2查询结果返回方式二
		    //List<Object[]> list=query.list();
		    //for(Object[] obj:list){
		    //System.out.println(Arrays.toString(obj));
		    //}
		    //3.修改 Update
		    //Student s=(Student)se.get(Student.class, 1);
		    //s.setSname("a");
		    //s.setSgrade(1);
		    //se.update(s);
		    //4.删除
		    //Student s=(Student)se.get(Student.class, 1);
		    //s.setSname("a");
		    //s.setSgrade(1);
		    //se.delete(s);
		    se.flush();
		    //se2.save(stu);
		    tx.commit();
		    //最后还需要关闭资源，使用close方法
		    //事务关闭时会把当前session关闭并删除
		    se.close();
		    //se2.close();
		    sf.close();
		    System.out.println("success!!!");
	}
}
