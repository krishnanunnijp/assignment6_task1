package com.nestjavatraining.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.nestjavatraining.entity.Product;
import com.nestjavatraining.service.ProductService;
import com.nestjavatraining.service.ProductServiceImpl;

public class ProductUtility {
	private static ProductService productService = new ProductServiceImpl();
	public static void main(String[] args) {
		
		char ch;
		Scanner scanner = new Scanner(System.in);
		do { 
			System.out.println("1.Save Product, 2.Delete Product, 3.List All Products, 4.Search Product");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				saveProduct();
				break;
			case 2:
				deleteProduct();
				break;
			case 3:
				listAllProducts();
				break;
			case 4:
				searchProduct();
				break;
			default:
				System.out.println("Invalid Choice");
				 }
			System.out.println("Do you want to continue (y/n)");
			ch = scanner.next().charAt(0);
			} while (ch == 'y');
		scanner.close();
	}
	
	private static void searchProduct() {
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter product code:");
		String productCode=scanner.next();
		Product product=productService.getProduct(productCode);
		 System.out.println("Product Code " + " " + "Product Name"+ " " +"Product Description" + " "+ "Activation Date");
		 System.out.println(product.getProductCode() + " " + product.getProductName()+ " " +product.getProductDescription() + " "+ product.getActivationDate());
		
		 }
	 
	private static void listAllProducts() {
		 List<Product> productList = productService.listAllProducts();
		 System.out.println("Product Code " + " " + "Product Name"+ " " +"Product Description" + " "+ "Activation Date");
		 for(Product product : productList)
			 System.out.println(product.getProductCode() + " " + product.getProductName()+ " " +product.getProductDescription() + " "+ product.getActivationDate());
		  }
	
	private static void deleteProduct() {
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter product code:");
		String productCode=scanner.next();
		productService.deleteProduct(productCode);;
		
		 }
	
	private static void saveProduct() {
		Date date1 = null,date2 = null;
		SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter product code:");
		String productCode=scanner.next();
		System.out.println("Enter product name:");
		String productName=scanner.next();
		System.out.println("Enter product description:");
		String productDescription=scanner.next();
		System.out.println("Enter product activation date:");
		String activationDate=scanner.next();
		System.out.println("Enter product expiry date:");
		String expiryDate=scanner.next();
		try {
		  date1 = dateInput.parse(activationDate);
		  date2 = dateInput.parse(expiryDate);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		Product product= new Product(productCode, productName, productDescription,date1,
				date2);
		System.out.println(product);
		productService.saveProduct(product);
		System.out.println("ok");
		 }
		
}
