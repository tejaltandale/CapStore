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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cg.service.ServiceClass;
import com.cg.domain.Category;
import com.cg.domain.Merchant;
import com.cg.domain.Product;
import com.cg.domain.ProductDescription;
import com.cg.domain.ProductMediaPath;
import com.cg.domain.Transaction;
@Controller
@RequestMapping("/")
public class MerchantController {

	@Value("#{albumResource['ipaddress']}")
	private String ipaddress;

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
	
	@RequestMapping(value="mediauploaded",method=RequestMethod.GET)
	public String redirecttoMerchantHome(){
		return "UpdateSuccessByMerchant";
	}
	
	
	@RequestMapping(value="fileinsertdescriptionformerchant",method=RequestMethod.POST)
	public String insertproductdescMerchant(@RequestParam("datasize")MultipartFile datasize) throws FileNotFoundException, NumberFormatException, ParseException{
		List<ProductDescription> pojoList=sc.insertproductdescMerchant(datasize);
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
	public String insertproduct(@RequestParam("datasize")MultipartFile datasize,ModelMap map,HttpServletRequest request) throws NumberFormatException, ParseException, IOException{
		List<Product> pojoList=sc.insertProduct(datasize,map);
		List<ProductMediaPath> l=(List<ProductMediaPath>)map.get("productmedias");
		String str=null;
		for(ProductMediaPath p:l){
			String url=request.getContextPath();
			/*sc.unzip(p.getProductmediapath());
			sc.extracteFiles(url,p.getProductmediapath(),p.getProduct());
			str=sc.getImages();*/
			map.addAttribute("image", str);
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

	/*Dinesh Kotha Reddy*/
	@RequestMapping(value="createOffer",method=RequestMethod.POST)
	public @ResponseBody boolean createOffer(@RequestParam(value="name") String name,@RequestParam(value="type") String type,@RequestParam(value="value") String value,@RequestParam(value="des") String des,HttpSession session){
		String merchantId=(String)session.getAttribute("merchantid");

		return sc.createOffer(name, type, value,des, merchantId);
	}
	@RequestMapping(value="createOffer")
	public String createOffer(ModelMap model){
		model.addAttribute("test", true);
		return "CreateOfferByMerchant";
	}
	@RequestMapping(value="addScheme")
	public String addCupon(ModelMap model,HttpSession session){
		String merchantId=(String)session.getAttribute("merchantid");
		model.addAttribute("list", sc.getAllProductByMarchantID(merchantId));	
		return "AddSchemeByMerchant";
	}
	@RequestMapping(value="removeScheme")
	public String removeCupon(ModelMap model,HttpSession session){
		String merchantId=(String)session.getAttribute("merchantid");
		model.addAttribute("list", sc.getAllProductByMarchantID(merchantId));

		return "RemoveSchemeByMerchant";
	}
	@RequestMapping(value="removeScheme",method=RequestMethod.POST)
	public String removeCupon(@RequestParam("selectedId")String[] values,@RequestParam(value="productId") String product_id,ModelMap model,HttpSession session){
		String merchantId=(String)session.getAttribute("merchantid");
		model.addAttribute("list", sc.getAllProductByMarchantID(merchantId));		
		model.addAttribute("result", sc.deleteScheme(values, product_id));	
		return "RemoveSchemeByMerchant";
	}
	@RequestMapping(value="addScheme",method=RequestMethod.POST)
	public String addCupon(@RequestParam(value="productId") String product_id,@RequestParam(value="schemeId") String scheme_id,@RequestParam(value="startDate") String start_date,@RequestParam(value="endDate") String end_date,ModelMap model,HttpSession session){
		String merchantId=(String)session.getAttribute("merchantid");
		model.addAttribute("result",sc.addScheme(scheme_id, product_id, start_date, end_date));
		model.addAttribute("list", sc.getAllProductByMarchantID(merchantId));	
		return "AddSchemeByMerchant";
	}
	@RequestMapping("getAllSchemeByMerchantIdAndProductId")  
	public @ResponseBody  
	List<String> getAllSchemeByMerchantIdAndProductId(@RequestParam(value="productId") String product_id,HttpSession session) {  
		String merchantId=(String)session.getAttribute("merchantid");

		return sc.getAllSchemeByMerchantIdAndProductId(merchantId,product_id) ;	 
	} 
	@RequestMapping("getAllSchemeByProductID")  
	public @ResponseBody  
	List<String> getAllSchemeByProductID(@RequestParam(value="productId") String product_id) {  
		return sc.getAllSchemeByProductID(product_id) ;	 
	} 
	@RequestMapping("getAllProductIdForMerchantID")  
	public @ResponseBody  
	List<String> getAllProductIdForMerchantID(HttpSession session) {  
		String merchantId=(String)session.getAttribute("merchantid");
		return sc.getAllProductDetailsByMarchantID(merchantId);
	} 
	@RequestMapping(value="discountsbyMerchant",method=RequestMethod.GET)
	public String showDiscountsByMerchant(){

		return "DiscountByMerchant";
	}
	@RequestMapping(value="removeOfferByMerchant")
	public String removeOfferByMerchant(ModelMap model,HttpSession session) {
		String merchantId=(String)session.getAttribute("merchantid");
		model.addAttribute("list", sc.getAllOfferByMerchant(merchantId));
		return "removeOfferByMerchant";
	}
	@RequestMapping(value="removeOfferByMerchant",method=RequestMethod.POST)
	public String removeOfferBymerchant(ModelMap model,HttpSession session,@RequestParam("selectedId")String[] values) {
		String merchantId=(String)session.getAttribute("merchantid");
		
		model.addAttribute("result", sc.deleteOfferByMerchant(values,merchantId));
		model.addAttribute("list", sc.getAllOfferByMerchant(merchantId));
		return "removeOfferByMerchant";
	}
	
	
}
