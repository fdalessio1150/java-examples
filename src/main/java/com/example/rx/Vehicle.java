package com.example.rx;

import java.util.List;

public class Vehicle {
	
	private String name;
	private String color;
	private Double value;
	private List<String> items;
	
	private Vehicle(Builder builder) {
		this.name = builder.name;
		this.color = builder.color;
		this.value = builder.value;
		this.items = builder.items;		
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public Integer getItemsSize() {
		return items.size();
	}
	
	public void discount() {
		this.value = value - (value * 0.05);
	}
		
	public static class Builder {
		
		private String name;
		private String color;
		private Double value;
		private List<String> items;
		
		public Builder() {}
		
		public Builder withName(String name) {
            this.name = name;
            return this;
        }
		
		public Builder withColor(String color) {
            this.color = color;
            return this;
        }
		
		public Builder withValue(Double value) {
            this.value = value;
            return this;
        }
		
		public Builder withItems(List<String> items) {
			this.items = items;
			return this;
		}
		public Vehicle build() {
			return new Vehicle(this);
		}
		
	}
		
}
