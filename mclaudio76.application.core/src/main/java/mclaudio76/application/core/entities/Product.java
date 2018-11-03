package mclaudio76.application.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	public String productID;
	
	@Column(length=50)
	public String description;
	
	
}