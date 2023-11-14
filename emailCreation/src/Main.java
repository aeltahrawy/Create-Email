import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String email = "";
        String pass = "";
        String empty = "";

        String username = "user1";
        String password = "Abdulrahman@015";
        String dataURL = "jdbc:mysql://localhost:3306/test";

        Connection connection = DriverManager.getConnection(dataURL, username, password);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("Select Email_address from emails");

        System.out.println("Please enter your email");
        email = scan.next();

        while (true) {
            if (email.endsWith("@gmail.com") || email.endsWith("@hotmail.com")) {
                break;
            } else {
                System.out.println("Wrong email format. Please try again!");
                email = scan.next();
            }
        }

        empty = scan.nextLine();

        System.out.println("Please enter your password");
        pass = scan.next();

        while (true) {
            pass = scan.nextLine();

            if (pass.length() >= 8) {

                while (resultSet.next()) {
                    String check = resultSet.getString("Email_address");
                    if (check.equalsIgnoreCase(email)) {
                        System.out.println("This email is already in use");
                        System.exit(0);
                    }
                }

                String mysql = "insert into emails (Email_address, ppassword)" + "values (?, ?)";


                PreparedStatement preparedStatement = connection.prepareStatement(mysql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, pass);

                System.out.println("The email has been added.");

                preparedStatement.execute();
                break;
            } else {
                System.out.println("Your password should be minimum of 8 characters.");
            }
        }
    }
}

