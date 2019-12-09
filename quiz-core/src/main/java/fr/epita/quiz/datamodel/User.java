package fr.epita.quiz.datamodel;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="Users")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "username")
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "isAdmin")
	private boolean isAdmin;
	
	public User()
	{
	}

	public User(String username, String password, boolean isAdmin)
	{
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
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
	
	public boolean getIsAdmin()
	{
		return this.isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
}
