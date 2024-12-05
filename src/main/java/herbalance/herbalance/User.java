package herbalance.herbalance;

public class User {
    private String userEmail;
    private String password;
    private String firstName;

    private Survey survey;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String useremail) {
        this.userEmail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}


}
