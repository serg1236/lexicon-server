package com.lexicon.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="customer")
@NamedQueries({
	@NamedQuery(query="Select user from User user Where user.fbId != :excludedUser", name="User.getAllExceptOne")
})
public class User {
	
	@GeneratedValue
	@Id
	private int id;
	
	@Column(unique=true, name="fbLogin")
	private String fbId;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Image> images;

	private String firstName;
	private String lastName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFbId() {
		return fbId;
	}
	public void setFbId(String fbId) {
		this.fbId = fbId;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fbId=" + fbId + ", images=" + images
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	public void addImage(String imageUrl, String publicId){
		Image image = new Image();
		image.setAuthor(this);
		image.setUrl(imageUrl);
		image.setPublicId(publicId);
		if(getImages()==null){
			setImages(new ArrayList<Image>());
		}
		images.add(image);
		//PersistenceUtils.save(image);
	}
	
	

}
