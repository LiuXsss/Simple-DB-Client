import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Users {
    private StringProperty firstname;
    private  StringProperty lastname;


    public Users(String firstname, String lastname){
        this.firstname = new SimpleStringProperty(this, "firstname");
        this.lastname = new SimpleStringProperty(this, "lastname");
        this.setFirstname(firstname);
        this.setLastname(lastname);
    }
    public String getFirstname() {
        return firstname.get();
    }


    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }



    public static ObservableList<Users> getUsers(){
        ObservableList<Users> ret_val = FXCollections.observableArrayList();

        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=1234qwer&serverTimezone=UTC";

        Connection conn = null;

        Controller controller = new Controller();

        try {
            conn = DriverManager.getConnection(conn_url);
            ResultSet rs = null;

            String statement = controller.input;
            PreparedStatement stmt = conn.prepareStatement(statement);
            rs = stmt.executeQuery();

            while(rs!=null && rs.next()){
                String firstname = rs.getString(1);
                String lastname = rs.getString(2);
                ret_val.add(new Users(firstname, lastname));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret_val;
    }

}
