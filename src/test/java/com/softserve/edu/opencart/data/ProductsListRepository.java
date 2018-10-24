package com.softserve.edu.opencart.data;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Andrii Koliadiuk
 *
 */
public final class ProductsListRepository {
	private ProductsListRepository() {	
	}
	
	public static List<Product> getEmptyList() {
		return new ArrayList<>();
	}
	
	/**
	 * 
	 * @param sortCriteria - define if method returns sorted Products list
	 * @return ProductsList instance with filled List 
	 * 		   of products for "mac" search request
	 */
	public static ProductsList getMacList(SortCriterias sortCriteria) {
		ProductsList productsList = new ProductsList()
				.addProduct(ProductRepository.iMac())
				.addProduct(ProductRepository.macBook())
				.addProduct(ProductRepository.macBookAir())
				.addProduct(ProductRepository.macBookPro());
		productsList.sort(sortCriteria.getComparator());
		return productsList;
	}
	public static ProductsList getHPList(SortCriterias sortCriteria) {
		ProductsList productsList = new ProductsList()
				.addProduct(ProductRepository.hp_lp3065())
				.addProduct(ProductRepository.hp_15_ay168sa())
				.addProduct(ProductRepository.hp_250_g5())
				.addProduct(ProductRepository.hp_laserjet_pro())
				.addProduct(ProductRepository.hp_pavilion_x360())
				.addProduct(ProductRepository.hp_sprocket())
				.addProduct(ProductRepository.hp_x3000_wireless_mouse());
		 productsList.sort(sortCriteria.getComparator());
		 return productsList;
	}
	
	public static List<String> getMacNamesList(SortCriterias sortCriteria) {
		ProductsList productsList = getMacList(sortCriteria);
		List<String> names = new ArrayList<>();
		for (Product product : productsList.getProducts()) {
			names.add(product.getName());
		}
		return names;
	}
	public static List<String> getHPNamesList(SortCriterias sortCriteria) {
		ProductsList productsList = getHPList(sortCriteria);
		List<String> names = new ArrayList<>();
		for (Product product : productsList.getProducts()) {
			names.add(product.getName());
		}
		return names;
	}
	
	
}
