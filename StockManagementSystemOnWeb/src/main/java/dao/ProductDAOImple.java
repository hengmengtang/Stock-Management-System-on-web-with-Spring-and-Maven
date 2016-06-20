package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import connection.dbConnection;
import dto.Product;
import validation.CheckInput;

public class ProductDAOImple implements ProductInterface {
	Scanner in=new Scanner(System.in);
	LocalDate date=LocalDate.now();
	//Pagination page=new Pagination();
	List<Product> productList;
	//get connection from class dbConnection();
	Connection con=dbConnection.getInstance().getConnection();
	CallableStatement cs;
	ResultSet rs;
	CheckInput input=new CheckInput();
	PreparedStatement pre;
	
	public ProductDAOImple() {
		productList=new ArrayList<>();
	}
	
	@Override
	public boolean insertProduct(Product product) {
		try {
			/*String storedProc="{call insertProduct(?,?,?,?,?)}";
			cs=con.prepareCall(storedProc);*/
			
			String sql="insert into tbstock values(?,?,?,?,?)";
			pre=con.prepareStatement(sql);
						
			System.out.println("ID: "+ (getProduct(product).get(0).getId()+1));
			product.setId((getProduct(product).get(0).getId()+1));
			System.out.print("Product name: ");		
			product.setpName(CheckInput.isCharacter(in.nextLine()));
			System.out.print("Stock Qty: ");
			product.setpStockQty(Double.parseDouble(CheckInput.isNumber(in.nextLine())));
			System.out.print("Unit Price: ");
			product.setpUnitPrice(Double.parseDouble(CheckInput.isNumber(in.nextLine())));
			product.setpImportDate("" + date);
			
			pre.setInt(1, product.getId());
			pre.setString(2, product.getpName());
			pre.setDouble(3, product.getpStockQty());
			pre.setDouble(4, product.getpUnitPrice());
			pre.setString(5, product.getpImportDate());
			
			if(CheckInput.Confirmation("Do you want to insert this product to Stock")==true){
				int result=pre.executeUpdate();
				if(result>0)
					return true;
			}else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(pre!=null){
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteProduct(Product product) {
		try {
			String storedProc="{call deleteproducts(?)}";
			cs=con.prepareCall(storedProc);
			
			System.out.println("Product ID: ");
			product.setId(Integer.parseInt(CheckInput.isNumber(in.nextLine())));

			cs.setInt(1,product.getId());
			if(CheckInput.Confirmation("Do you want to insert this product to Stock")==true){
			int result=cs.executeUpdate();
			if(result>0)
				return true;
			}
			System.out.println("Product number: " + product.getId() + " is deleted!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cs!=null){
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean updateProductById(Product product) {
		System.out.print("Search product id that you want to update.");
		if (searchProductById(product).getpName() != null) {
			System.out.print("Product name: ");
			product.setpName(CheckInput.isCharacter(in.nextLine()));
			System.out.print("Stock Qty: ");
			product.setpStockQty(Double.parseDouble(CheckInput.isNumber(in.nextLine())));
			System.out.print("Unit Price: ");
			product.setpUnitPrice(Double.parseDouble(CheckInput.isNumber(in.nextLine())));
			product.setpImportDate("" + date);

			if (CheckInput.Confirmation("Do you want to update this product to Stock") == true) {
				try {
					String storedProc = "{call updateproductbyid(?,?,?,?,?)}";
					cs = con.prepareCall(storedProc);

					cs.setInt(1, product.getId());
					cs.setString(2, product.getpName());
					cs.setDouble(3, product.getpStockQty());
					cs.setDouble(4, product.getpUnitPrice());
					cs.setString(5, product.getpImportDate());

					int result = cs.executeUpdate();
					if (result > 0)
						return true;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (cs != null) {
						try {
							cs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				return true;
			}
			return false;
		} else{
			System.err.println("No product found!");
			return false;
		}
	}
	
	@Override
	public boolean updateProductByName(Product product) {
		try {
			String storedProc="{call updateproductbyname(?,?,?,?,?)}";
			cs=con.prepareCall(storedProc);
			
			cs.setInt(1,product.getId());
			cs.setString(2, product.getpName());
			cs.setDouble(3, product.getpStockQty());
			cs.setDouble(4, product.getpUnitPrice());
			cs.setString(5, product.getpImportDate());
			
			int result=cs.executeUpdate();
			if(result>0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cs!=null){
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	@Override
	public Product searchProductById(Product product) {
		try {
			String storedProc="{call searchproductbyid(?)}";
			cs=con.prepareCall(storedProc);
			
			System.out.print("ID: ");
			
			product.setId(Integer.parseInt(CheckInput.isNumber(in.nextLine())));
			
			cs.setInt(1,product.getId());
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				product.setId(rs.getInt("pid"));
				product.setpName(rs.getString("pname"));
				product.setpStockQty(rs.getDouble("pqty"));
				product.setpUnitPrice(rs.getDouble("punitprice"));
				product.setpImportDate(rs.getString("importdate"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cs!=null){
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return product;
	}
	
	@Override
	public List<Product> searchProductByName(Product product) {
		try {
			String storedProc="{call searchproductbyname(?)}";
			cs=con.prepareCall(storedProc);
			
			System.out.println("Product name: ");
			product.setpName(CheckInput.isCharacter(in.nextLine()));
			
			cs.setString(1,product.getpName());
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				product=new Product();
				product.setId(rs.getInt("pid"));
				product.setpName(rs.getString("pname"));
				product.setpStockQty(rs.getDouble("pqty"));
				product.setpUnitPrice(rs.getDouble("punitprice"));
				product.setpImportDate(rs.getString("importdate"));
				productList.add(product);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cs!=null){
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return productList;
	}
	
	@Override
	public List<Product> getProduct(Product product) {
		productList.removeAll(productList);
		try {
			String storedProc="{call getallproducts()}";
			cs=con.prepareCall(storedProc);
			
			rs=cs.executeQuery();
			
			while(rs.next()){
				product=new Product();
				product.setId(rs.getInt("pid"));
				product.setpName(rs.getString("pname"));
				product.setpStockQty(rs.getDouble("pqty"));
				product.setpUnitPrice(rs.getDouble("punitprice"));
				product.setpImportDate(rs.getString("importdate"));
				productList.add(product);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cs!=null){
				try {
					cs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return productList;
	}
	
	/*public List<Product> search(Product product){
		String sql="select searchproductbyname(?) limit 5 offset 0";
		try {
			pre=con.prepareStatement(sql);
			System.out.println("Product name: ");
			product.setpName(CheckInput.isCharacter(in.nextLine()));
			pre.setString(1,product.getpName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		try {
			rs=pre.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("pid"));
				System.out.println(rs.getString("proname"));
				System.out.println(rs.getDouble("proqty"));
				System.out.println(rs.getDouble("punitprice"));
				System.out.println(rs.getString("importdate"));
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}*/
}
