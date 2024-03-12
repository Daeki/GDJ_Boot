package com.winter.app.member;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.groups.MemberJoinGroup;
import com.winter.app.member.groups.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String login(@ModelAttribute MemberVO memberVO)throws Exception{
		memberVO.setUsername("winter");
		memberVO.setPassword("123456");
		return "member/login";
		
	}
	
	@GetMapping("page")
	public void page(HttpSession session)throws Exception{
		
		//Attribut Name???
		Enumeration<String> en = session.getAttributeNames();
		
		while(en.hasMoreElements()) {
			log.info("=== attribute {}", en.nextElement());
		}
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		log.info("=== obj {}", obj);
		
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		String name  = contextImpl.getAuthentication().getName();
		MemberVO memberVO = (MemberVO)contextImpl.getAuthentication().getPrincipal();
		
		log.info("=== Name {}", name);
		log.info("=== MemberVO {}", memberVO);
		
		SecurityContext context = SecurityContextHolder.getContext();
		context.getAuthentication();
		//DB 조회
		
	}
	
	
	@GetMapping("update")
	public void update(Model model)throws Exception{
		
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:../";
	}
	
	
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO)throws Exception{
		
		//model.addAttribute("memberVO", memberVO);
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberJoinGroup.class) MemberVO memberVO,BindingResult bindingResult, Model model)throws Exception{
		
		boolean check= memberService.checkMember(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		
				
		int result = memberService.add(memberVO);
		model.addAttribute("result", "member.add.result");
		model.addAttribute("path", "/");
		//service로 보냄
		return "commons/result";
	}
	

}
