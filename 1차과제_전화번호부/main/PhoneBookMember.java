package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import vo.Member;

public class PhoneBookMember {
	private static HashMap<String, Member> members = 
				new HashMap<String, Member>();//class안에서만 사용~private
//	메뉴 출력
	public static void showMenu() {
		System.out.println("\n===============================");
		System.out.println("    다음 메뉴 중 하나를 선택하세요.");
		System.out.println("===============================");
		System.out.println("1.회원 추가");
		System.out.println("2.회원 목록 보기");
		System.out.println("3.회원 정보 수정하기");
		System.out.println("4.회원 삭제");
		System.out.println("5.종료");
		System.out.print(">> 메뉴 선택  : ");
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		메뉴 번호 선택
		while (true) {
			showMenu();
			String menuSelect = scanner.next();
			if (menuSelect.equals("1")) {
				addMember(scanner);
			}else if (members.isEmpty()) {
				System.out.println("멤버가 없습니다.");
				break;
			}else if(menuSelect.equals("2")) {
				showMembers(scanner);
			}else if(menuSelect.equals("3")) {
				replaceMember(scanner);
			}else if (menuSelect.equals("4")) {
				deleteMember(scanner);
			}else if (menuSelect.equals("5")) {
				break;
			}else {
				System.out.println("선택 오류 다시 입력");
			}
		}//while end
		System.out.println("==== 연락처 프로그램을 종료합니다. ====");
		scanner.close();
	}//main end
	
//	1) 회원 추가 메소드 -> 키보드 입력 -> Member object setting -> HashMap put
	public static void addMember(Scanner scanner) {
		//member object new -> 키보드입력값 setting
		Member member = new Member(); //vo.Member new
		
		//이름입력
		System.out.print("\n");
		String name = scanner.nextLine(); //name 초기화(nextLine()사용시해야함)
		while(true){
			System.out.print("이름 : ");
			name = scanner.nextLine(); //이름 받기
			if(name.trim().length()>1){//앞뒤 공백제거 후의 길이 > 1
				member.setName(name.trim());//공백 제거 후 set
				break;
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
		//전화번호입력
		while(true){
			System.out.print("전화번호(ex 01012345678) : ");
			String phonenumber = scanner.nextLine();
			try {//숫자가 아닌 문자가 들어왔을 때의 예외 처리
				int intphonenumber = Integer.valueOf(phonenumber.trim());
			}catch(NumberFormatException e) {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				continue;
			}
			if(phonenumber.trim().length() == 11){
				if (members.containsKey(phonenumber)) {//전화번호 중복 해결
					System.out.println("이미 있는 전화번호입니다.");
					continue;
				}else {
					member.setPhonenumber(phonenumber.trim());
					break;
				}
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
		//주소입력
		while(true){
			System.out.print("주소 : "); 
			String address = scanner.nextLine();
			if(address.trim().length() > 1){
				member.setAddress(address.trim());
				break;
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
		//그룹입력
		while(true){
			System.out.print("그룹(가족, 친구, 기타) : "); 
			String group = scanner.nextLine();
			if((group.equals("가족") 
					|| group.equals("친구") 
					|| group.equals("기타"))){
				member.setGroup(group.trim());
				break;
			}else {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
				continue;
			}
		}
		
//		HashMap members에 Member object 추가(put(key,member))
		members.put(member.getPhonenumber(), member);
		
		System.out.println("\n"+member.getName()+"님이 회원으로 정상추가되었습니다.");
	}//1) 회원 추가 메소드 종료
		
//  2) 회원 목록 출력 메소드 => 회원이 여러명 (Hash Map)있다. => 반복출력
	public static void showMembers(Scanner scanner) {
//		1. HashMap members의 키 값 set으로 뽑기 
		Set<String> keys = members.keySet();
//		2.Iterator object 생성 => key(전화번호) 반복처리
		Iterator<String> it = keys.iterator();
		
		System.out.println("\n===============================");
		System.out.println("         회원 목록 출력");
		System.out.println("===============================");
		System.out.println("전체 회원이 "+ members.size() +"명 있습니다.");

//		조회할 회원 이름 받기
		System.out.print("검색할 이름을 입력하세요(전체출력 : 전체) : ");
		String searchname = scanner.next();
		
//		3. 반복처리
		int count = 0;//일치하는 이름이 없을 경우 카운트
		while(it.hasNext()) {
			String key = it.next(); //key는 'members의 key'
			Member member = members.get(key);//key(전화번호)의 member class
			String membername = member.getName();//member class의 name
			
			if (membername.equals(searchname)) {//입력받은 이름이 member의 이름과 같으면
				System.out.println(member.toString());	//이름,전화번호,주소,그룹 출력
				count++;//일치하는 이름 있으면 +1
			}
			if (searchname.equals("전체")) {
				System.out.println(member.toString());
				count++;
			}
		}//while end
		if(count == 0) {//일치하는 이름 없음
			System.out.println("일치하는 회원이 없습니다.");
		}
	}//showMemebers() end
	
//  3)검색(이름)한 회원 정보수정 메소드(이름받기, 목록출력, 번호선택, 회원수정)
	public static void replaceMember(Scanner scanner) {
		Set<String> keys = members.keySet();
		Iterator<String> it = keys.iterator();
		Vector<String> countMember = new Vector<>();//수정 요청 멤버 벡터(index,phonenumber)
		countMember.add(0,null);//1부터 번호시작 ~ 인덱스 0 초기화
		
//		1. 이름받기
		System.out.print("수정할 회원의 이름을 입력하세요 : ");
		String searchname = scanner.next();
		
		int count = 0;//회원 순서 번호 매기기
//		
		while(it.hasNext()) {
			String key = it.next(); 
			Member member = members.get(key);
			String membername = member.getName();
			
//		2. 목록출력
			if (membername.equals(searchname)) {
				count++;
				System.out.println(count+"."+member.toString());//1.이름,번호,주소,그룹
				countMember.add(count, member.getPhonenumber());//인덱스(count)에 Phonenumber 넣기 
			}//목록출력 if end
		}//이름받기 while end 
		if(count == 0) {//일치하는 이름 없으면
			System.out.println("일치하는 회원이 없습니다.");
//		3. 번호선택
		}else {//일치하는 이름 있으면
			while(true) {
				System.out.print("수정할 회원의 번호를 입력하세요 : ");
				String memberNumber = scanner.next();
				//char값 소유 여부 검사
				for(int i = 0; i < memberNumber.length(); i++) {
					char charmemberNumber = memberNumber.charAt(i);//isDisit 사용을 위해 String-> char형변환
					
					if (Character.isDigit(charmemberNumber)==false) {//charmemberNumber가 int형이 아니면
						memberNumber = "오류"; 
						break;
					}
				}
				if(memberNumber.equals("오류")) {
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					continue;
				}else {//memeberNumber에 char가 없으면
					int intmemberNumber = Integer.valueOf(memberNumber);
					if ((intmemberNumber > count )
							|| (intmemberNumber < 0)){		
						System.out.println("없는 회원 번호입니다.");	
						continue;
//					4. 회원 수정
//					HashMap 에 Member object 추가(put),삭제(remove)
//					members.put(member.getPhonenumber(), member);
					}else {
						//벡터의 (intmemberNumber) 인덱스 해당 phoneNumber
						String memberphonenumber = countMember.get(intmemberNumber);
				
						//이름입력
						System.out.print("\n");
						String name = scanner.nextLine(); //name 초기화
						while(true){
							System.out.print("이름 : ");
							name = scanner.nextLine(); 
							if(name.trim().length()>1){//앞뒤 공백제거 후의 길이 >1
								//HashMap member의 memberphonenumber key의 member class의 name set
								members.get(memberphonenumber).setName(name);
								break;
							}else {	
								System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
								continue;
							}
						}
						//전화번호입력
						members.get(memberphonenumber).setPhonenumber(memberphonenumber);
						//주소입력
						while(true){
							System.out.print("주소 : "); 
							String address = scanner.nextLine();
							if(address.trim().length() > 1){
								members.get(memberphonenumber).setAddress(address);
								break;
							}else {
								System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
								continue;
							}
						}
						//그룹입력
						while(true){
							System.out.print("그룹(가족, 친구, 기타) : "); 
							String group = scanner.nextLine();
							if((group.equals("가족") 
									^ group.equals("친구") 
									^ group.equals("기타"))){
								members.get(memberphonenumber).setGroup(group);
								break;
							}else {
								System.out.println("잘못입력하셨습니다. 다시 입력하세요.");
								continue;
							}
						}
						System.out.println("\n"+members.get(memberphonenumber).getName()
												+"님이 정상수정되었습니다.");	
						break;
					}//수정요청 else end	
				}//번호입력, 수정 else end
			}//while end
		}//번호선택 count>0 while end
	}//수정메소드 end
	
//	4)회원 삭제 메소드(이름받기,목록출력,번호선택,삭제완료)
	public static void deleteMember(Scanner scanner) {
		Set<String> keys = members.keySet();
		Iterator<String> it = keys.iterator();
		Vector<String> countMember = new Vector<>();//수정요청 멤버 벡터
		countMember.add(0,"멤버");//1부터 번호시작 ~ 인덱스 0 초기화
		
		//1. 이름받기
		int count = 0;
		String searchname = scanner.nextLine();
		
		System.out.print("삭제할 회원의 이름을 입력하세요 : ");
		searchname = scanner.nextLine();
		while(it.hasNext()) {
			
			String key = it.next(); 
			Member member = members.get(key);
			String membername = member.getName();

//			2. 목록출력
			if (membername.equals(searchname.trim())) {
				count++;
				System.out.println(count+"."+member.toString());
				countMember.add(count, member.getPhonenumber());
			}//목록출력 if end

		}// 이름받기 while end
		if(count == 0) {
			System.out.println("일치하는 회원이 없습니다.");
//		3.번호선택
		}else {
				while(true) {
				System.out.print("수정할 회원의 번호를 입력하세요 : ");
				String memberNumber = scanner.next();
				
				//char 값 있는지 검사
				for(int i = 0; i < memberNumber.length(); i++) {
					char charmemberNumber = memberNumber.charAt(i);//isDisit 사용을 위해 String-> char형변환
					
					if (Character.isDigit(charmemberNumber)==false) {//charmemberNumber가 int형이 아니면
						memberNumber = "오류"; 
						break;
					}
				}
				if(memberNumber.equals("오류")) {
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					continue;
				}else {//memeberNumber에 char가 없으면
					int intmemberNumber = Integer.valueOf(memberNumber);
					if ((intmemberNumber > count )
							|| (intmemberNumber < 0)){		
						System.out.println("없는 회원 번호입니다.");	
						continue;
//					4.회원 삭제
					}else {
						String memberphonenumber = countMember.get(intmemberNumber);
						members.remove(memberphonenumber);
						System.out.println("삭제가 완료되었습니다.");
						break;
					}
				}
			}//while end
		}//번호선택 end	
	}//삭제메소드 end
}//class end
