package cybersoft.java10.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String address;
	private String fullname;
	private String phone;
	private String role;
	private int roleId;
	private String roleName;
	
	

	public User() {
		super();
	}

	public User(int id, String username, String password, String email, String address, String fullname, String phone,
			int roleId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.fullname = fullname;
		this.phone = phone;
		this.roleId = roleId;
	}

	public User(String username, String password, String email, String address, String fullname, String phone,
			int roleId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.fullname = fullname;
		this.phone = phone;
		this.roleId = roleId;
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
