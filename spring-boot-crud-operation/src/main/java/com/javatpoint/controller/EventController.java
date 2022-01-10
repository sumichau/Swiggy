package com.javatpoint.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javatpoint.model.Expense;
import com.javatpoint.model.UserInfo;
//mark class as Controller
@RestController
public class EventController 
{
	static List <UserInfo> lists=new ArrayList<UserInfo>();
	static HashMap<String, Integer> hs=new HashMap<String, Integer>();
	
	@PostMapping("/expense")
	public void saveexpense(@RequestBody Expense ex) 
	{
		
		 HashMap<String, Integer> hstemp=new HashMap<String, Integer>();

		
		List <UserInfo> ls=ex.getUserinfos();
		for(UserInfo ui:ls) {
			ui.setBalance(ui.getPaid()- ui.getOwns());
		}
		if(!lists.isEmpty()) {
			for(UserInfo ui:ls) {
				for(UserInfo uim:lists) {
					if(uim.getUserId().equals(ui.getUserId())) {
					ui.setOwns(ui.getOwns()+uim.getOwns());	
					ui.setPaid(ui.getPaid()+uim.getPaid());	
					ui.setBalance(ui.getPaid()-ui.getOwns());
					}
				}
			}
			
		}
		lists.clear();
		 lists.addAll(ls);
		 
		//List<UserInfo> us=ls.stream().sorted(Comparator.comparingInt(UserInfo::getBalance)).collect(Collectors.toList());
		for(UserInfo ui:ls) {
			if(ui.getBalance()>0 ) {
				for(UserInfo uii:ls) {
					if(uii.getBalance()<0 && ui.getBalance()>0){
						if(ui.getBalance()>Math.abs(uii.getBalance())) {
						ui.setBalance(ui.getBalance()+uii.getBalance());
						hstemp.put(uii.getUserId()+"->"+ ui.getUserId(),Math.abs(uii.getBalance()));
						uii.setBalance(0);
						}
						else {
						hstemp.put(uii.getUserId()+"->"+ ui.getUserId(),Math.abs(ui.getBalance()));
						uii.setBalance(ui.getBalance()+uii.getBalance());
						ui.setBalance(0);
						
						
					}}
		
				}}

			}
		hs.clear();
		hs.putAll(hstemp);
		//FOR SECOND qUERY
			}
	
	@GetMapping("/getAll")
	public List<String> getAllExpense() 
	{
		List<String> ls=new ArrayList<String>();
		for(Map.Entry<String, Integer> entry:hs.entrySet()) {
			ls.add(entry.getKey() + ":" +entry.getValue());
		}
		return ls;
	
	}
		
	
	@GetMapping("/getbyuserid/{id}")
	public List<String> getbyuserid(@PathVariable String id) 
	{
		List<String> ls=new ArrayList<String>();
		for(Map.Entry<String, Integer> entry:hs.entrySet()) {
			if(entry.getKey().substring(0,2).equals(id))
			ls.add(entry.getKey() + ":" +entry.getValue());
		}
		return ls;
		
	}
	@GetMapping("/clear")
	public void getclear() 
	{
		hs.clear();
		lists.clear();
		
	}
		
		
		
	
	
}
