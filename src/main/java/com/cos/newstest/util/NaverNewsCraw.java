package com.cos.newstest.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.newstest.domain.News;


//https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid=0000000001

@Component
public class NaverNewsCraw {
	
	int aidNum = 1;
	
	public List<News> newsCollect() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList =new ArrayList<>();
	
	for (int i = 1; i <6; i++) {
		String aid = String.format("%010d", aidNum);
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid="+aid;
		String html = rt.getForObject(url,String.class);
		
		Document doc = Jsoup.parse(html);
	
		
		Element titleElement = doc.selectFirst("#articleTitle");
		Element createdAtElement = doc.selectFirst(".t11");
			
		String company = doc.selectFirst(".content a img").attr("alt");
		String title = titleElement.text();
		String createdAt = createdAtElement.text();
		
		News news = News.builder()
				.company(company)
				.title(title)
				.createdAt(createdAt)
				.build();

		newsList.add(news);
		
		aidNum ++;
	}
	return newsList;
	
	}
}

