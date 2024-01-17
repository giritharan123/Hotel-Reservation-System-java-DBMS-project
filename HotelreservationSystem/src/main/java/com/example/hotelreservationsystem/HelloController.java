package com.example.hotelreservationsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.io.IOException;
import java.util.EventObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.*;


public class HelloController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotel";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Giritharan@123";
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    PreparedStatement pst;

    @FXML
    private Label Welcometext;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;


    @FXML
    private DatePicker CheckinTextField;

    @FXML
    private DatePicker CheckoutTextField;




    @FXML
    private TextField RoomtypeTextField;

    @FXML
    private TextField TotalguestTextField;

    @FXML
    private Button checkButton;

    @FXML
    private ImageView imageview;




    @FXML
    private TextField AddressTextField;

    @FXML
    private TextField Phonenumber;

    @FXML
    private TextField RoomtypeText;

    @FXML
    private Button complete;

    @FXML
    private TextField districtTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField Name;


    @FXML
    private TextField addressfield;

    @FXML
    private TextField districtfield;

    @FXML
    private TextField emailfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField personsfield;

    @FXML
    private TextField phonenumberfield;

    @FXML
    private TextField roomtypefield;




    @FXML
    private void onHelloButtonClick(ActionEvent event) {
        if (usernameField != null && passwordField != null) {
            String providedUsername = usernameField.getText();
            String providedPassword = passwordField.getText();

            try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
                String sql = "SELECT * FROM loginpage WHERE username = ? AND password = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, providedUsername);
                    preparedStatement.setString(2, providedPassword);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            Welcometext.setText("Authentication successful!");
                            RoomScene();


                        } else {
                            Welcometext.setText("Invalid username or password.");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Welcometext.setText("Error: " + e.getMessage());
            }
        } else {
            Welcometext.setText("usernameField or passwordField is null!");
        }
    }




    @FXML


    private void RoomScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Roomm.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Selection field");
            stage.setScene(new Scene(root));

            stage.show();

            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();


        } catch (IOException e) {
            e.printStackTrace();
            Welcometext.setText("Error loading room scene: " + e.getMessage());


        }
    }

    @FXML
    private void Openuserscene() {
        LocalDate newcheckin = CheckinTextField.getValue();
        LocalDate newcheckout = CheckoutTextField.getValue();
        String newroom = RoomtypeTextField.getText();
        String newguest  = TotalguestTextField.getText();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO roomdetail (Checkin, Checkout , Roomtype , Totalguest) VALUES (?, ? , ? , ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, Date.valueOf(newcheckin));
                preparedStatement.setDate(2, Date.valueOf(newcheckout));
                preparedStatement.setString(3, newroom);
                preparedStatement.setString(4, newguest);

                preparedStatement.executeUpdate();
                System.out.print("User room details registered successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Error!");
        }
        openu();
    }

    private void openu() {






        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Userdetailss.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("User Details");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
        }


    @FXML
    private void userdetailconfirm() {

        String newname = Name.getText();
        String newemail = emailTextField.getText();
        String newaddress = AddressTextField.getText();
        String newphonenumber = Phonenumber.getText();
        String newroomtype = RoomtypeText.getText();
        String newdistrict = districtTextField.getText();


        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO userdetail (name, email , address , phonenumber,roomtype,district) VALUES (?, ? , ? , ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newname);
                preparedStatement.setString(2, newemail);
                preparedStatement.setString(3, newaddress);
                preparedStatement.setString(4, newphonenumber);
                preparedStatement.setString(5, newroomtype);
                preparedStatement.setString(6, newdistrict);

                preparedStatement.executeUpdate();
                System.out.print("User details registered successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Error!");
        }
        useru ();
    }

        public void useru () {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("conformation.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle(" user conformation");
                stage.setScene(new Scene(root));

                stage.show();

            } catch (IOException e) {
                e.printStackTrace();


            }
        }


    @FXML
    private void paymentdetails() {
        String newname = namefield.getText();
        String newemail = emailfield.getText();

        String newphonenumber = phonenumberfield.getText();
        String newaddress = addressfield.getText();
        String newdistrict = districtfield.getText();
        String newroomtype = roomtypefield.getText();
        String newpersons = personsfield.getText();



        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO userconfirm (name, email , phonenumber,address,district,roomtype,persons) VALUES (?, ? , ? , ?, ?, ? ,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, newname);
                preparedStatement.setString(2, newemail);
                preparedStatement.setString(3, newphonenumber);
                preparedStatement.setString(4, newaddress);
                preparedStatement.setString(5, newdistrict);
                preparedStatement.setString(6, newroomtype);
                preparedStatement.setString(7, newpersons);

                preparedStatement.executeUpdate();
                System.out.print("User details  are confirmed successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Error!");
        }
        payment();
    }

    private void payment(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("$$payment$$");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    @FXML
    private void finaldetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("final.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Final ");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    @FXML

    private void logindetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("login Scene");
            stage.setScene(new Scene(root));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();


        }
    }




}



