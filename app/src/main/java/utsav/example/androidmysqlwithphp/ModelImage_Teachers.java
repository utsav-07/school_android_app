package utsav.example.androidmysqlwithphp;

public class ModelImage_Teachers {

    private String username,email,imageurl;

    public ModelImage_Teachers() {
    }

    public ModelImage_Teachers(String username, String email, String imageurl) {
        this.username = username;
        this.email = email;
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
