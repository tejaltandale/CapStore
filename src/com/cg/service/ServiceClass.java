package com.cg.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.cg.domain.Advertisement;
import com.cg.domain.AdvertisementMedia;
import com.cg.domain.Category;
import com.cg.domain.CategoryCount;
import com.cg.domain.Count;
import com.cg.domain.Feedback;
import com.cg.domain.MarketShare;
import com.cg.domain.MarketShareRevenue;
import com.cg.domain.Media;
import com.cg.domain.MediaPath;
import com.cg.domain.Merchant;
import com.cg.domain.MerchantRatings;
import com.cg.domain.MostBoughtProducts;
import com.cg.domain.Offer;
import com.cg.domain.Order;
import com.cg.domain.Product;
import com.cg.domain.ProductDesc;
import com.cg.domain.ProductDescription;
import com.cg.domain.ProductMedia;
import com.cg.domain.ProductMediaPath;
import com.cg.domain.ProductRatings;
import com.cg.domain.ProductShare;
import com.cg.domain.ProductViews;
import com.cg.domain.ProductWish;
import com.cg.domain.RevenueTable;
import com.cg.domain.Scheme;
import com.cg.domain.SchemeOffer;
import com.cg.domain.Shipping;
import com.cg.domain.Transaction;
import com.cg.domain.User;
import com.cg.domain.Viewcount;
import com.cg.domain.Wishlist;
import com.cg.domain.ReduceProduct;
import com.cg.domain.Check;
import com.cg.domain.WishlistCount;

import com.cg.repository.IDaoCategory;
import com.cg.repository.IDaoFeedback;
import com.cg.repository.IDaoMedia;
import com.cg.repository.IDaoMerchant;
import com.cg.repository.IDaoOffer;
import com.cg.repository.IDaoProductDescription;
import com.cg.repository.IDaoReturneditem;
import com.cg.repository.IDaoReturnstatus;
import com.cg.repository.IDaoReward;
import com.cg.repository.IDaoScheme;
import com.cg.repository.IDaoTransaction;
import com.cg.repository.IDaoUser;
import com.cg.repository.IDaoAdvertisement;
import com.cg.repository.IDaoOrder;
import com.cg.repository.IDaoProduct;
import com.cg.repository.IDaoShipping;
import com.cg.repository.IDaoViewcount;
import com.cg.repository.IDaoWishlist;
import com.cg.util.Status;

@Service
public class ServiceClass {
	@Autowired
	public IDaoAdvertisement idaoadvertisement;
	@Autowired
	public IDaoCategory idaocategory;
	@Autowired
	public IDaoFeedback idaofeedback;
	@Autowired
	public IDaoMedia idaomedia;
	@Autowired
	public IDaoMerchant idaomerchant;
	@Autowired
	public IDaoOffer idaooffer;
	@Autowired
	public IDaoOrder idaoorder;
	@Autowired
	public IDaoProduct idaoproduct;
	@Autowired
	public IDaoProductDescription idaoproductdescription;
	@Autowired
	public IDaoReturneditem idaoreturneditem;
	@Autowired
	public IDaoReturnstatus idaoreturnstatus;
	@Autowired
	public IDaoReward idaoreward;
	@Autowired
	public IDaoScheme idaoscheme;
	@Autowired
	public IDaoShipping idaoshipping;
	@Autowired
	public IDaoTransaction idaotransaction;
	@Autowired
	public IDaoUser idaouser;
	@Autowired
	public IDaoViewcount idaoviewcount;
	@Autowired
	public IDaoWishlist idaowishlist;

	final String ALGO = "AES";
	final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };	
	//Dinesh's module starts
	@Transactional
	public boolean addUser(String userid, String fname, String lname,String pwd, String email, String sec_ques, String ans, String add) {
		User user = new User();
		Date d=new Date();
		boolean canBeAdded = false;
		boolean valid = check(userid, email);
		if (valid) {
			user.setUserId(userid);
			user.setUserFirstname(fname);
			user.setUserLastname(lname);
			String pass = new String();
			try {
				pass = encrypt(pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			user.setUserPassword(pass);
			user.setUserEmail(email);
			user.setUserSecurityquestion(sec_ques);
			user.setUserSecurityanswer(ans);
			user.setUserAddress(add);
			user.setUserAddeddate(d);
			user.setUserStatus("Inactive");
			idaouser.saveAndFlush(user);
			user_mail(email,userid);
			canBeAdded = true;
		}
		return canBeAdded;
	}
	@Transactional
	public String encrypt(String Data) throws Exception {

		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}
	@Transactional
	public String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	@Transactional
	private Key generateKey() {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
	@Transactional
	public boolean check(String id,String email){
		boolean valid=true;
		if(id.matches("[C][_][0-9A-Za-z]+")){
			List<User> userList=new ArrayList<User>();
			userList=idaouser.findAll();
			Iterator<User> itr=userList.iterator();
			while(itr.hasNext()){
				User user=new User();
				user=(User)itr.next();
				if(id.equals(user.getUserId()) || email.equals(user.getUserEmail())){
					valid=false;

					break;
				}
			}
		}else{
			List<Merchant> merchantList=new ArrayList<Merchant>();
			merchantList=idaomerchant.findAll();
			Iterator<Merchant> itr=merchantList.iterator();
			while(itr.hasNext()){
				Merchant merchant=new Merchant();
				merchant=(Merchant)itr.next();
				if(id.equals(merchant.getMerchantId()) || email.equals(merchant.getMerchantEmail())){
					valid=false;
					break;
				}
			}
		}
		return valid;
	}
	@Transactional
	public boolean addMerchant(String mer_id,String mer_name,String mer_pwd,String mer_email,String mer_type,String mer_sec_ques,String mer_ans,String mer_add){
		Merchant merchant=new Merchant();
		boolean canBeAdded = false;
		Date d=new Date();

		boolean valid = check(mer_id,mer_email);
		if (valid) {
			merchant.setMerchantId(mer_id);
			merchant.setMerchantName(mer_name);
			merchant.setMerchantType(mer_type);
			String pass = new String();
			try {
				pass = encrypt(mer_pwd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			merchant.setMerchantPassword(pass);
			merchant.setMerchantEmail(mer_email);
			merchant.setMerchantSecurityquestion(mer_sec_ques);
			merchant.setMerchantSecurityanswer(mer_ans);
			merchant.setMerchantAddress(mer_add);
			merchant.setMerchantAddeddate(d);
			merchant.setMerchantStatus("Inactive");
			idaomerchant.saveAndFlush(merchant);
			canBeAdded = true;
		}
		return canBeAdded;
	}
	@Transactional
	public boolean activateUser(String userid,Long ts){
		boolean activated=false;
		Calendar cal=Calendar.getInstance();
		Long ts1=cal.getTimeInMillis();
		Long time=(long) (24*60*60*1000);
		if((ts1-ts)<=time){
			List<User> userList=new ArrayList<User>();
			userList=idaouser.findAll();
			Iterator<User> itr=userList.iterator();
			while(itr.hasNext()){
				User user=new User();
				user=(User)itr.next();
				if(userid.equals(user.getUserId())){
					user.setUserStatus("Active");
					idaouser.saveAndFlush(user);
					activated=true;
					break;
				}
			}
		}
		return activated;
	}
	@Transactional
	public boolean activateMerchant(String mer_id,Long ts){
		boolean activated=false;
		Calendar cal=Calendar.getInstance();
		Long ts1=cal.getTimeInMillis();
		Long time=(long) (24*60*60*1000);
		if((ts1-ts)<=time){
			List<Merchant> merchantList=new ArrayList<Merchant>();
			merchantList=idaomerchant.findAll();
			Iterator<Merchant> itr=merchantList.iterator();
			while(itr.hasNext()){
				Merchant merchant=new Merchant();
				merchant=(Merchant)itr.next();
				if(mer_id.equals(merchant.getMerchantId())){
					merchant.setMerchantStatus("Active");
					idaomerchant.saveAndFlush(merchant);
					activated=true;
					break;
				}
			}
		}
		return activated;
	}
	@Transactional
	public void user_mail(String email,String userid){

		Calendar cal=Calendar.getInstance();
		long stamp=cal.getTimeInMillis();
		String host="nsiore01.capgemini.com";
		String from="Capstore@capgemini.com";
		String password=" ";
		Properties props=new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		Session session = Session.getInstance(props, new PasswordAuthenticator(from, password));
		try{
			MimeMessage message = new MimeMessage(session);
			Address fromAddress = new InternetAddress(from);
			Address toAddress = new InternetAddress(email);
			message.setFrom(fromAddress);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject("CapStore Account Activation");
			message.setContent("<html><body><form><a href='http://localhost:1729/Integration/FetchUser?userid="+userid+"&stamp="+stamp+"'>Click to activate your Capstore account</a></form></body></html>","text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			message.saveChanges();
			Transport.send(message);
			transport.close();
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	//Siddhi's module starts
	@Transactional
	public boolean changePwdService(String userid,String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		boolean changed=false;
		List<User> userList=new ArrayList<User>();
		userList=idaouser.findAll();

		String password= new String();
		try {
			password = encrypt(oldPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<User> itr=userList.iterator();
		while(itr.hasNext()){
			User user=new User();
			user=(User)itr.next();
			if(userid.equals(user.getUserId()) && password.equals(user.getUserPassword())){
				String encPassword=new String();
				try {
					encPassword = encrypt(newPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				user.setUserPassword(encPassword);
				idaouser.saveAndFlush(user);
				changed=true;

				break;
			}
		}
		return changed;
	}
	@Transactional
	public boolean changePwdServiceForMerchant(String merchantid,String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		boolean changed=false;
		List<Merchant> merchantList=new ArrayList<Merchant>();
		merchantList=idaomerchant.findAll();
		String password= new String();
		try {
			password = encrypt(oldPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Merchant> itr=merchantList.iterator();
		while(itr.hasNext()){
			Merchant merchant=new Merchant();
			merchant=(Merchant)itr.next();
			if(merchantid.equals(merchant.getMerchantId()) && password.equals(merchant.getMerchantPassword())){
				String encPassword=new String();
				try {
					encPassword = encrypt(newPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				merchant.setMerchantPassword(encPassword);
				idaomerchant.saveAndFlush(merchant);
				changed=true;
				break;
			}
		}
		return changed;
	}

	@Transactional
	public boolean forgotPwd(String userid, String secQuestion, String secAnswer){
		// TODO Auto-generated method stub

		User u=new User();
		u=idaouser.findOne(userid);
		if(u.getUserSecurityanswer().equals(secAnswer)){
			String pass = null;
			try {
				pass = decrypt(u.getUserPassword());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String receiver=u.getUserEmail();
			String subject="Password recovery from CapStore";
			boolean isSend=emailSender(receiver, subject, pass);
			return true;
		}
		return false;
	}

	@Transactional
	public User getDetails(String userid) {
		return idaouser.findOne(userid);
	}

	@Transactional
	public boolean forgotPwdForMerchant(String userid, String secQuestion, String secAnswer){
		// TODO Auto-generated method stub
		Merchant m=new Merchant();
		m=idaomerchant.findOne(userid);
		if(m.getMerchantSecurityanswer().equals(secAnswer)){
			String pass = null;
			try {
				pass = decrypt(m.getMerchantPassword());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String receiver=m.getMerchantEmail();
			String subject="password recovery";
			boolean isSend=emailSender(receiver, subject, pass);
			return true;
		}
		return false;
	}
	@Transactional
	public Merchant getDetailsM(String userid) {
		return idaomerchant.findOne(userid);
	}
	public boolean emailSender(String toaddress, String subject, String content) {
		String host="nsiore01.capgemini.com";
		final String from="Capstore@capgemini.com";
		final String password=" ";

		Properties propertyobj=new Properties();
		propertyobj.put("mail.smtp.starttls.enable", "true");
		propertyobj.put("mail.smtp.host", host);
		propertyobj.put("mail.smtp.user", from);
		propertyobj.put("mail.smtp.password", password);
		propertyobj.put("mail.smtp.port", "25");
		propertyobj.put("mail.smtp.auth", "true");
		propertyobj.put("mail.debug", "true");
		Session session=Session.getDefaultInstance(propertyobj,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,password);
			}
		});
		try	{
			MimeMessage mimemessageobj = new MimeMessage(session);

			mimemessageobj.setFrom(new InternetAddress(from));
			mimemessageobj.setRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));
			mimemessageobj.setSubject(subject);
			mimemessageobj.setText(content);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			mimemessageobj.saveChanges();
			Transport.send(mimemessageobj);
			transport.close();
		}	
		catch(Exception exceptionobj)	{
			System.out.println(exceptionobj);
			return false;
		}
		return true;
	}
	//Monalisa's module starts

	private User u;
	private Merchant m;
	@Transactional
	public boolean validateLogin(HttpServletRequest req,String email_id, String user_password) {
		String s=null;
		String merchantname=null;

		u=idaouser.findById(email_id);

		if(u!=null){
			s=u.getUserId();
		}else if(u==null){
			m=idaomerchant.findById(email_id);
			System.out.println(m);
			if(m!=null){
				s=m.getMerchantId();
				merchantname=m.getMerchantName();
			}
			else{
				return false;
			}
		}		
		String pass=new String();
		try {
			pass = encrypt(user_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		char[] a;
		a = s.toCharArray();
		if (a[0] == 'C' && a[1] == '_') {
			String s2 = idaouser.getUserPassword(email_id);
			String s1=idaouser.getUserStatus(email_id);
			if (s2.equals(pass)&&s1.equalsIgnoreCase("active")) {
				String uid=idaouser.getUserId(email_id);
				String userName=idaouser.getUserName(email_id);
				HttpSession ses=req.getSession();
				ses.setAttribute("merchantid",uid);
				ses.setAttribute("userName",userName);
				return true;
			} else {
				return false;
			}
		} else if (a[0] == 'M' && a[1] == '_') {
			String s2 = idaomerchant.getMerchantPassword(email_id);
			String s1=idaomerchant.getMerchantStatus(email_id);
			if (pass.equals(s2)&&s1.equalsIgnoreCase("active")) {
				String mid=idaomerchant.getMerchantId(email_id);
				HttpSession ses=req.getSession();
				ses.setAttribute("merchantid",mid);
				ses.setAttribute("merchantname",merchantname);
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}
	@Transactional
	public boolean logoutUser(HttpServletRequest request) {

		HttpSession ses = request.getSession(false);
		if(ses!=null){
			ses.invalidate();
			return true;
		}
		return false;
	}	
	/*Bismay's module*/
	@Transactional
	public List<Merchant> getAllMerchants() {
		return idaomerchant.findAll();
	}

	@Transactional
	public List<Merchant> getPendingMerchants() {
		List<Merchant> allMer = idaomerchant.findAll();
		Properties pro = Status.getMerchantStatus();
		List<Merchant> pendingMer = new ArrayList<Merchant>();
		for (Merchant m : allMer) {
			if (m.getMerchantStatus().equalsIgnoreCase(pro.getProperty("pending"))) {
				pendingMer.add(m);
			}
		}
		return pendingMer;
	}

	@Transactional
	public List<Merchant> getApprovedMerchants() {
		List<Merchant> allMer = idaomerchant.findAll();
		Properties pro = Status.getMerchantStatus();
		List<Merchant> approvedMer = new ArrayList<Merchant>();
		for (Merchant m : allMer) {
			if (m.getMerchantStatus().equalsIgnoreCase(pro.getProperty("approved"))) {
				approvedMer.add(m);
			}
		}
		return approvedMer;
	}

	@Transactional
	public void approveMerchant(String id) {
		List<Merchant> allMer = idaomerchant.findAll();
		Properties pro = Status.getMerchantStatus();
		Date curr_date = new Date();
		Merchant mtemp = new Merchant();
		for (Merchant m : allMer) {
			if (m.getMerchantId().equalsIgnoreCase(id)) {
				mtemp = m;
				break;
			}
		}
		mtemp.setMerchantStatus(pro.getProperty("approved"));
		mtemp.setMerchantAddeddate(curr_date);
		mtemp.setMerchantRemoveddate(null);
		idaomerchant.save(mtemp);
		sendEmailToMerchantAboutActivation(mtemp.getMerchantEmail());
	}

	@Transactional
	public void rejectMerchant(String id) {
		List<Merchant> allMer = idaomerchant.findAll();
		Properties pro = Status.getMerchantStatus();
		Date curr_date = new Date();
		Merchant mtemp = new Merchant();
		for (Merchant m : allMer) {
			if (m.getMerchantId().equalsIgnoreCase(id)) {
				mtemp = m;
				break;
			}
		}

		mtemp.setMerchantStatus(pro.getProperty("rejected"));
		mtemp.setMerchantRemoveddate(curr_date);
		idaomerchant.save(mtemp);
	}

	@Transactional
	public void removeMerchant(String id) {
		List<Merchant> allMer = idaomerchant.findAll();
		Properties pro = Status.getMerchantStatus();
		Date curr_date = new Date();
		Merchant mtemp = new Merchant();
		for (Merchant m : allMer) {
			if (m.getMerchantId().equalsIgnoreCase(id)) {
				mtemp = m;
				break;
			}
		}
		mtemp.setMerchantStatus(pro.getProperty("removed"));
		mtemp.setMerchantRemoveddate(curr_date);
		idaomerchant.save(mtemp);
	}

	@Transactional
	public List<Merchant> getOtherMerchants() {
		List<Merchant> allMer = idaomerchant.findAll();
		Properties pro = Status.getMerchantStatus();
		List<Merchant> otherMer = new ArrayList<Merchant>();
		for (Merchant m : allMer) {
			if (m.getMerchantStatus().equalsIgnoreCase(pro.getProperty("removed")) || m.getMerchantStatus().equalsIgnoreCase(pro.getProperty("rejected")) ) {
				otherMer.add(m);
			}
		}
		return otherMer;

	}
	@Transactional	
	public void sendEmailToMerchantAboutActivation(String mer_email){

		String host="nsiore01.capgemini.com";
		String from="Capstore@capgemini.com";
		String password=" ";
		Properties props=new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");	
		props.put("mail.debug", "true");
		Session session = Session.getInstance(props, new PasswordAuthenticator(from, password));
		try{
			MimeMessage message = new MimeMessage(session);
			Address fromAddress = new InternetAddress(from);
			Address toAddress = new InternetAddress(mer_email);
			message.setFrom(fromAddress);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject("CapStore Account Activation");
			message.setContent("<html><body><form>Congrats!!!Your account has been activated.<br><a href='http://localhost:1729/Integration/'>CapStore</a></form></body></html>","text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			message.saveChanges();
			Transport.send(message);
			transport.close();
		}catch(Exception ex){

			System.out.println(ex);
		}

	}
	/*Alok's Module starts*/
	@Transactional
	public List<ProductDescription> insertproductdescMerchant(MultipartFile datasize) throws FileNotFoundException{
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
		return pojoList;

	}
	@Transactional
	public List<Product> insertProduct(MultipartFile datasize,ModelMap map) throws IOException{
		List<Product> pojoList = new ArrayList<Product>();
		BufferedReader br = new BufferedReader(new FileReader("D:/Allfiles/"+datasize.getOriginalFilename()));
		String line = "";
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Date d=new Date();
		String mediapath=null;
		//ModelMap map=new ModelMap();
		List<ProductMediaPath> mediapathobjects=new ArrayList<ProductMediaPath>();
		try {
			while((line = br.readLine()) != null) {  
				Merchant m=new Merchant();

				Category cat=new Category();
				String[] fields = line.split(",");
				cat.setCategoryId(fields[1]);
				m.setMerchantId(fields[2]);		
				Product p = new Product(fields[0],cat,m,fields[3],Double.parseDouble(fields[4]),d,fields[5],fields[6],Long.parseLong(fields[7]));
				mediapath=null;
				ProductMediaPath productmediapath=new ProductMediaPath(p,mediapath);
				mediapathobjects.add(productmediapath);
				map.put("productmedias",mediapathobjects);
				pojoList.add(p);
			}
		} 
		catch (IOException e) {
			br.close();

			e.printStackTrace();
		}
		return pojoList;

	}
	@Transactional
	public void increaseProduct(String id, int quan) {
		Product p=idaoproduct.findOne(id);
		long amt=p.getProductStock()+quan;
		p.setProductStock(amt);
		idaoproduct.save(p);
	}
	@Transactional
	public List<Product> getAllProduct(){

		return idaoproduct.findAll();
	}
	@Transactional
	public void deleteProduct(String id,int quan){
		Product p=idaoproduct.findOne(id);
		long amt=p.getProductStock()-quan;
		p.setProductStock(amt);
		idaoproduct.save(p);
	}
	@Transactional
	public void addProduct(List<Product> list){

		for(Product p:list){
			idaoproduct.saveAndFlush(p);
		}
	}
	@Transactional
	public void addProductDesc(List<ProductDescription> pojoList) {

		List<ProductDescription> databaselist=idaoproductdescription.findAll();
		List<ProductDescription>finallist=new ArrayList<ProductDescription>();
		for(ProductDescription p:pojoList){
			for(ProductDescription p1:databaselist){
				if(p.getProduct().getProductId().equals(p1.getProduct().getProductId()) && p.getAttributeName().equals(p1.getAttributeName() )){
					idaoproductdescription.delete(p1); 	   
				}
			}
		}
		for(ProductDescription p2:pojoList){
			idaoproductdescription.saveAndFlush(p2);
		}
	}
	@Transactional
	public List<Transaction> getOrder(String id){
		List<Transaction> list=idaoorder.getBymerchantId(id);
		return list;
	}

	@Transactional
	public void deleteProduct1(List<Transaction>list ){
		List<ReduceProduct> reduce=new ArrayList<ReduceProduct>();
		for(Transaction p:list){
			ReduceProduct rep = new ReduceProduct();
			rep.setProduct_id(p.getProduct().getProductId());
			rep.setReduceQuantity(p.getProductQuantity());
			reduce.add(rep);
		}
		for(ReduceProduct r:reduce){
			Product p=idaoproduct.findOne(r.getProduct_id());
			if(p.getProductId()==r.getProduct_id()){
				p.setProductStock(p.getProductStock()-r.getReduceQuantity());
			}
			idaoproduct.save(p);
		}

	}
	@Transactional
	public List<Product> getAllProductofMerchant(String id) {

		return idaoproduct.getById(id);
	}
	/*Dinesh Reddy's module*/

	@Transactional
	public boolean createOffer(String name,String type,String value,String des,String mar_id) {

		Offer of = new Offer();
		of.setSchemeName(name);
		of.setType(type);
		of.setValue(value);
		of.setSchemeDescription(des);
		of.setMerchant(idaooffer.getByMerchantId(mar_id));		
		boolean b = idaooffer.saveAndFlush(of) != null;
		return b;

	}
	@Transactional
	public boolean deleteScheme(String[] scheme_id,String product_id) {
		Calendar c=Calendar.getInstance();
		boolean is_deleted = false;
		Date d1=c.getTime();
		for (Scheme sc : idaoscheme.findAll()) {
			Date d = new Date(sc.getEndDate().getTime());

			if (d.compareTo(d1)<0) {

				idaoscheme.delete(sc);

			}
		}

		for (int i = 0; i < scheme_id.length; i++) {
			long temp = Long.parseLong(scheme_id[i]);
			if (idaooffer.getByentryId(idaoproduct.findOne(product_id), idaooffer.findOne(temp)) != null) {
				is_deleted = true;
			} else {
				is_deleted = false;
			}

			idaoscheme.delete(idaooffer.getByentryId(idaoproduct.findOne(product_id), idaooffer.findOne(temp)));

		}
		return is_deleted;

	}
	@Transactional
	public boolean addScheme(String scheme_id,String product_id,String start_date,String end_date) {
		Scheme sc = new Scheme();
		long scheme = Long.parseLong(scheme_id);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date start = null,end = null;
		try {

			start = format.parse(start_date);
			end = format.parse(end_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		java.sql.Date start_date_sql = new java.sql.Date(start.getTime());
		java.sql.Date end_date_sql = new java.sql.Date(end.getTime());
		sc.setStartDate(start_date_sql);
		sc.setEndDate(end_date_sql);
		sc.setOffer(idaooffer.getBySchemeId(scheme));
		sc.setProduct(idaoproduct.findOne(product_id));

		boolean b = idaoscheme.saveAndFlush(sc) != null;
		return b;
	}
	@Transactional
	public List<Product> getAllProductByMarchantID(String mid) {

		return idaoproduct.getByproductId(idaomerchant.findOne(mid));
	} 
	@Transactional
	public List<String> getAllSchemeByProductID(String product_id) {
		List<Offer> list1 = new ArrayList<Offer>() ;
		Calendar c=Calendar.getInstance();
		Date d1=c.getTime();
		for (Scheme s : idaooffer.getByentryId(idaoproduct.findOne(product_id))) {		
			Date d = new Date(s.getEndDate().getTime());				
			if (d.compareTo(d1)>0) {					
				list1.add(s.getOffer());					
			}			
		}
		List<String> list = new ArrayList<String>();
		JSONObject obj = new JSONObject();
		for (Offer offer : list1) {
			try {				
				obj.put("schemeId", offer.getSchemeId());
				obj.put("schemeDescription", offer.getSchemeDescription());
				String str = obj.toString();
				list.add(str);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
	@Transactional
	public List<String> getAllSchemeByMerchantIdAndProductId(String mid,String product_id) {  
		List<Offer> list = idaooffer.getBySchemeId(idaooffer.getByMerchantId(mid));
		List<String> list2 = new ArrayList<String>();
		List<Offer> list1 = new ArrayList<Offer>() ;
		for (Scheme scheme : idaooffer.getByentryId(idaoproduct.findOne(product_id))) {
			list1.add(scheme.getOffer());			
		}
		list.removeAll(list1);	
		JSONObject obj = new JSONObject();
		for (Offer offer : list) {
			try {				
				obj.put("schemeId", offer.getSchemeId());
				obj.put("schemeDescription", offer.getSchemeDescription());
				String str = obj.toString();
				list2.add(str);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list2;
	}
	@Transactional
	public List<String> getAllProductDetailsByMarchantID(String mid) {
		List<String> list_string = new ArrayList<String>();
		JSONObject obj = new JSONObject();
		for (Product product : idaoproduct.getByproductId(idaomerchant.findOne(mid))) {
			try {				
				obj.put("productId", product.getProductId());
				obj.put("productName", product.getProductName());
				String str = obj.toString();
				list_string.add(str);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list_string;
	} 


	@Transactional
	public List<Offer> getAllOfferByAdmin() {

		// TODO Auto-generated method stub
		return idaooffer.getBySchemeId("cart");
	}
	@Transactional
	public boolean deleteOfferByAdmin(String[] scheme_id) {
		boolean is_deleted = false ;
		for (int i = 0; i < scheme_id.length; i++) {
			long temp = Long.parseLong(scheme_id[i]);

			if (idaooffer.findOne(temp) != null) {
				is_deleted = true;
			} else {
				is_deleted = false;
			}
			idaooffer.delete(idaooffer.findOne(temp));
		}
		return is_deleted;
		// TODO Auto-generated method stub

	}
	@Transactional
	public List<Offer> getAllOfferByMerchant(String merchant_id) {
		// TODO Auto-generated method stub
		return idaooffer.getBySchemeId(idaooffer.getByMerchantId(merchant_id));
	}
	@Transactional
	public boolean deleteOfferByMerchant(String[] scheme_id,String merchant_id) {
		boolean is_deleted = false ;
		for (int i = 0; i < scheme_id.length; i++) {
			long temp = Long.parseLong(scheme_id[i]);
			for (Product product : idaooffer.getProductId(idaomerchant.findOne(merchant_id))) {
				Scheme sc = idaooffer.getByentryId(product, idaooffer.findOne(temp));
				if (sc != null) {
					idaoscheme.delete(sc);
				}
			}			
			if (idaooffer.findOne(temp) != null) {
				is_deleted = true;
			} else {
				is_deleted = false;
			}
			idaooffer.delete(idaooffer.findOne(temp));
		}		
		return is_deleted;
	} 



	/*Sushma's module*/
	/*
	private static final int BUFFER_SIZE = 4096;

	public void unzip(String zipFilePath) throws IOException {
		String destDirectory="C:/p1";
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();


		while (entry != null) {
			String filePath = destDirectory + File.separator + entry.getName();
			String fstring=null;

			if (!entry.isDirectory()) {

				if(filePath.contains(".jpg")){
					int i=filePath.indexOf(".");
					fstring=filePath.substring(0,i);
					Long time=new java.util.Date().getTime();
					fstring=fstring+time+".jpg";
				}


				extractFile(zipIn, fstring);
			} else {

				File dir = new File(filePath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	public void extracteFiles(String url,String spath,Product product){

		String fpath=null;
		String[] sarr=spath.split("/");
		for (String string : sarr) {
			if(string.contains(".zip")){
				int i=string.indexOf(".");
				fpath=string.substring(0,i);
			}
		}
		String dpath="C://p1//"+fpath;
		File folder = new File(dpath);
		if (!folder.exists()) {
			folder.mkdir(); 
		}

		File[] listOfFiles = folder.listFiles();
		Long time=new java.util.Date().getTime();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				String FileName=url+"/"+fpath+"/"+listOfFiles[i].getName();
				Media m_arg=new Media();
				m_arg.setMediaPath(FileName);
				m_arg.setMediaType("images");
				m_arg.setProduct(product);
				idaomedia.saveAndFlush(m_arg);
			} else if (listOfFiles[i].isDirectory()) {

			}
		}
	}
	public String getImages() {
		List<Media> mlist=idaomedia.findAll();
		Media m=mlist.get(0);
		return m.getMediaPath();
	}*/
	/*Deepika's module*/
	List<AdvertisementMedia> adList;
	List<AdvertisementMedia> adList1;


	@Transactional
	public List<AdvertisementMedia>getAdDetails(){
		adList= idaomedia.findDetails();
		adList1=Validation(adList);

		return adList1;

	}

	public List<AdvertisementMedia> Validation(List<AdvertisementMedia> l){
		List<AdvertisementMedia> newList=new ArrayList<>();
		java.util.Date today = new java.util.Date();
		java.sql.Date sqlToday = new java.sql.Date(today.getTime());

		for(AdvertisementMedia am: l){
			if((sqlToday.compareTo(am.getAdvertisementEnddate())<0)&&(sqlToday.compareTo(am.getAdvertisementStartdate())>0)){

				newList.add(am);
			}
		}
		return newList;

	}
	/*Shaswat*/
	@Transactional
	public List<ProductViews> researchViewcount() {
		List<Viewcount> view = idaoviewcount.getAllViewcount();
		List<ProductViews> pvlist = new ArrayList<ProductViews>();
		List<String> prodidlist = new ArrayList<String>();
		for (Viewcount v : view) {
			prodidlist.add(v.getProductId());
		}
		for (Viewcount v : view) {
			ProductViews pv = new ProductViews();
			pv.setProductId(v.getProductId());
			pv.setViews(v.getCount());
			pv.setProductName(v.getProduct().getProductName());
			pvlist.add(pv);
		}
		if (pvlist.size() >= 5) {
			Collections.sort(pvlist);
			return pvlist.subList(0, 5);
		} else {
			return pvlist;
		}
	}

	@Transactional
	public List<WishlistCount> researchWishlist() {
		List<Wishlist> wish = idaowishlist.getAllWishlist();
		List<WishlistCount> wishc = new ArrayList<WishlistCount>();
		List<String> wishprod = new ArrayList<String>();
		for (Wishlist w : wish) {
			wishprod.add(w.getProduct().getProductId());
		}
		String[] productarray = new String[100];

		int i = 0;
		boolean flag = false;
		for (Wishlist w : wish) {
			flag = false;
			int occurrences = Collections.frequency(wishprod, w.getProduct()
					.getProductId());
			for (int t = 0; t < i; t++) {
				if (productarray[i] == productarray[t]) {
					flag = true;
				}
			}
			if (!flag) {
				WishlistCount wishco = new WishlistCount();
				wishco.setCount(occurrences);
				wishco.setProductId(w.getProduct().getProductId());
				wishco.setProductName(w.getProduct().getProductName());
				wishc.add(wishco);
			}

		}
		if (wishc.size() < 5) {
			return wishc;
		}
		return wishc.subList(0, 5);
	}

	@Transactional
	public List<MostBoughtProducts> researchTransactions() {
		List<Transaction> trans = idaotransaction.getAllTransactions();
		List<String> transprod = new ArrayList<String>();
		List<MostBoughtProducts> transpro = new ArrayList<MostBoughtProducts>();

		for (Transaction w : trans) {
			transprod.add(w.getProduct().getProductName());
		}
		List<MostBoughtProducts> result = new ArrayList<MostBoughtProducts>();
		String[] productarray = new String[100];
		int[] productoccurence = new int[100];
		int i = 0;
		boolean flag = false;
		for (Transaction w : trans) {
			flag = false;
			int occurrences = Collections.frequency(transprod, w.getProduct()
					.getProductName());

			productarray[i] = w.getProduct().getProductName();

			for (int t = 0; t < i; t++) {
				if (productarray[i] == productarray[t]) {
					flag = true;
				}
			}
			if (!flag) {
				MostBoughtProducts br = new MostBoughtProducts();
				br.setProductname(productarray[i]);
				productoccurence[i] = occurrences;
				br.setNumberofsales(productoccurence[i]);
				transpro.add(br);
			}

			i++;
		}
		Collections.sort(transpro);
		if (transpro.size() < 5) {
			return transpro;
		}
		result = transpro.subList(0, 5);
		return result;

	}

	@Transactional
	public List<MarketShare> researchMarketShare() {
		List<Transaction> trans = idaotransaction.getAllTransactions();
		List<String> merchan = new ArrayList<String>();
		List<MarketShare> mrkshr = new ArrayList<MarketShare>();

		for (Transaction w : trans) {
			merchan.add(w.getMerchant().getMerchantId());
		}
		String[] merchantarray = new String[100];
		int[] productoccurence = new int[100];
		int i = 0;
		boolean flag = false;
		for (Transaction w : trans) {
			flag = false;
			int occurrences = Collections.frequency(merchan, w.getMerchant()
					.getMerchantId());

			merchantarray[i] = w.getProduct().getProductName();

			for (int t = 0; t < i; t++) {
				if (merchantarray[i] == merchantarray[t]) {
					flag = true;
				}
			}
			if (!flag) {

				MarketShare mr = new MarketShare();
				mr.setMerchantname(w.getMerchant().getMerchantName());
				productoccurence[i] = occurrences;

				mr.setNumberoftransactions(occurrences);
				mr.setMerchantid(w.getProduct().getProductId());
				mrkshr.add(mr);
			}

			i++;
		}
		return mrkshr;
	}

	@Transactional
	public List<MarketShareRevenue> researchMarketShareRevenue() {
		List<Transaction> trans = idaotransaction.getAllTransactions();
		List<MarketShareRevenue> merchan = new ArrayList<MarketShareRevenue>();
		List<MarketShareRevenue> merchan2 = new ArrayList<MarketShareRevenue>();
		double merchantRevenue = 0;

		for (Transaction w : trans) {
			MarketShareRevenue msr = new MarketShareRevenue();
			msr.setMerchantid(w.getMerchant().getMerchantId());
			msr.setMerchantname(w.getMerchant().getMerchantName());
			merchan.add(msr);
		}
		for (MarketShareRevenue m : merchan) {
			merchantRevenue = 0;

			for (Transaction w : trans) {
				if (m.getMerchantid().equalsIgnoreCase(
						w.getMerchant().getMerchantId())) {
					merchantRevenue = merchantRevenue + w.getProductCost();
				}
			}

			m.setRevenue(merchantRevenue);
			merchan2.add(m);
		}
		return merchan2;
	}

	@Transactional
	public List<Integer> researchOrderStatus() {
		int i = 0, j = 0;
		List<Integer> result = new ArrayList<Integer>();
		List<Order> orders = idaoorder.getAllOrders();
		for (Order ord : orders) {
			if (ord.getOrderStatus().equalsIgnoreCase("DELIVERED")) {
				i++;
			}
			if (ord.getOrderStatus().equalsIgnoreCase("UNDELIVERED")) {
				j++;
			}
			result.add(0, i);
			result.add(1, j);
		}
		return result;
	}

	@Transactional
	public List<MerchantRatings> researchMerchant() {

		double temprating = 0;
		double den = 0;
		List<Merchant> merchan = idaomerchant.getAllMerchants();
		List<MerchantRatings> mrch = new ArrayList<MerchantRatings>();

		List<Feedback> feedbacks = idaofeedback.getAllFeedback();
		List<MerchantRatings> merchants = new ArrayList<MerchantRatings>();
		for (Merchant mer : merchan) {
			MerchantRatings mrr = new MerchantRatings();
			mrr.setMerchantid(mer.getMerchantId());
			mrr.setMerchantName(mer.getMerchantName());
			merchants.add(mrr);
		}
		for (MerchantRatings m : merchants) {
			temprating = 0;
			den = 0;
			for (Feedback f : feedbacks) {
				if (m.getMerchantid().equalsIgnoreCase(f.getId())) {
					den++;
					temprating = temprating + f.getRating();

				}
			}
			MerchantRatings mrr = new MerchantRatings();
			mrr.setMerchantid(m.getMerchantid());
			mrr.setMerchantName(m.getMerchantName());
			if (den == 0) {
				den = 1;
			}

			mrr.setRating(temprating / den);

			mrch.add(mrr);

		}
		if (mrch.size() < 5) {
			return mrch;
		}
		return mrch.subList(0, 5);
	}

	@Transactional
	public List<ProductRatings> researchProduct() {
		double temprating = 0;
		double den = 0;
		List<Product> products = idaoproduct.getAllProducts();
		List<ProductRatings> prod = new ArrayList<ProductRatings>();
		List<Feedback> feedbacks = idaofeedback.getAllFeedback();

		List<ProductRatings> prdcts = new ArrayList<ProductRatings>();
		for (Product pr : products) {
			ProductRatings prrr = new ProductRatings();
			prrr.setProductId(pr.getProductId());
			prrr.setProductName(pr.getProductName());
			prrr.setCategory(pr.getCategory().getCategoryName());
			prdcts.add(prrr);

		}

		for (ProductRatings m : prdcts) {
			temprating = 0;
			den = 0;
			for (Feedback f : feedbacks) {

				if (m.getProductId().equalsIgnoreCase(f.getId())) {
					den++;
					temprating = temprating + f.getRating();
				}
			}
			ProductRatings prr = new ProductRatings();
			prr.setProductName(m.getProductName());
			prr.setProductId(m.getProductId());
			prr.setCategory(m.getCategory());
			if (den == 0) {
				den = 1;
			}

			prr.setRating(temprating / den);

			prod.add(prr);

		}
		if (prod.size() < 5) {

			return prod;
		}
		return prod.subList(0, 5);
	}

	@Transactional
	public List<ProductShare> researchProductShare() {
		List<Transaction> trans = idaotransaction.getAllTransactions();
		List<String> produc = new ArrayList<String>();
		List<ProductShare> prdshr = new ArrayList<ProductShare>();

		for (Transaction w : trans) {
			produc.add(w.getProduct().getProductId());
		}
		String[] merchantarray = new String[100];
		int[] productoccurence = new int[100];
		int i = 0;
		boolean flag = false;
		for (Transaction w : trans) {
			flag = false;
			int occurrences = Collections.frequency(produc, w.getProduct()
					.getProductId());

			merchantarray[i] = w.getProduct().getProductId();

			for (int t = 0; t < i; t++) {
				if (merchantarray[i] == merchantarray[t]) {
					flag = true;
				}
			}
			if (!flag) {
				ProductShare pr = new ProductShare();
				// result.add(j++, productarray[i]);
				pr.setProductid(w.getProduct().getProductId());
				pr.setProductName(w.getProductName());
				productoccurence[i] = occurrences;
				// result.add(j++, productoccurence[i]);
				pr.setNumberoftransactions(productoccurence[i]);

				prdshr.add(pr);
			}

			i++;
		}
		return prdshr;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<RevenueTable> researchQuarterRevenue() {
		List<Transaction> trans = idaotransaction.getAllTransactions();
		List<RevenueTable> rtable = new ArrayList<RevenueTable>();

		double firstquartertemprevenue = 0;
		double secondquartertemprevenue = 0;
		double thirdquartertemprevenue = 0;
		double fourthquartertemprevenue = 0;

		for (Transaction t : trans) {

			if (t.getTransactionDate().getYear() == 113) {

				if (t.getTransactionDate().getMonth() == 0
						|| t.getTransactionDate().getMonth() == 1
						|| t.getTransactionDate().getMonth() == 2) {
					firstquartertemprevenue = firstquartertemprevenue
							+ t.getProductCost();
				}
				if (t.getTransactionDate().getMonth() == 3
						|| t.getTransactionDate().getMonth() == 5
						|| t.getTransactionDate().getMonth() == 4) {
					secondquartertemprevenue = secondquartertemprevenue
							+ t.getProductCost();
				}
				if (t.getTransactionDate().getMonth() == 6
						|| t.getTransactionDate().getMonth() == 7
						|| t.getTransactionDate().getMonth() == 8) {
					thirdquartertemprevenue = thirdquartertemprevenue
							+ t.getProductCost();
				}
				if (t.getTransactionDate().getMonth() == 9
						|| t.getTransactionDate().getMonth() == 10
						|| t.getTransactionDate().getMonth() == 11) {
					fourthquartertemprevenue = fourthquartertemprevenue
							+ t.getProductCost();
				}
			}
		}
		RevenueTable rt = new RevenueTable();
		rt.setFirstrevenue(firstquartertemprevenue);
		rt.setSecondrevenue(secondquartertemprevenue);
		rt.setThirdrevenue(thirdquartertemprevenue);
		rt.setFourthrevenue(fourthquartertemprevenue);
		rtable.add(rt);
		return rtable;
	}

	@Transactional
	public List<CategoryCount> researchCategorySales() {
		int catcount = 0;

		List<CategoryCount> ctlist = new ArrayList<CategoryCount>();
		List<Transaction> trans = idaotransaction.getAllTransactions();
		List<Category> catlist = idaocategory.getAllCategories();
		List<String> catid = new ArrayList<String>();
		for (Category c : catlist) {
			catid.add(c.getCategoryId());
		}
		for (Category cat : catlist) {
			for (Transaction t : trans) {
				if (t.getProduct().getCategory().getCategoryId()
						.equalsIgnoreCase(cat.getCategoryId())) {
					catcount++;
				}

			}
			CategoryCount ct = new CategoryCount();
			ct.setCategoryId(cat.getCategoryId());
			ct.setCategoryname(cat.getCategoryName());
			ct.setCategorycount(catcount);
			ctlist.add(ct);

		}
		return ctlist;
	}

	/*THE FANTASTIC 6*/

	@Transactional
	public List<ProductMedia> getInitialProducts(String prod) {
		List<Media> li = idaomedia.findAll();
		List<ProductMedia> finallist = new ArrayList<ProductMedia>();
		for (Media m : li) {
			if (m.getProduct().getProductName().contains(prod)) {
				ProductMedia pm = new ProductMedia();
				pm.setM(m);
				String pid = m.getProduct().getProductId();
				Product p = idaoproduct.findOne(pid);
				pm.setP(p);
				finallist.add(pm);
			}
		}
		return finallist;
	}


	@Transactional
	public List<Product> getAllBrands(String br) {
		return idaoproduct.sortMeByproduct_brand();
	}

	@Transactional
	public List<ProductMedia> setSearchData() {
		List<Media> li = new ArrayList<Media>();
		li = idaomedia.findAll();
		List<ProductMedia> finallist = new ArrayList<ProductMedia>();

		for (Media m : li) {
			ProductMedia pm = new ProductMedia();
			pm.setM(m);
			String mid = m.getProduct().getProductId();
			Product p = idaoproduct.findOne(mid);
			pm.setP(p);
			finallist.add(pm);
		}
		return finallist;
	}

	@Transactional
	public List<ProductMedia> getAllProducts(String query) {
		List<Media> li = new ArrayList<Media>();
		li = idaomedia.findAll();
		List<ProductMedia> finallist = new ArrayList<ProductMedia>();
		for (Media m : li) {
			if (m.getProduct().getProductName().contains(query.toLowerCase().trim())) {
				ProductMedia pm = new ProductMedia();
				pm.setM(m);
				String mid = m.getProduct().getProductId();
				Product p = idaoproduct.findOne(mid);
				pm.setP(p);
				finallist.add(pm);
			}
		}
		return finallist;
	}

	@Transactional
	public List<ProductMedia> searchRelatedProduct(String brand) {
		List<Media> li = idaomedia.findAll();
		List<ProductMedia> finallist = new ArrayList<ProductMedia>();
		System.out.println("total size"+li.size());
		for (Media m : li) {
			if (brand.contains(m.getProduct().getProductBrand())) {
				ProductMedia pm = new ProductMedia();
				pm.setM(m);
				String pid = m.getProduct().getProductId();
				Product p = idaoproduct.findOne(pid);
				pm.setP(p);
				finallist.add(pm);
			}
		}
		return finallist;
	}

	@Transactional
	public List<Product> searchAllBrands() {
		List<Product> li = idaoproduct.sortMeByproduct_brand();
		return li;
	}

	@Transactional
	public List<ProductMedia> searchRelatedProductPriceWise(String brandCode,
			String minprice, String maxprice) {

		List<Media> li = new ArrayList<Media>();
		li = idaomedia.findAll();
		List<ProductMedia> finallist = new ArrayList<ProductMedia>();

		if (minprice == "" || minprice.equals("0")) {
			minprice = "100";
		}

		if (maxprice == "" || maxprice.equals("0")) {
			maxprice = "2000";
		}

		for (Media m : li) {
			if (brandCode.contains(m.getProduct().getProductBrand())
					&& m.getProduct().getProductCost() >= Float
					.parseFloat(minprice + "")
					&& m.getProduct().getProductCost() <= Float
					.parseFloat(maxprice + "")) {
				ProductMedia pm = new ProductMedia();
				pm.setM(m);
				String mid = m.getProduct().getProductId();
				Product p = idaoproduct.findOne(mid);
				pm.setP(p);
				finallist.add(pm);
			}
		}
		return finallist;
	}


	//********************************MURSID WORK**********************************************

	@Transactional
	public ProductDesc getDetail(String prod_id) {
		// TODO Auto-generated method stub
		ProductDesc desc=new ProductDesc();
		ProductDescription pd=idaoproductdescription.getByProductId(prod_id);

		desc.setProddesc(pd);

		Product pro=idaoproduct.findOne(prod_id);
		desc.setProduct(pro);

		return desc;
	}


	@Transactional
	public String getMediaPath(String prod_id) {
		// TODO Auto-generated method stub



		return idaomedia.mediaReturn(prod_id) ;
	}	




	//**************************VISWANATH INTEGRATION*********************************

	private	List<Product> list_product ;
	@Transactional
	public List<Product> getSuggestedProducts(String product_id){
		int i =0;


		Product product=idaoproduct.findOne(product_id);

		String categoryid=product.getCategory().getCategoryId();

		List<Product> list_product1 = new ArrayList<Product>();
		List<Viewcount> list_view = idaoviewcount.getByProductId(categoryid);


		for (Viewcount viewcount : list_view) {
			if (i==3) {
				break;
			}
			i++;
			list_product1.add(viewcount.getProduct());
		}
		list_product = list_product1;
		return list_product1;
	}

	/**************Pending work***************/
	private	List<Media> list_media ;
	@Transactional
	public List<Media> getMediaPath1(List<Product> list) {
		// TODO Auto-generated method stub
		List<Media> list_media = new ArrayList<Media>();
		for (Product product : list) {
			Media me=new Media();
			Long id=idaomedia.mediaReturnId(product.getProductId());
			me.setMediaId(id);
			String path=idaomedia.mediaReturn(product.getProductId());
			me.setMediaPath(path);
			list_media.add(me);


		}

		return list_media;

	}








	//***************************MRINAL INTEGRATION************************************


	@Transactional
	public boolean createFeedback(String userid,String id,String feedback_content,double rating){// REmoved USER_ID from fn




		Feedback fb=new Feedback();


		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal=Calendar.getInstance();

		String date=dateFormat.format(cal.getTime());

		String d[]= date.split("/");
		int year = Integer.parseInt(d[0])-1900;

		int month = Integer.parseInt(d[1])-1;
		int day = Integer.parseInt(d[2]);
		Date requiredForm =  new Date(year,month,day); 




		fb.setFeedbackContent(feedback_content);
		fb.setFeedbackDate(requiredForm);

		fb.setId(id);
		fb.setRating(rating);
		fb.setType("user");

		User u=new User();
		u.setUserId(userid);

		fb.setUser(u);
		idaofeedback.save(fb);

		return true;

	}

	@Transactional
	public  List<Feedback> getAll(String pid ){
		List<Feedback> fli= new ArrayList<Feedback>();
		fli=idaofeedback.findAll();

		List<Feedback> finalli=new ArrayList<Feedback>();
		for(Feedback f:fli){

			if(f.getId().contains(pid)){
				Feedback obj=new Feedback();
				obj.setFeedbackId(f.getFeedbackId());
				obj.setFeedbackContent(f.getFeedbackContent());
				obj.setFeedbackDate(f.getFeedbackDate());
				obj.setRating(f.getRating());
				obj.setId(f.getId()); 				
				obj.setUser(f.getUser());
				finalli.add(obj);

			}
		}

		return finalli;
	} 




	@Transactional
	public List<Feedback> delete(String userid,String pid,int feedid){


		List<Feedback> dli= new ArrayList<Feedback>();
		dli=getAll(pid);

		List<Feedback> userli=new ArrayList<Feedback>();
		for(Feedback f:dli){

			if(f.getUser().getUserId()!=null){
				if(f.getUser().getUserId().equals(userid)){


					Feedback obj=new Feedback();
					obj.setFeedbackId(f.getFeedbackId());
					obj.setFeedbackContent(f.getFeedbackContent());
					obj.setFeedbackDate(f.getFeedbackDate());
					obj.setRating(f.getRating());

					obj.setUser(f.getUser());
					userli.add(obj);

				}
			}
		}


		for(Feedback f2:userli){

			if(f2.getFeedbackId()==feedid){
				idaofeedback.delete(f2);
				break;

			}
		}

		return getAll(pid);

	}

	@Transactional
	public double average(String pid){
		double d=0.0;
		Count cnt=idaofeedback.countrate(pid);
		//Average avg=idaofeedback.getrate(pid); 
		if(cnt.getCountRating()>0){
			d=idaofeedback.getrate(pid);
		}
		return d;
	}



	//*****************************************DINESH INTEGRATION****************************
	@Transactional
	public List<SchemeOffer> getAllSchemeByProductIDInProduct(String product_id) {
		List<Offer> list1 = new ArrayList<Offer>() ;
		List<SchemeOffer> list_schemeoffer = new ArrayList<SchemeOffer>();
		Calendar c=Calendar.getInstance();

		Date d1=c.getTime();
		Product p = idaoproduct.findOne(product_id);


		for (Scheme s : idaoproduct.getByentryId(idaoproduct.findOne(product_id))) {

			Date d = new Date(s.getEndDate().getTime());
			SchemeOffer so = new SchemeOffer();
			if (d.compareTo(d1)>0) {
				String test =  s.getOffer().getType();
				String test1 = "buyandget";

				if (test.equalsIgnoreCase(test1)) {
					System.out.println("dinesh here");
					String buy_id = s.getProduct().getProductId();
					String get_id = s.getOffer().getSchemeName();
					Offer offer = s.getOffer();
					if (buy_id==get_id) {
						System.out.println("dinesh here 2");
						if (idaoproduct.findOne(get_id).getProductStock()>=(Long.parseLong(offer.getValue())+1)) {
							System.out.println(idaoproduct.findOne(get_id).getProductStock());
							System.out.println(Long.parseLong(offer.getValue())+1);
							so.setScheme(s);
							so.setOffer(s.getOffer());
							list_schemeoffer.add(so);
						}
					}else {
						if (idaoproduct.findOne(get_id).getProductStock()>=(Long.parseLong(offer.getValue()))) {
							System.out.println(idaoproduct.findOne(get_id).getProductStock());
							System.out.println(Long.parseLong(offer.getValue())+1);
							so.setScheme(s);
							so.setOffer(s.getOffer());
							list_schemeoffer.add(so);
						}
					}
				}else {

					System.out.println("dinesh here 3");
					so.setScheme(s);
					so.setOffer(s.getOffer());
					list_schemeoffer.add(so);
				}



			}

		}

		return list_schemeoffer;
	}



	/*==============================Ghanshyam service======================================*/
	@Transactional
	public List<ProductMedia> sortRelatedProductOnSelect(String countryCode) {
		List<ProductMedia> li1=new ArrayList<ProductMedia>();
		li1=searchRelatedProduct(countryCode);
		List<ProductMedia> fli1=new ArrayList<ProductMedia>();
		Collections.sort(li1,new MySalaryComp());
		for (ProductMedia p : li1) {
			fli1.add(p);
		}
		return fli1;

	}



	@Transactional
	public List<ProductMedia> sortRelatedProductOnSelectLowerTo(String countryCode) {
		List<ProductMedia> li1=new ArrayList<ProductMedia>();
		li1=searchRelatedProduct(countryCode);
		List<ProductMedia> fli1=new ArrayList<ProductMedia>();
		Collections.sort(li1,new MySalaryComp());
		Collections.reverse(li1);
		for (ProductMedia p : li1) {
			fli1.add(p);
		}
		System.out.println(fli1);
		return fli1;

	}

	@Transactional
	public boolean setOnGivenD(String productId,long vew_count,String cat_gory) {
		long i=1;
		List<Viewcount> li=new ArrayList<Viewcount>();
		li=idaoviewcount.findAll();
		Category c=idaocategory.findOne(cat_gory);
		Product p=idaoproduct.findOne(productId);
		Viewcount v=new Viewcount();
		if(li.size()!=0){
			for(Viewcount r:li){
				if(r.getProduct().getProductId().equals(productId)){
					v=r;
				}
			}
			if(v.getProduct()!=null && v.getCategory()!=null && v.getProduct().getProductId().equals(productId) && v.getCategory().getCategoryId().equals(cat_gory)){
				long i2=v.getCount();
				i=i+i2;
				v.setCount(i);
				idaoviewcount.updateRewardsByuserId(productId,i,cat_gory);
				return true;
			}
			else{
				Viewcount vnew=new Viewcount();
				vnew.setCategory(c);
				vnew.setProductId(p.getProductId());
				vnew.setCount(i);
				idaoviewcount.saveAndFlush(vnew);
				return true;			
			}

		}
		else{
			Viewcount vnew=new Viewcount();
			vnew.setCategory(c);
			vnew.setProductId(p.getProductId());
			vnew.setCount(i);
			idaoviewcount.saveAndFlush(vnew);
			return true;
		}
	}

	@Transactional
	public String getCatgoryOfgivenProduct(String prod_id) {
		return idaoproduct.findOne(prod_id).getCategory().getCategoryId();
	}

	@Transactional
	public List<ProductMedia> sortRelatedProductOnMostView(String countryCode) {
		List<String> p=new ArrayList<String>();
		List<Media> li=new ArrayList<Media>();
		li=idaomedia.findAll();
		p=idaoviewcount.getMaxPid();
		List<Media> li1=new ArrayList<Media>();
		for(Media m:li){
			for(String tp:p){			
				if(m.getProduct().getProductId().contains(tp)){
					Media obj=new Media();
					obj.setAdvertisements(m.getAdvertisements());
					obj.setMediaId(m.getMediaId());
					obj.setMediaPath(m.getMediaPath());
					obj.setProduct(m.getProduct());
					li1.add(obj);

				}
			}

		} 
		List<ProductMedia> finallist = new ArrayList<ProductMedia>();
		for (Media m : li1) {
			if (countryCode.contains(m.getProduct().getProductBrand())) {
				ProductMedia pm = new ProductMedia();
				pm.setM(m);
				String pid = m.getProduct().getProductId();
				Product p1 = idaoproduct.findOne(pid);
				pm.setP(p1);
				finallist.add(pm);
			}
		}
		return finallist;

		/*===============================ghanshyam service=======================================*/

	}




	/*============================ghanshyam raut comparator class=========================*/


	class MySalaryComp implements Comparator<ProductMedia>{
		@Override
		public int compare(ProductMedia e1, ProductMedia e2) {
			if(e1.getP().getProductCost() < e2.getP().getProductCost()){
				return 1;
			} 
			else {
				return -1;
			}
		}
	}
	@Transactional
	public boolean chechList(String userid, String productid){
		boolean c=true;
		for(Wishlist check:getAllWishList()){
			if((check.getProduct().getProductId().equals(productid)) && (check.getUser().getUserId().equals(userid))){
				 c=false;	
			}			
		}
		return c;
	}
	@Transactional	
	public boolean addToWishList(String userid, String productid) {
		boolean b=true;
		Date d=new Date(System.currentTimeMillis());
		Date d1=new Date(1970-01-01);
		Wishlist wish=new Wishlist();
		User user=idaouser.findOne(userid);
		if(user==null){
			b=false;
		}
		wish.setUser(user);
		Product product=idaoproduct.findOne(productid);
		if(product==null){
			b=false;
		}
		wish.setProduct(product);
		wish.setWishCreationdate(d);
		wish.setWishEnddate(d1);
		idaowishlist.saveAndFlush(wish);
		return b;
	}

	@Transactional
	public List<Product> getAllWishListPro() {
		Date d1=new Date(1970-01-01);	
		return idaoproduct.getByproductId(d1);
	}
	@Transactional
	public List<Wishlist> getAllWishList(){
	 return idaowishlist.findAll();
	}
	@Transactional
	public List<MediaPath> getImageList() {
		// TODO Auto-generated method stub
		return idaomedia.getByproductId();
	}
	@Transactional
	public int deleteFromWishList(String proid) {
		// TODO Auto-generated method stub
		int i=0;
		Date deld=new Date(System.currentTimeMillis());
		Wishlist wish1=new Wishlist();
		for(Wishlist wish:getAllWishList()){
			if(wish.getProduct().getProductId().equals(proid)){
				wish1=wish;	
				wish1.setWishEnddate(deld);
				idaowishlist.saveAndFlush(wish1);
				i=1;
			}
		}
		return i;
	}
}
class PasswordAuthenticator extends Authenticator {
	String user;
	String pw;
	public PasswordAuthenticator (String username, String password)
	{
		super();
		this.user = username;
		this.pw = password;
	}
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(user, pw);
	}
}


