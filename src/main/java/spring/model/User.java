package spring.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private int id;
	
	private String username;
	
	@NotBlank
	private String fullname;
	
	private String password;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String phone;
	
	private String avatar;
	
	private Role role;
	
	private int enabled;

	// đối tượng để join bảng lấy dữ liệu trong blog
	public User(int id, String username, String fullname, String avatar) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.avatar = avatar;
	}

}
