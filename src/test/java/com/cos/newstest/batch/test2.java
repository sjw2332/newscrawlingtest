package com.cos.newstest.batch;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;


//https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid=0000000001


public class test2 {
	
	@Test
	public void newsCollect() {
		RestTemplate rt = new RestTemplate();
		int aidNum = 0001004005;
		List<News> newsList =new ArrayList<>();
	
	for (int i = 1; i <10; i++) {
		
	
		
		
		String aid = String.format("%010d", aidNum);
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid="+aid;
		String html = rt.getForObject(url,String.class);
		
		Document doc = Jsoup.parse(html);
		
		//System.out.println(doc);
		
		
		
		Element titleElement = doc.selectFirst("#articleTitle");
		Element createdAtElement = doc.selectFirst(".t11");
		
		
		String company = doc.selectFirst(".content a img").attr("alt");
		String title = titleElement.text();
		String createdAt = createdAtElement.text();
		
		System.out.println(company);
		System.out.println(title);
		System.out.println(createdAt);

		News news = News.builder()
				.company(company)
				.title(title)
				.createdAt(createdAt)
				.build();
		
		//newsList.add(news);
		
		aidNum ++;
	}
	
	
	
	
	//return newsList;
	
	}
}
