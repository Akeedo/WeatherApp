package com.mycompany.weather;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("login")
public class LoginForm extends VerticalLayout {


    private TextField usernameField = new TextField("Username");
    private PasswordField passwordField = new PasswordField("Password");
    private Button loginButton = new Button("Login");
    private Button cancelButton = new Button("Cancel");

    public LoginForm() {
        FormLayout formLayout = new FormLayout(usernameField, passwordField);
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(loginButton,
            cancelButton);
        add(formLayout,buttonLayout);
    }

}
