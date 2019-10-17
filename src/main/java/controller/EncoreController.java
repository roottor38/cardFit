//package controller;
//
//import java.util.ArrayList;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class EncoreController {
//
//	public EncoreController() {
//		System.out.println("***** EncoreController *****");
//	}
//
//	@RequestMapping("/myCardBenefit")
//	public String myCardBenefit() {
//		return "myCardBenefit";
//	}
//
//	@PostMapping("/myCardBenefit")
//	public String myCardBenefit(@RequestParam(value = "bankName") String bankName,
//			@RequestParam(value = "cardName") String cardName, Model model) {
//		if (cardName == null || cardName.length() == 0 || cardName.equals("")) {
//			
//			return "myCardBenefit";
//		} else {
//			
//			model.addAttribute("card", //객체저장);
//			return "myCardBenefit";
//		}
//	}
//
//	@RequestMapping("/option")
//	public String option() {
//		return "option";
//	}
//
//	@PostMapping("/option")
//	public String option(@RequestParam(value="movie", required=false) String movie, @RequestParam(value="telecome", required=false) String telecome, @RequestParam(value="traffic", required=false) String traffic,
//			@RequestParam(value="offshop", required=false) String offshop, @RequestParam(value="onshop", required=false) String onshop, @RequestParam(value="food", required=false) String food,
//			@RequestParam(value="cafe", required=false) String cafe, @RequestParam(value="others", required=false) String others, Model model) {
//		//체크 박스로 추천 로직
//		
//		model.addAttribute("data", //객체);
//		return "option";
//	}
//
//	@RequestMapping("/keyword")
//	public String keyword() {
//		return "keyword";
//	}
//
//	@PostMapping("/keyword")
//	public String keyword(@RequestParam(value = "search") String search, Model model) {
//		if (search == null || search.equals("") || search.length() == 0) {
//			return "keyword";
//		} else {
//			//키워드 추천 로직
//			
//			model.addAttribute("data", //객체);
//		}
//		return "keyword";
//	}
//
//	@PostMapping("/cardControl")
//	public String cardControl(@RequestParam(value = "bankName") String bankName,
//			@RequestParam(value = "cardName") String cardName, Model model) {
//		//관리자 카드 검색 로직( 위 내카드 혜택보기랑 같은 로직)
//		
//		model.addAttribute("card", //객체);
//		return "cardControl";
//	}
//
//	@GetMapping("/deleteCard/{bankName}/{cardName}")
//	public String deleteCard(@PathVariable(value = "bankName") String bankName,
//			@PathVariable(value = "cardName") String cardName) {
//		//관리자 은행명하고 카드명으로 카드 삭제 로직
//		
//		
//		return "redirect:/admin.html";
//	}
//	
//	@RequestMapping("/createCard")
//	public String createCard() {
//		return "createCard";
//	}
//	
//	@PostMapping("/createCard")
//	public String createCard(@RequestParam(value = "bankName") String bankName,
//			@RequestParam(value = "cardName") String cardName,
//			@RequestParam(value = "condition") String condition,
//			@RequestParam(value = "benefit") String benefit) {
//		//관리자 카드 추가 로직
//		
//		return "redirect:/admin.html";
//	}
//
//	@RequestMapping("/updateCard/{bankName}/{cardName}")
//	public String updateCard(@PathVariable(value = "bankName") String bankName,
//			@PathVariable(value = "cardName") String cardName, Model model) {
//		//카드 수정을 위한 은행명하고 카드명으로 값 검색 로직
//		
//		
//		model.addAttribute("card", //객체);
//		return "updateCard";
//	}
//	
//	@PostMapping("/updateCard")
//	public String updateCard(@RequestParam(value = "bankName") String bankName,
//			@RequestParam(value = "cardName") String cardName,
//			@RequestParam(value = "condition") String condition,
//			@RequestParam(value = "benefit") String benefit) {
//		//관리자 카드 정보 수정 로직
//		
//		return "redirect:/admin.html";
//	}
//
//}
