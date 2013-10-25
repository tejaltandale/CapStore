package com.cg.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cg.service.ServiceClass;
import com.cg.domain.Category;
import com.cg.domain.Merchant;
import com.cg.domain.Product;
import com.cg.domain.ProductDescription;
@Controller
@RequestMapping("/")
public class AdminController {

	@Autowired
	public ServiceClass sc;
	@RequestMapping(value="admin_home",method=RequestMethod.POST)
	public String showAdmin(@RequestParam("username")String u , @RequestParam("password")String p){
		if(u.equals("Admin") && p.equals("PASSWORD")){
			return "Admin";
		}else{
			return "Admin_Login";
		}
	}
	/*Bismay*/
	@RequestMapping(value="getAllMerchants")
	public String getAllMerchants(ModelMap map) {
		List<Merchant> mList = sc.getAllMerchants();
		map.addAttribute("list",mList);
		return "AllMerchants";
	}
	/*Bismay*/
	@RequestMapping(value="getPendingMerchants")
	public String getPendingMerchants(ModelMap map){
		List<Merchant> mList = sc.getPendingMerchants();
		map.addAttribute("list",mList);
		return "Pending";
	}
	/*Bismay*/
	@RequestMapping(value="getApprovedMerchants")
	public String getApprovedMerchants(ModelMap map){
		List<Merchant> mList = sc.getApprovedMerchants();
		map.addAttribute("list",mList);
		return "Approved";
	}
	/*Bismay*/
	@RequestMapping(value="getRemovedOrRejectedMerchants")
	public String getOtherMerchants(ModelMap map){
		map.addAttribute("list",sc.getOtherMerchants());
		return "OtherMerchants";
	}
	/*Bismay*/
	@RequestMapping(value="approveAMerchant")
	public String approveMerchant(@RequestParam("merchant_id")String id,ModelMap map){
		sc.approveMerchant(id);
		map.addAttribute("list",sc.getPendingMerchants());
		return "Pending";
	}
	/*Bismay*/
	@RequestMapping(value="rejectAMerchant")
	public String rejectMerchant(@RequestParam("merchant_id")String id,ModelMap map){
		sc.rejectMerchant(id);
		map.addAttribute("list",sc.getPendingMerchants());
		return "Pending";
	}
	/*Bismay*/
	@RequestMapping(value="removeAMerchant")
	public String removeMerchant(@RequestParam("merchant_id")String id,ModelMap map){
		sc.removeMerchant(id);
		map.addAttribute("list",sc.getApprovedMerchants());
		return "Approved";
	}
	/*Bismay*/
	@RequestMapping(value="reapproveAMerchant")
	public String reApproveMerchant(@RequestParam("merchant_id")String id,ModelMap map){
		sc.approveMerchant(id);
		map.addAttribute("list",sc.getOtherMerchants());
		return "OtherMerchants";
	}
	/*Bismay*/
	@RequestMapping(value="inviteAMerchant")
	public String inviteMerchants(){
		return "InvitationToMerchant";
	}	
	/*Bismay*/
	@RequestMapping(value="sendInvitationToNewMerchant")
	public String sendInvitation(@RequestParam("sendTo")String toaddress, @RequestParam("subject")String subject, @RequestParam("msgcontent")String content){
		if(sc.emailSender(toaddress, subject, content)){
			return "inviteSuccess";
		}
		else return "inviteFailure";
	}
	/*Alok*/
	@RequestMapping(value="addproducts")

	public String add(){

		return "AddProducts";
	}
	/*Alok*/
	@RequestMapping(value="updateproducts",method=RequestMethod.GET)

	public String del(ModelMap map){
		List<Product> list=sc.getAllProduct();
		map.put("list",list);
		return "UpdateInventoryByAdmin";
	}
	/*Alok*/
	@RequestMapping(value="findproducts",method=RequestMethod.GET)
	public String findUser(ModelMap map){
		List<Product> userlist=sc.getAllProduct();
		map.put("list",userlist);
		return "ShowInventoryForAdmin";
	}
/*Alok*/
	@RequestMapping(value="fileinsert",method=RequestMethod.POST)
	public String insert(@RequestParam("datasize")MultipartFile datasize) throws FileNotFoundException, NumberFormatException, ParseException{
		List<Product> pojoList = new ArrayList<Product>();
		BufferedReader br = new BufferedReader(new FileReader("D:/Allfiles/"+datasize.getOriginalFilename()));
		String line = "";
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Date d=new Date();
		Merchant m=new Merchant();
		Category cat=new Category();
		try {
			while((line = br.readLine()) != null) {  
				String[] fields = line.split(",");
				cat.setCategoryId(fields[1]);
				m.setMerchantId(fields[2]);
				Product p = new Product(fields[0],cat,m,fields[3],Double.parseDouble(fields[4]),d,fields[6],d,fields[8],Long.parseLong(fields[9]));
				pojoList.add(p);
				System.out.println(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.addProduct(pojoList);
		return "AddProductDescription";
	}
	/*Alok*/
	@RequestMapping(value="fileinsertdescription",method=RequestMethod.POST)
	public String insertproductdesc(@RequestParam("datasize")MultipartFile datasize) throws FileNotFoundException, NumberFormatException, ParseException{
		List<ProductDescription> pojoList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("D:/Allfiles/"+datasize.getOriginalFilename()));
		String line = "";
		try {
			while((line = br.readLine()) != null) {  
				String[] fields = line.split(",");
				for(int i=1;i<fields.length;i++){
					Product pod = new Product();
					pod.setProductId(fields[0]);
					String[] sub=fields[i].split("=");
					ProductDescription p = new  ProductDescription(sub[0],sub[1],pod);
					pojoList.add(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.addProductDesc(pojoList);
		return "InventoryAdditionSuccess";
	}
	/*Alok*/

	@RequestMapping(value="updateinventory")
	public String navigate(@RequestParam("id")String ID,@RequestParam("amt")String quan,@RequestParam("update")String control){
		if(control.equals("Reduce_stock")){
			sc.deleteProduct(ID,Integer.parseInt(quan));
			
			return "UpdateSuccessByAdmin";
		}
		else{
			sc.increaseProduct(ID,Integer.parseInt(quan));
			
			return "UpdateSuccessByAdmin";
		}
	}
	
}
