package com.cza.test;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.data.xy.XYSeries;

import us.codecraft.webmagic.Spider;

public class test {
	public static long start(int threadNums) throws Throwable {
		long startTime, endTime;
		System.out.println("开始爬取...");
		startTime = System.currentTimeMillis();
		HupuListByWebmagic hupuListByWebmagic=new HupuListByWebmagic();
		Spider.create(hupuListByWebmagic).addUrl("https://voice.hupu.com/nba").thread(threadNums).run();
		endTime = System.currentTimeMillis();
		System.out.println("爬取结束，耗时约" + (endTime - startTime) + "毫秒");
		hupuListByWebmagic.finaly();
		hupuListByWebmagic=null;
		
		return (endTime - startTime);
	}
	public static long average(int n,int threadNums) throws Throwable {
		long sum=0;	//记录总时间
		for(int i=0;i<n;i++) {
			sum+=start(threadNums);//顺序执行threadNums
		}
		return sum/n;//返回平均时间
	}
	public static void run() throws Throwable {
			Scanner in=new Scanner(System.in);
			System.out.println("最大线程数");
			int maxThreadNum=in.nextInt();//最大线程数
			
			System.out.print("每次增加线程数");
			int interval=in.nextInt();//每种线程执行次数
			
			System.out.print("每种线程执行次数:");
			int timesPerOneThread=in.nextInt();//每种线程执行次数
			
			XYSeries series = new XYSeries("xySeries");
			//绘图需要的参数，每次多线程执行都会填入参数和结果
			UI ui=new UI(series);

			for(int x=1;x<=maxThreadNum;x+=interval) {//每次增加5个线程
				//记录平均timePerOneThread次执行x条线程所需要的毫秒时间
				long y=test.average(timesPerOneThread, x);		
				series.add(x, y);
				ui.dispose();
				ui=new UI(series);
				

			}
	}
	public static void main(String[] args) throws Throwable {
		
		test.run();
		JOptionPane.showMessageDialog(new JPanel(), "结束");
	}
}
