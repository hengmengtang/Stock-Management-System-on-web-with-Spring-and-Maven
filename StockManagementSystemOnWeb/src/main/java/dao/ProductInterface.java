package dao;

import java.util.List;

import dto.Product;

//Data Access Object Pattern

public interface ProductInterface {
	//All interface for implementation in stock
	public boolean insertProduct(Product product);
	public List<Product> getProduct(Product product);
	public boolean deleteProduct(Product product);
	public boolean updateProductById(Product product);
	public boolean updateProductByName(Product product);
	public Product searchProductById(Product product);
	public List<Product> searchProductByName(Product product);
}
