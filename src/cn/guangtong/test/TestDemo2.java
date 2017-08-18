package cn.guangtong.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TestDemo2 implements ActionListener{

	private int id;
	private String name;
	private Date time;
	
	public float num;
	public char cc;
	public boolean bl;
	
	public void testA(){
		System.out.println("AAA");
	}
	
	public String testS(){
		System.out.println("SSS");
		return name+"ssss";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getNum() {
		return num;
	}
	public void setNum(float num) {
		this.num = num;
	}
	public char getCc() {
		return cc;
	}
	public void setCc(char cc) {
		this.cc = cc;
	}
	public boolean isBl() {
		return bl;
	}
	public void setBl(boolean bl) {
		this.bl = bl;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
