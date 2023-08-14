package com.google.phonebook.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.phonebook.dao.PhonebookDao;
import com.google.phonebook.dao.UserTableDao;
import com.google.phonebook.dto.PhoneBookDto;
import com.google.phonebook.dto.UserTableDto;

@Controller
@RequestMapping("/phonebook")
public class PhonebookController {
	
	PhonebookDao phonebookDao;
	UserTableDao usertableDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public PhonebookController(PhonebookDao phonebookDao, UserTableDao usertableDao) {
		this.phonebookDao = phonebookDao;
		this.usertableDao = usertableDao;
	}
	
	@Value("${phonebook.imgdir}")
	String fdir;
	
//	회원가입 화면
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
//	회원가입 처리
	@PostMapping("/up")
	public String signup(@ModelAttribute UserTableDto usertalbeDto
						,Model model 
						,@RequestParam("file") MultipartFile file) {
		//2.0 파일 처리 => 파일 객체 생성, 저장
		File dest = new File(fdir +"/"+ file.getOriginalFilename()); //파일 생성x(경로+파일명)
		
		try {
			//2.1 정상처리
			file.transferTo(dest);//파일 저장
			usertalbeDto.setImgnm("/images/"+dest.getName());//중복이미지일시 변경된이름으로get
			logger.info(dest.getName());
			usertableDao.insert(usertalbeDto);
		} catch (Exception e) {
			//2.2 비정상처리 => 화면에 비정상처리 경고메세지보내야함
			e.printStackTrace();
			logger.warn("회원가입 과정에서의 문제 발생");
			model.addAttribute("error","회원가입이 정상적으로 되지 않았습니다.");
		}
		
		return "redirect:/phonebook/signin";
	}
	
//	로그인 화면
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	
//	로그인 처리
	@PostMapping("/in")
//	http://localhost:3570/phonebook/in?userid=hong&userpassword=1234
	public String signin(@RequestParam(value="userid",required=false) String userid
						,@RequestParam(value="userpassword",required=false) String userpassword
						,HttpServletRequest request
						,Model model) throws Exception {
		ArrayList<UserTableDto> loginMember = usertableDao.searchUser(userid, userpassword);
		HttpSession session = request.getSession();
		
		session.setAttribute("loginMember", loginMember);					// 로그인한 회원 아이디, 비밀번호
		session.setAttribute("image",loginMember.get(0).getImgnm());		// 로그인한 회원의 프로필 이미지
		session.setAttribute("username", loginMember.get(0).getUsername()); // 로그인한 회원의 이름
		
		if (loginMember.size() > 0) {
			return "redirect:/phonebook/login/afterloginmain";			
		}
		else {
			logger.warn("로그인 과정에서 문제 발생");
			model.addAttribute("error", "로그인 실패");	
			return "signin";
		}
	}
	
//	로그인 후 메인화면
	@GetMapping("/login/afterloginmain")
	public String afterLoginMain() {
		return "afterloginmain";
	}
//	로그인 전 메인화면
	@GetMapping("/beforelogin")
	public String beforeLoginMain() {
		return "beforelogin";
	}
	
//	전체 전화번호부 목록
	@SuppressWarnings("unchecked")
	@GetMapping("/login/searchall")
	public String searchAll(ServletRequest request
							, Model model) {
		
		
		ArrayList<PhoneBookDto> phonebookdto; 
		ArrayList<UserTableDto> usertabledto;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		session.setAttribute("loginMember", usertabledto);
		session.setAttribute("image",usertabledto.get(0).getImgnm());
		session.setAttribute("username", usertabledto.get(0).getUsername());
		
		String userid = usertabledto.get(0).getUserid();		// seseion에 저장되어있는 회원 정보 추출, 아이디 추출
		try {
			
			phonebookdto = phonebookDao.searchAll(userid);
			model.addAttribute("phonebooklist",phonebookdto);
			int listsize = phonebookdto.size();
			 model.addAttribute("listsize", listsize);
			logger.info(userid);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("전체목록보기에서 문제 발생");
			model.addAttribute("error", "전체목록을 불러오지 못했습니다.");
		}
		return "searchall";
	}
	
//	연락처 추가하기 화면
	@SuppressWarnings("unchecked")
	@GetMapping("/login/addmember")
	public String addmember(ServletRequest request
							, Model model) {
		
		ArrayList<UserTableDto> usertabledto;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		session.setAttribute("loginMember", usertabledto);
		session.setAttribute("image",usertabledto.get(0).getImgnm());
		session.setAttribute("username", usertabledto.get(0).getUsername());
		
		return "addmember";
	}
	
//	연락처 추가 
	@SuppressWarnings("unchecked")
	@PostMapping("/login/add")  
	public String add(@ModelAttribute PhoneBookDto phonebookdto
						, Model model
						, ServletRequest request) {
		
		PhonebookDao phonebookDao = new PhonebookDao();
		ArrayList<UserTableDto> usertabledto;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		
		String userid = usertabledto.get(0).getUserid();
		//세션에서 가져온 userid를 포함, 입력받은 dto(userid, membernm, phonenumber, address, groupnm)
		phonebookdto.setUserid(userid);
		
		try {
			logger.info("연락처 추가 성공");
			phonebookDao.insert(phonebookdto);

		}catch(Exception e) {
			e.printStackTrace();
			logger.info("연락처 추가 과정에서 문제 발생!!");
			model.addAttribute("error", "연락처가 정상적으로 등록되지 않았습니다!!");
		}
		return "redirect:/phonebook/login/searchall";
	}
	
// 삭제하기 
	@SuppressWarnings("unchecked")
	@GetMapping("/login/delete/{phonenumber}")
	public String delete(@PathVariable String phonenumber
						, ServletRequest request
							) {
		
		ArrayList<UserTableDto> usertabledto;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		
		String userid = usertabledto.get(0).getUserid();
		try {
			phonebookDao.delete(userid, phonenumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/phonebook/login/searchall";
		
		
		
	}//delete() end
	
	
//	이름 검색, 출력
	@SuppressWarnings("unchecked")
	@GetMapping("/login/searchname")
	public String searchName(@RequestParam(value="searchnm",required=false) String searchnm
							, ServletRequest request
							, Model model) {

		ArrayList<PhoneBookDto> phonebookList;
		ArrayList<UserTableDto> usertabledto;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		
		String userid = usertabledto.get(0).getUserid();
		
		 try { 
			 phonebookList= phonebookDao.searchByName(searchnm,userid);
			 
			 model.addAttribute("phonebooklist",phonebookList);
			 model.addAttribute("listsize", phonebookList.size());
			 System.out.println(phonebookList);
			 logger.info(userid);
			
		 } catch (SQLException e) {
			 e.printStackTrace(); logger.warn("phonebook 이름 검색 과정에서의 문제 발생");
			 model.addAttribute("error","이름 검색 결과를 정상적으로 가져오지 못했습니다."); 
		 }
		return "searchall";
	}
	
//	연락처 수정하기
	@SuppressWarnings("unchecked")
	@PostMapping("/login/update")
	public String update(@ModelAttribute PhoneBookDto phonebookdto
						, @RequestParam(value = "phonenumber", required = false) String phonenumber
						, Model model
						, ServletRequest request ) {
	
		PhonebookDao phonebookDao = new PhonebookDao();
		ArrayList<UserTableDto> usertabledto;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		System.out.println(usertabledto);
		String userid = usertabledto.get(0).getUserid();

		phonebookdto.setUserid(userid);
		phonebookdto.setPhonenumber(phonenumber);
		
		try {
			phonebookDao.update(phonebookdto);
			logger.info("연락처 수정 성공");

		}catch(Exception e) {
			e.printStackTrace();
			logger.info("연락처 수정 과정에서 문제 발생!!");
			model.addAttribute("error", "연락처가 정상적으로 수정되지 않았습니다!!");
		}
		
		return "redirect:/phonebook/login/searchall";
	}
	
//	로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    return "redirect:/phonebook/beforelogin";
	}
	
	
//	id 중복 체크
	@ResponseBody
	@RequestMapping(value="/idcheck" , method=RequestMethod.POST)
	public int check(@RequestParam(value = "userid", required = false) String userid) {
		int check = 0;
		try {
			ArrayList<UserTableDto> list = usertableDao.signupidcheck(userid);
			if(list.size() > 0) {
				check = 1;
			}else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

//	phonenumber 중복 체크
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/pncheck", method = RequestMethod.POST)
	public int pncheck(@RequestParam(value = "phonenumber", required = false) String phonenumber
					, ServletRequest request) {
		
		ArrayList<UserTableDto> usertabledto;
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		usertabledto = (ArrayList<UserTableDto>) session.getAttribute("loginMember");
		System.out.println(usertabledto);
		String userid = usertabledto.get(0).getUserid();

		int check = 0;
		try {
			ArrayList<PhoneBookDto> list = phonebookDao.addmemberpncheck(phonenumber, userid);
				if(list.size() == 0) {
					check = 1;
				} else {
				check = 0;
			}
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
//	등록, 수정 이름 길이 확인 :: 2자 이상
	@ResponseBody
	@RequestMapping(value="/namecheck" , method=RequestMethod.POST)
	public int namecheck(@RequestParam(value = "membernm", required = false) String membernm) {
		int check = 0;
		try {
			if(membernm.length()<2 || membernm.length()==0) {
				check = 1;
			}
			else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
//	등록, 수정 주소 길이 확인 :: 2자이상
	@ResponseBody
	@RequestMapping(value="/addresscheck" , method=RequestMethod.POST)
	public int addresscheck(@RequestParam(value = "address", required = false) String address) {
		int check = 0;
		try {
			if(address.length()<2 || address.length()==0) {
				check = 1;
			}
			else {
				check = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}
