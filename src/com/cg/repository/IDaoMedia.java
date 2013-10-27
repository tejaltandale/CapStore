package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.domain.AdvertisementMedia;
import com.cg.domain.Media;
import com.cg.domain.MediaPath;
import com.cg.domain.Product;
@Repository
public interface IDaoMedia extends JpaRepository<Media,Long>{
	@Transactional
	@Query("select new com.cg.domain.AdvertisementMedia(a.advertisementId,m.mediaId,m.mediaPath,m.mediaType,a.advertisementStartdate,a.advertisementEnddate) from Advertisement a,Media m where a.media.mediaId=m.mediaId")
	public List<AdvertisementMedia> findDetails();
	@Transactional
	@Query("select distinct u from Product u  group by u.productBrand having u.productTag=?1   ")
	public List<Product> sortMeByproduct_brand(String br);
	@Transactional
	@Query("select m.mediaPath from Media m where m.product.productId=?1")
	public String mediaReturn(String productId);
	@Transactional
	@Query("select m.mediaId from Media m where m.product.productId=?1")
	public Long mediaReturnId(String productId);
	@Transactional
	@Query("select m.mediaPath from Media m where m.product.productId=?1")
	public List<Media> mediaReturn1(String productId);
	@Transactional
	@Query("select  m from Media m")
	List<Media> getInitialBrands();
	@Transactional
	@Query("select new com.cg.domain.MediaPath(m.mediaPath) from Wishlist w ,Media m where w.productId=m.product.productId")
	public List<MediaPath> getByproductId();

}
