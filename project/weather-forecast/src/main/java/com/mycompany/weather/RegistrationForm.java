package com.mycompany.weather;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("registration")
public class RegistrationForm extends VerticalLayout {


    private TextField usernameField = new TextField("Username");
    private PasswordField passwordField = new PasswordField("Password");
    private Button registerButton = new Button("Register");

    private Button cancelButton = new Button("Cancel");

    public RegistrationForm() {
        FormLayout formLayout = new FormLayout(usernameField, passwordField);
        HorizontalLayout buttonLayout = new HorizontalLayout(registerButton,
            cancelButton);
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(formLayout,buttonLayout);
    }

}
