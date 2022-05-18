package vn.hangdinh.bohoshop.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private Role role;

    public User() {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public User(int id, String username, String password, String fullName, String phone, String address, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = Role.parseRole(role);
    }

    public static User parseUser(String row){
        User user = new User();
        String[] userInformation = row.split(",");
        user.id = Integer.parseInt(userInformation[0]);
        user.username = userInformation[1];
        user.password = userInformation[2];
        user.fullName = userInformation[3];
        user.phone = userInformation[4];
        user.address = userInformation[5];
        user.email = userInformation[6];
        user.role = Role.parseRole(userInformation[7]);

        return user;
    }

    public User(Integer id, String username, String password, String fullName, String phone, String address, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return  id+
                ","+username+
                ","+password+
                ","+fullName+
                ","+phone+
                ","+address+
                ","+email+
                ","+role;
    }
}
