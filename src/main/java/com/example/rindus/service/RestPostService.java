package com.example.rindus.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.rindus.entity.PostBean;

@Service
public class RestPostService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${typi.post.url}")
	private String postUrl;
	
	public List<?> getAllPost(){
			List<Object> list = restTemplate.getForObject(postUrl, List.class);
			return list;
	}

	public Object getPost(String id) {
		String url = postUrl + "/{id}"; 
	    
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", id);
	    Object result = null;
	    try {
	    	result = restTemplate.getForObject(url, Object.class, params);
	    }catch(Exception e) {
	    	result = null;
	    }	    
	    return result;
	  
	}

	public void deletePost(String id) {
		String url = postUrl + "/{id}"; 
    
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
   
		restTemplate.delete(url, params);		
		
	}

	public void savePost(PostBean postVo) {
		String uri = postUrl ;    
	    restTemplate.postForObject( uri, postVo, PostBean.class);	
		
	}

	public boolean updatePost(PostBean postVo) {
		String uri = postUrl + "/{id}";  
		boolean result = true;
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", ""+postVo.getId());
		try {
			restTemplate.put( uri, postVo, params);
		}catch(Exception e) {
			result = false;
		}
	    return result;
		
	}

}
