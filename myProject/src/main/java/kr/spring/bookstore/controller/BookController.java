package kr.spring.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import kr.spring.bookstore.vo.BookVO;

@Controller
public class BookController {
	public List<BookVO> getBookList(String query){
		if(query == null || query.length() == 0) {
			return new ArrayList<BookVO>();
		}
		final String APP_KEY = "KakaoAK 01be56c57f3e1447f4b6d6cad08f3f3b";
		StringBuilder sb = new StringBuilder();
		sb.append("https://dapi.kakao.com");
		sb.append("/v3/search/book");
		sb.append("?query=").append(query);
		
		Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		Header authHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, APP_KEY);
		List<Header> headers = new ArrayList<Header>();
		headers.add(jsonHeader);
		headers.add(authHeader);
		
		HttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(100)
				.setMaxConnPerRoute(5)
				.setDefaultHeaders(headers)
				.build();
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(3000);
		factory.setHttpClient(httpClient);
		
		RestTemplate restTemplate = new RestTemplate(factory);
		
		String response = restTemplate.getForObject(sb.toString(), String.class);
		JSONObject json = new JSONObject(response);
		JSONArray bookArray = json.getJSONArray("documents");
		
		List<BookVO> result = new ArrayList<BookVO>();
		for(int i=0; i<bookArray.length(); i++) {
			JSONObject book = bookArray.getJSONObject(i);
			BookVO vo = BookVO.parse(book);
			result.add(vo);
		}
		
		return result;
	}
}
