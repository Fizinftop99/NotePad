package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FormView {
    private GridPane gp = new GridPane();

    Label name = new Label("Name");
    Label position = new Label("Desired Position");
    Label salary = new Label("Desired Annual Salary");

    TextField nameTF = new TextField();
    TextField positionTF = new TextField();
    TextField salaryTF = new TextField();

    Button reset = new Button();
    Button cancel = new Button();
    Button save = new Button();

    HBox buttonBar = new HBox();

    public void configureView() {
        gp.add(name, 0, 0);
        gp.add(nameTF, 1, 0);
        gp.add(position, 2, 0);
        gp.add(salary, 0, 2);



    }

}
