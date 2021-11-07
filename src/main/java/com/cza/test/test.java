package com.cza.test;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.data.xy.XYSeries;

import us.codecraft.webmagic.Spider;

public class test {
	public static long start(int threadNums) throws Throwable {
		long startTime, endTime;
		System.out.println("��ʼ��ȡ...");
		startTime = System.currentTimeMillis();
		HupuListByWebmagic hupuListByWebmagic=new HupuListByWebmagic();
		Spider.create(hupuListByWebmagic).addUrl("https://voice.hupu.com/nba").thread(threadNums).run();
		endTime = System.currentTimeMillis();
		System.out.println("��ȡ��������ʱԼ" + (endTime - startTime) + "����");
		hupuListByWebmagic.finaly();
		hupuListByWebmagic=null;
		
		return (endTime - startTime);
	}
	public static long average(int n,int threadNums) throws Throwable {
		long sum=0;	//��¼��ʱ��
		for(int i=0;i<n;i++) {
			sum+=start(threadNums);//˳��ִ��threadNums
		}
		return sum/n;//����ƽ��ʱ��
	}
	public static void run() throws Throwable {
			Scanner in=new Scanner(System.in);
			System.out.println("����߳���");
			int maxThreadNum=in.nextInt();//����߳���
			
			System.out.print("ÿ�������߳���");
			int interval=in.nextInt();//ÿ���߳�ִ�д���
			
			System.out.print("ÿ���߳�ִ�д���:");
			int timesPerOneThread=in.nextInt();//ÿ���߳�ִ�д���
			
			XYSeries series = new XYSeries("xySeries");
			//��ͼ��Ҫ�Ĳ�����ÿ�ζ��߳�ִ�ж�����������ͽ��
			UI ui=new UI(series);

			for(int x=1;x<=maxThreadNum;x+=interval) {//ÿ������5���߳�
				//��¼ƽ��timePerOneThread��ִ��x���߳�����Ҫ�ĺ���ʱ��
				long y=test.average(timesPerOneThread, x);		
				series.add(x, y);
				ui.dispose();
				ui=new UI(series);
				

			}
	}
	public static void main(String[] args) throws Throwable {
		
		test.run();
		JOptionPane.showMessageDialog(new JPanel(), "����");
	}
}
