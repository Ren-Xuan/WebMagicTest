package com.cza.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class HupuListByWebmagic implements PageProcessor {
	public static int cnt = 0;
	private Site site = Site.me().setRetryTimes(3).setSleepTime(10);
	

	public Site getSite() {
		return site;
	}
	@SuppressWarnings("deprecation")
	public void finaly() throws Throwable {
		cnt=0;//�������ļ�����ֹ��һ���޷�ִ���̲߳���
		this.finalize();
	}

	public void process(Page page) {
		if (cnt >= 100) {
			return;
		}
		cnt++;
		// �б�ҳ�������ӵ�URL��/html/body/div[3]/div[1]/div[2]/ul/li[2]/div[1]/h4/a
		// �б�ҳ�淭ҳ��URL��/html/body/div[3]/div[1]/div[3]/a[4]
		// ����ҳ��URL��https://voice.hupu.com/nba/2409577.html
		// ����ҳ�ı��⣺/html/body/div[4]/div[1]/div[1]/h1
		// //div[@class='artical-title']/h1/text()
		// ����ҳ�����ݣ�/html/body/div[4]/div[1]/div[2]
		// //div[@class='artical-main-content']/p/text()

		// �ж�ҳ���Ƿ�Ϊ����ҳ
		if (!page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match()) {
			// ����url
			page.addTargetRequests(
					page.getHtml().xpath("/html/body/div[3]/div[1]/div[2]/ul/li/div[1]/h4/a/@href").all());
			// ��ҳurl
			page.addTargetRequests(
					page.getHtml().xpath("/html/body/div[3]/div[1]/div[3]/a[@class='page-btn-prev']/@href").all());
		} else {
			System.out.println(cnt + "==" + page.getHtml().xpath("//div[@class='artical-title']/h1/text()").toString());
			System.out.println(page.getHtml().xpath("//div[@class='artical-main-content']/p/text()").all().toString());
//			page.putField("title", page.getHtml().xpath("//div[@class='artical-title']/h1/text()").toString());
//			page.putField("content", page.getHtml().xpath("//div[@class='artical-main-content']/p/text()").all().toString());
		}

	}

	
}
