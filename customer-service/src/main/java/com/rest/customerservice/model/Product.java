package com.rest.customerservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="product")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
//	@JsonProperty("id")
	@ApiModelProperty(value="Id of the Product (primary key field)")
	private String id;
	
	@Column(name = "name")
//	@JsonProperty("name")
	@ApiModelProperty(value="Name of the Product")
	private String name;

	public Product() {
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
}
