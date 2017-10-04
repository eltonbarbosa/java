package br.com.inatel.jersey;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloEntity {
	
	public HelloEntity() {
		// TODO Auto-generated constructor stub
	}
	
	private Long id;
	private String name;
	
	public HelloEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
