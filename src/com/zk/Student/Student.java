package com.zk.Student;

public class Student {
	//·ûºÏjavaBean¹æ·¶
	private int Sno;
	private String Sname;
	private int Sgrade;
	public int getSno() {
		return Sno;
	}
	public void setSno(int sno) {
		Sno = sno;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public int getSgrade() {
		return Sgrade;
	}
	public void setSgrade(int sgrade) {
		Sgrade = sgrade;
	}
	@Override
	public String toString() {
		return "Student [Sno=" + Sno + ", Sname=" + Sname + ", Sgrade="
				+ Sgrade + "]";
	}
}
