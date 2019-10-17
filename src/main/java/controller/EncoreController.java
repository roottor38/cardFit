package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.domain.Card;

@Controller
public class EncoreController {


	public EncoreController() {
		System.out.println("***** EncoreController *****");
	}

	@RequestMapping("/myCardBenefit")
	public String myCardBenefit() {
		return "myCardBenefit";
	}
	
	@PostMapping("/myCardBenefit")
	public String myCardBenefit(@RequestParam(value="bankName") String bankName,@RequestParam(value="cardName") String cardName, Model model){
		Card card = new Card();
		card.setBankName(bankName);
		card.setCardName(cardName);
		card.setCondition(30);
		card.setBenefit("응 없엉~");
		System.out.println(card);
		model.addAttribute("card", card);
		return "myCardBenefit";
	}
	
	@RequestMapping("/option")
	public String option() {
		return "option";
	}
	
	@PostMapping("/option")
	public String option(@RequestParam String a) {
		
		return "option";
	}
	

//	@GetMapping("/findMembersBySeq")
//	public void findMembersBySeq(String seq) {
//		Card member = MembersRepo.findMembersBySeq(Long.parseLong(seq));
//		System.out.println(member);
//	}
//
//	@GetMapping("/findMembersByRegion")
//	public void findMembersByRegion(String region) {
//		List<Card> Members = MembersRepo.findMembersByRegion(region);
//		System.out.println(Members);
//	}
//
//	@GetMapping("/findMembersByAge")
//	public void findMembersByAge(String age) {
//		List<Card> Members = MembersRepo.findMembersByAge(Long.parseLong(age));
//		System.out.println(Members);
//	}
//
//	@GetMapping("/setFixedNameFor")
//	public void setFixedNameFor(String name, String seq) {
//		MembersRepo.setFixedNameFor(name, Long.parseLong(seq));
//	}
//
//	@GetMapping("/setFixedRegionFor")
//	public void setFixedRegionFor(String region, String seq) {
//		MembersRepo.setFixedRegionFor(region, Long.parseLong(seq));
//	}
//
//	@GetMapping("/deleteInBulkBySeq")
//	public void deleteInBulkBySeq(String seq) {
//		Card member = MembersRepo.findMembersBySeq(Long.parseLong(seq));
//		MembersRepo.delete(member);
//	}
}
