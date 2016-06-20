package dto;

public class Product {

	private int  id;
	private String pName;
    private double pUnitPrice;
    private double pStockQty;
    private String pImportDate;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public double getpUnitPrice() {
		return pUnitPrice;
	}

	public void setpUnitPrice(double pUnitPrice) {
		this.pUnitPrice = pUnitPrice;
	}

	public double getpStockQty() {
		return pStockQty;
	}

	public void setpStockQty(double d) {
		this.pStockQty = d;
	}

	public String getpImportDate() {
		return pImportDate;
	}

	public void setpImportDate(String string) {
		this.pImportDate = string;
	}
	
	public String toString(){
		return id + "\t" + pName + "\t" + pUnitPrice + "\t" + pStockQty + "\t" + pImportDate;
	}
}
