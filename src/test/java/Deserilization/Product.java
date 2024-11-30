package Deserilization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	
	
	private int id;
	private String title;
	private float price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Rating{
		private float rate;
		private int count;
	}
	

}
