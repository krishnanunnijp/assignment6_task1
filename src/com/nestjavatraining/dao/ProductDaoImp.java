package com.nestjavatraining.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.nestjavatraining.connectionpool.ConnectionPool;
import com.nestjavatraining.entity.Product;

public class ProductDaoImp implements ProductDao {

	@Override
	public void saveProduct(Product product) {
		Connection connection=null;
		String insertSQL="INSERT INTO `product`( `productcode`, `productname`, `productdescription`, `activationdate`, `expirtydate`) VALUES (?,?,?,?,?)"; 
		 try{
			 DataSource ds=ConnectionPool.getDataSource();
			 connection = ds.getConnection();
			 PreparedStatement statement=connection.prepareStatement(insertSQL);
			 statement.setString(1,product.getProductCode());
			 statement.setString(2,product.getProductName());
			 statement.setString(3,product.getProductDescription());
			 statement.setDate(4,new java.sql.Date(product.getActivationDate().getTime()));
			 statement.setDate(5,new java.sql.Date(product.getExpiryDate().getTime()));  
			 int resultSet=statement.executeUpdate(); 
			  connection.close();
		  }catch(SQLException e){
			  e.printStackTrace();
			  }
	}

	@Override
	public List<Product> listAllProducts() {
		List<Product> productList=new ArrayList<Product>();
		Connection connection=null;
		String selectSQL="Select * from product";
		PreparedStatement prepStmt=null;
		 try{
			 DataSource ds=ConnectionPool.getDataSource();
			 connection=ds.getConnection();
			 prepStmt=connection.prepareStatement(selectSQL);
			 ResultSet resultSet=prepStmt.executeQuery();
			 while(resultSet.next()){
				  productList.add(new Product(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6)));
				  }
			  connection.close();
		  }catch(SQLException e){
			  e.printStackTrace();
			  }
		  return productList;

	}

	@Override
	public Product getProduct(String productCode) {
		List<Product> productList=new ArrayList<Product>();
		Connection connection=null;
		String selectSQL="Select * from product WHERE `productcode`=?";
		PreparedStatement prepStmt=null;
		 try{
			 DataSource ds=ConnectionPool.getDataSource();
			 connection=ds.getConnection();
			 prepStmt=connection.prepareStatement(selectSQL);  
			 prepStmt.setString(1,productCode); 
			 ResultSet resultSet=prepStmt.executeQuery();
			 while(resultSet.next()){
				  productList.add(new Product(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5),resultSet.getDate(6)));
				  }
			  connection.close();
		  }catch(SQLException e){
			  e.printStackTrace();
			  }
		  return productList.get(0);
	}

	@Override
	public void deleteProduct(String productCode) {
		// TODO Auto-generated method stub
		String insertSQL="DELETE FROM `product` WHERE `productcode`=?"; 
		 try{
			 DataSource ds=ConnectionPool.getDataSource();
			 Connection connection = ds.getConnection();
			 PreparedStatement statement=connection.prepareStatement(insertSQL);  
			 statement.setString(1,productCode); 
			 int resultSet=statement.executeUpdate(); 
			  connection.close();
		  }catch(SQLException e){
			  e.printStackTrace();
			  }
		
	}



}
