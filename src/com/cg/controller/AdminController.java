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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cg.service.ServiceClass;
import com.cg.domain.Category;
import com.cg.domain.CategoryCount;
import com.cg.domain.MarketShare;
import com.cg.domain.MarketShareRevenue;
import com.cg.domain.Merchant;
import com.cg.domain.MerchantRatings;
import com.cg.domain.MostBoughtProducts;
import com.cg.domain.Product;
import com.cg.domain.ProductDescription;
import com.cg.domain.ProductRatings;
import com.cg.domain.ProductShare;
import com.cg.domain.ProductViews;
import com.cg.domain.RevenueTable;
import com.cg.domain.WishlistCount;
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
		
		try {
			while((line = br.readLine()) != null) {  
				Merchant m=new Merchant();
				Category cat=new Category();
				String[] fields = line.split(",");
				cat.setCategoryId(fields[1]);
				m.setMerchantId(fields[2]);
				Product p = new Product(fields[0],cat,m,fields[3],Double.parseDouble(fields[4]),d,fields[5],fields[6],Long.parseLong(fields[7]));
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
	/*DineshReddy*/

	@RequestMapping(value="createOfferAdmin",method=RequestMethod.POST)
	public @ResponseBody boolean createOffer(@RequestParam(value="name") String name,@RequestParam(value="type") String type,@RequestParam(value="value") String value,@RequestParam(value="des") String des,HttpSession session){
		String merchantId="Admin";
		return sc.createOffer(name, type, value,des, merchantId);
	}
	@RequestMapping(value="createOfferAdmin")
	public String createOffer(ModelMap model){
		model.addAttribute("test", true);
		return "CreateOfferByAdmin";
	}

	@RequestMapping(value="removeOfferByAdmin")
	public String removeOfferByAdmin(ModelMap model) {
		model.addAttribute("list", sc.getAllOfferByAdmin());
		return "removeOfferByAdmin";
	}
	@RequestMapping(value="removeOfferByAdmin",method=RequestMethod.POST)
	public String removeOfferByAdmin(ModelMap model,@RequestParam("selectedId")String[] values) {		
		model.addAttribute("result", sc.deleteOfferByAdmin(values));
		model.addAttribute("list", sc.getAllOfferByAdmin());
		return "removeOfferByAdmin";
	}
	/*Shaswat*/
	@RequestMapping(value="reports",method = RequestMethod.GET)
	public String getAllWishlist(ModelMap m) {
		List<WishlistCount> list = sc.researchWishlist();
		m.put("list1", list);
		List<ProductViews> list2 = sc.researchViewcount();
		m.put("list2", list2);
		List<MostBoughtProducts> list3 = sc.researchTransactions();
		m.put("list3", list3);
		List<MarketShare> list4 = sc.researchMarketShare();
		m.put("list4", list4);
		List<Integer> list5 = sc.researchOrderStatus();
		m.put("list5", list5);
		List<MerchantRatings> list6 = sc.researchMerchant();
		m.put("list6", list6);
		List<ProductRatings> list7 = sc.researchProduct();
		m.put("list7", list7);
		List<ProductShare> list8 = sc.researchProductShare();
		m.put("list8", list8);
		List<RevenueTable> list9 = sc.researchQuarterRevenue();
		m.put("list9", list9);
		List<CategoryCount> list10 = sc.researchCategorySales();
		m.put("list10", list10);
		List<MarketShareRevenue> list11 = sc.researchMarketShareRevenue();
		m.put("list11", list11);

		return "ReportForAdmin";
	}



}
