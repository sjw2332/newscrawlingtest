package com.cos.newstest.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.newstest.domain.News;
import com.cos.newstest.domain.NewsRepository;
import com.cos.newstest.util.NaverNewsCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {
	
	private final NewsRepository newsRepository;
	private final NaverNewsCraw naverNewsCraw;
	
	//@Scheduled(cron = "* /1 * * * *",zone = "Asia/Seoul")
	@Scheduled(fixedDelay=1000*60*1)
	public void newsCrawStart() {
		List<News> newsList = naverNewsCraw.newsCollect();
		
		try {
			newsRepository.saveAll(newsList);			
		} catch (Exception e) {
			System.out.println("db저장 오류");
		}
		
		System.out.println("실행됨");
		
	}
}
