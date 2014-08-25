package DataAccessObject;

public class Product {
	private String name;
	private String company;
	private String category;
	
	public Product(String name, String company, String category){
		this.name = name;
		this.company = company;
		this.category = category;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCompany(){
		return company;
	}
	
	public String getCategory(){
		return category;
	}
}
