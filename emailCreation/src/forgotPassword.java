import java.sql.*;
import java.util.Scanner;

public class forgotPassword {

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String email = "";
        String username = "user1";
        String password = "Abdulrahman@015";
        String dataURL = "jdbc:mysql://localhost:3306/test";

        Connection connection = DriverManager.getConnection(dataURL, username, password);
        Statement statement = connection.createStatement();
        String all ="select * from emails";
        ResultSet resultSet = statement.executeQuery(all);

        System.out.println("Please enter your email address to retrieve the password.");
        email = scan.next();

        while(resultSet.next()){
            String check = resultSet.getString("Email_address");
            if(check.equalsIgnoreCase(email)){
                System.out.println("Your password is " + resultSet.getString("ppassword"));
                System.exit(0);
            }
        }

        System.out.println("This email does not exist.");
    }
}
