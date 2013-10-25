package com.cg.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.*;
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
import com.cg.domain.Media;
import com.cg.domain.Merchant;
import com.cg.domain.Order;
import com.cg.domain.Product;
import com.cg.domain.ProductDescription;
import com.cg.domain.Shipping;
import com.cg.domain.Transaction;
import com.cg.domain.User;
import com.cg.domain.Wishlist;
import com.cg.domain.ReduceProduct;
import com.cg.domain.Check;

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
	public IDaoScheme idascheme;
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
		System.out.println(email_id);
		u=idaouser.findById(email_id);
		System.out.println(u);
		if(u!=null){
			s=u.getUserId();
			System.out.println(s);
		}else if(u==null){


			m=idaomerchant.findById(email_id);
			if(m!=null){
				s=m.getMerchantId();
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
			System.out.println(s);
			if (s2.equals(pass)&&s1.equalsIgnoreCase("active")) {
				String uid=idaouser.getUserId(email_id);
				String userName=idaouser.getUserName(email_id);
				HttpSession ses=req.getSession();
				ses.setAttribute("userid",uid);
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
				ses.setAttribute("userid",mid);
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
			System.out.println(request.getSession(false));
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


