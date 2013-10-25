package com.cg.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.cg.domain.Transaction;
@Controller
@RequestMapping("/")
public class MerchantController {

	@Autowired
	public ServiceClass sc;
	/*DineshKana*/
	@RequestMapping(value="SignUpMerchant",method=RequestMethod.GET)
	public String signUpMerchant(){
		return "MerchantSignUp";
	}
	/*DineshKana*/
	@RequestMapping(value="AddMerchant",method=RequestMethod.POST)	
	public String addMerchant(@RequestParam("n1")String mer_id,@RequestParam("n2")String mer_name,@RequestParam("n3")String mer_pwd,@RequestParam("n4")String mer_email,@RequestParam("n5")String mer_type,@RequestParam("n6")String mer_sec_ques,@RequestParam("n7")String mer_ans,@RequestParam("n8")String mer_add){
		boolean flag=sc.addMerchant(mer_id,mer_name,mer_pwd,mer_email,mer_type,mer_sec_ques,mer_ans,mer_add);
		String message=new String();
		if (flag) {
			message = "MerchantHome";
		} else {
			message = "MerchantNotAddedMessage";
		}
		return message;
	}
	
	
	
	/*Alok's controller for merchant*/
	@RequestMapping(value="fileinsertdescriptionformerchant",method=RequestMethod.POST)
	public String insertproductdescMerchant(@RequestParam("datasize")MultipartFile datasize) throws FileNotFoundException, NumberFormatException, ParseException{
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

		return "UpdateSuccessByMerchant";
	}
	@RequestMapping(value="deleteproductbymerchant",method=RequestMethod.GET)

	public String merchantdel(ModelMap map,HttpServletRequest request){
		List<Transaction> list =sc.getOrder((String) request.getSession().getAttribute("userid"));
		map.put("list",list);
		return "DeleteProductsByMerchant";
	}
	@RequestMapping(value="reduceproductbymerchant",method=RequestMethod.GET)
	public String reduceInventory(ModelMap map,HttpServletRequest request){
		List<Transaction> list =sc.getOrder((String) request.getSession().getAttribute("userid"));
		sc.deleteProduct1(list);
		return "UpdateSuccessByMerchant";
	}
	@RequestMapping(value="showproductsformerchant",method=RequestMethod.GET)
	public String showproductofmerchant(ModelMap map,HttpServletRequest request){
		List<Product> list=sc.getAllProductofMerchant((String) request.getSession().getAttribute("userid"));
		map.put("list",list);
		return "ShowProductsForMerchant";
	}
	@RequestMapping(value="fileinsertmerchant",method=RequestMethod.POST)
	public String insertproduct(@RequestParam("datasize")MultipartFile datasize) throws NumberFormatException, ParseException, IOException{
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
			}
		} 
		catch (IOException e) {
			br.close();
			
			e.printStackTrace();
		}
		
		sc.addProduct(pojoList);
		return "AddProductDescriptionByMerchant";
	}
	@RequestMapping(value="addproductsbymerchant",method=RequestMethod.GET)
	   
	  public String meradd(){
		
		  return "AddProductsByMerchant";
	  }
	@RequestMapping(value="checkorders",method=RequestMethod.GET)
	public String checkorder(ModelMap map,HttpServletRequest request){
		
		List<Transaction> list =sc.getOrder((String) request.getSession().getAttribute("userid"));
		map.put("list",list);
		
		return "CheckOrdersByMerchant";
	}

}
