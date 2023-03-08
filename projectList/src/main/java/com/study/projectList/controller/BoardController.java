package com.study.projectList.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.study.projectList.service.BoardService;
import com.study.projectList.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	BoardService boardSerivce;

	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

		JsonObject jsonObject = new JsonObject();

		String fileRoot = "C:\\dev\\workspace\\projectList\\summernote_image\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명

		File targetFile = new File(fileRoot + savedFileName);

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
			System.out.println("jsonObject!!!!"+jsonObject);

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}

		return jsonObject;
	}

	@PostMapping("/insertBoard.do")
	@ResponseBody
	public void insertBoard(BoardVo Vo) {
		boardSerivce.insertBoard(Vo);
	}

	@GetMapping("/main")
	public String goMain(Model model) {
		model.addAttribute("list", boardSerivce.selectBoard());
		model.addAttribute("count", boardSerivce.countBoard());

		return "/main";
	}
	@GetMapping("/view")
	public String goView(int no, Model model) {
		BoardVo Vo = boardSerivce.viewBoard(no);
		System.out.println(Vo.getContent());
		model.addAttribute("view", boardSerivce.viewBoard(no));
		return "/view";
	}
	@GetMapping("/write")
	public String goWrite() {
		return "/write";
	}

}
