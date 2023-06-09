package com.mycompany.weather.jwt.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import static com.mycompany.weather.jwt.Constants.ADMIN;
import static com.mycompany.weather.jwt.Constants.USER;

@DeclareRoles({ADMIN,USER})
@ApplicationPath(value="api")
public class ApplicationConfig extends Application {
}
