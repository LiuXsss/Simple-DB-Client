import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    @FXML
    TableView<Users> tableView;
    static String input = "";

    public TextArea text_Area;

    public void ReadInput(){
        this.input = this.text_Area.getText();
        System.out.println(this.input);
    }

    public void load(){

        this.ReadInput();

        ObservableList<Users> values =
                Users.getUsers();

        TableColumn<Users,String> firstname
                = new TableColumn<Users, String>("Firstname");
        firstname.setCellValueFactory(new PropertyValueFactory("Firstname"));
        TableColumn<Users, String> lastname
                = new TableColumn<Users, String>("Lastname");
        lastname.setCellValueFactory(new PropertyValueFactory("Lastname"));

        tableView.getColumns().setAll(firstname, lastname);
        tableView.setItems(values);
    }

}
