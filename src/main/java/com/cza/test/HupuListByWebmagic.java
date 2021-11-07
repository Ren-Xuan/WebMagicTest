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
		cnt=0;//清零他的计数防止下一次无法执行线程操作
		this.finalize();
	}

	public void process(Page page) {
		if (cnt >= 100) {
			return;
		}
		cnt++;
		// 列表页文章链接的URL：/html/body/div[3]/div[1]/div[2]/ul/li[2]/div[1]/h4/a
		// 列表页面翻页的URL：/html/body/div[3]/div[1]/div[3]/a[4]
		// 文章页的URL：https://voice.hupu.com/nba/2409577.html
		// 详情页的标题：/html/body/div[4]/div[1]/div[1]/h1
		// //div[@class='artical-title']/h1/text()
		// 详情页的内容：/html/body/div[4]/div[1]/div[2]
		// //div[@class='artical-main-content']/p/text()

		// 判断页面是否为文章页
		if (!page.getUrl().regex("https://voice\\.hupu\\.com/nba/[0-9]{7}\\.html").match()) {
			// 文章url
			page.addTargetRequests(
					page.getHtml().xpath("/html/body/div[3]/div[1]/div[2]/ul/li/div[1]/h4/a/@href").all());
			// 翻页url
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
