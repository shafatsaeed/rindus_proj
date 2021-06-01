package com.example.rindus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rindus.entity.PostBean;
import com.example.rindus.service.RestPostService;



@RestController
public class RestConsumeController {
	
	@Autowired
	private RestPostService restPostService;
	
	@GetMapping("/getposts")
	public ResponseEntity<List<Object>> getAllPost(){
		List<?> list = restPostService.getAllPost();
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@GetMapping("/getpost/{id}")
	public ResponseEntity<?> getPost(@PathVariable("id") String id){
		Object bean = restPostService.getPost(id);
		if(bean!=null) {
			return ResponseEntity.ok(bean);
		}
		return new ResponseEntity("Record not found", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/deletepost/{id}")
	public ResponseEntity<String> delPost(@PathVariable("id") String id){
		restPostService.deletePost(id);		
		return new ResponseEntity<String>(HttpStatus.OK);			
	}

	@PostMapping("/createpost")
	public ResponseEntity<String> createPost(PostBean postVo){
		restPostService.savePost(postVo);		
		return new ResponseEntity<String>(HttpStatus.OK);			
	}
	
	@PutMapping("/updatepost")
	public ResponseEntity<String> updatePost(PostBean postVo){
		if(restPostService.updatePost(postVo))	
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity("Record not found", HttpStatus.BAD_REQUEST);
	}


}
	