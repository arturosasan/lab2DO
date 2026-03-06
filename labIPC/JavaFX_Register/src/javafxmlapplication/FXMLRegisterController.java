package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;

public class FXMLRegisterController implements Initializable {

  
    @FXML private TextField     emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField password2Field;
    @FXML private DatePicker    dateField;

    @FXML private Label emailError;
    @FXML private Label passwordError;
    @FXML private Label passwordConfirmError;
    @FXML private Label dateError;

    @FXML private Button bAccept;
    @FXML private Button bCancel;

    private BooleanProperty validEmail, validPassword, confirmPasswords, validDate;

    private ChangeListener<String> listenerEmail, listenerPassword, listenerPassword2;

    private void showError(boolean isValid, Node field, Node errorMessage) {
        errorMessage.setVisible(!isValid);
        field.setStyle(isValid ? "" : "-fx-background-color: #FCE5E0");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        validEmail = new SimpleBooleanProperty(false);
        validPassword = new SimpleBooleanProperty(false);
        confirmPasswords = new SimpleBooleanProperty(false);
        validDate = new SimpleBooleanProperty(false);

        // ---- EMAIL ----
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) { // usuario abandona el campo
                checkEmail();
                if (!validEmail.get()) {
                    // añadir listener para validar mientras escribe
                    if (listenerEmail == null) {
                        listenerEmail = (a, b, c) -> checkEmail();
                        emailField.textProperty().addListener(listenerEmail);
                    }
                } else {
                    // si ya es válido, quitamos el listener de escritura
                    if (listenerEmail != null) {
                        emailField.textProperty().removeListener(listenerEmail);
                        listenerEmail = null;
                    }
                }
            }
        });

        // ---- PASSWORD ----
        passwordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkPassword();
                if (!validPassword.get()) {
                    if (listenerPassword == null) {
                        listenerPassword = (a, b, c) -> checkPassword();
                        passwordField.textProperty().addListener(listenerPassword);
                    }
                } else {
                    if (listenerPassword != null) {
                        passwordField.textProperty().removeListener(listenerPassword);
                        listenerPassword = null;
                    }
                }
            }
        });

        // ---- PASSWORD2 ----
        password2Field.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkPasswordsMatch();
                if (!confirmPasswords.get()) {
                    if (listenerPassword2 == null) {
                        listenerPassword2 = (a, b, c) -> checkPasswordsMatch();
                        password2Field.textProperty().addListener(listenerPassword2);
                    }
                } else {
                    if (listenerPassword2 != null) {
                        password2Field.textProperty().removeListener(listenerPassword2);
                        listenerPassword2 = null;
                    }
                }
            }
        });

        // ---- DATE ----
        LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter() {
            @Override
            public LocalDate fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    System.out.println("Exception in fromString");
                    return LocalDate.now();
                }
            }
            @Override
            public String toString(LocalDate value) {
                return super.toString(value);
            }
        };
        dateField.setConverter(localDateStringConverter);

        dateField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkDate();
            }
        });

        // ---- BOTÓN ACCEPT: habilitado solo si todos los campos son válidos ----
        BooleanBinding validFields = Bindings.and(validEmail, validPassword)
                                             .and(confirmPasswords)
                                             .and(validDate);
        bAccept.disableProperty().bind(Bindings.not(validFields));

        // ---- BOTÓN CANCEL: cierra la ventana ----
        bCancel.setOnAction((event) -> {
            bCancel.getScene().getWindow().hide();
        });
    }

    // =========================================================
    // VALIDACIONES
    // =========================================================

    /** Valida formato de email con expresión regular */
    private void checkEmail() {
        String email = emailField.getText();
        boolean isValid = email.matches(
            "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
            "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        );
        validEmail.set(isValid);
        showError(isValid, emailField, emailError);
    }

    /** Valida que la contraseña tenga entre 8 y 15 caracteres alfanuméricos sin espacios */
    private void checkPassword() {
        String password = passwordField.getText();
        boolean isValid = password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}$");
        validPassword.set(isValid);
        showError(isValid, passwordField, passwordError);
    }

    /** Valida que la confirmación de contraseña coincida con la contraseña original */
    private void checkPasswordsMatch() {
        boolean match = passwordField.getText().equals(password2Field.getText());
        confirmPasswords.set(match);
        showError(match, password2Field, passwordConfirmError);
    }

    /** Valida que el usuario tenga más de 16 años */
    private void checkDate() {
        LocalDate value = dateField.getValue();
        if (value == null) {
            validDate.set(false);
            showError(false, dateField, dateError);
            return;
        }
        boolean isValid = value.isBefore(LocalDate.now().minus(16, YEARS));
        validDate.set(isValid);
        showError(isValid, dateField, dateError);
    }

    // =========================================================
    // BOTONES
    // =========================================================
    @FXML
    private void handleBAcceptOnAction(ActionEvent event) {

        emailField.clear();
        passwordField.clear();
        password2Field.clear();
        dateField.setValue(null);

        validEmail.setValue(Boolean.FALSE);
        validPassword.setValue(Boolean.FALSE);
        confirmPasswords.setValue(Boolean.FALSE);
        validDate.setValue(Boolean.FALSE);

        // Llamar a método que muestra el error cometido en cada campo
        showError(true, emailField, emailError);
        showError(true, passwordField, passwordError);
        showError(true, password2Field, passwordConfirmError);
        showError(true, dateField, dateError);
    }
}
