package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="Users")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "username")
	private String username;
	
	private String password;
	
//	@Column(name = "isAdmin")
//	private boolean isAdmin;
	
    @NotNull
    @Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	public User()
	{
	}

	public User(String username, String password, Role role)
	{
		this.username = username;
		this.password = password;
		this.role = role;
		//this.isAdmin = isAdmin;
	}
		
	public Integer getUserId()
	{
		return userId;
	}
	
	public void setUserId(Integer user_id)
	{
		this.userId = user_id;
	}

	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
//	public boolean getIsAdmin()
//	{
//		return this.isAdmin;
//	}
//	
//	public void setIsAdmin(boolean isAdmin)
//	{
//		this.isAdmin = isAdmin;
//	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
