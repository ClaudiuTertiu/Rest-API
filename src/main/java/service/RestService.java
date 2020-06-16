package service;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import handler.product.ProductHandler;
import handler.product.ProductPostHandler;
import handler.registration.RegistrationHandler;
import handler.user.UserHandler;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestService {
    private static RestService restService = null;
    private static boolean authenticated = false;
    private HttpServer server;

    public static RestService getInstance()
    {
        if (restService == null)
            restService = new RestService();
        return restService;
    }
    public void createContexts(RegistrationHandler registrationHandler, ProductHandler productHandler, UserHandler userHandler, ProductPostHandler productPostHandler) {

<<<<<<< Updated upstream
        restService.getServer().createContext("/api/register", registrationHandler::handle);

        HttpContext usersContext = restService.getServer().createContext("/api/users", userHandler::handle);
        if(authenticated == false)
            restService.basicAuthenticator(usersContext);

        HttpContext productContext = restService.getServer().createContext("/api/products", productHandler::handle);
        if(authenticated == false)
            restService.basicAuthenticator(productContext);

        HttpContext addProductContext = restService.getServer().createContext("/api/products/add", productPostHandler::handle);
        if(authenticated == false)
            restService.basicAuthenticator(addProductContext);
    }

    public void basicAuthenticator (HttpContext context) {
=======
        restService.getServer().createContext("/api/users/register", registrationHandler::handle);

        HttpContext usersContext = restService.getServer().createContext("/api/users", userHandler::handle);
        if(!authenticated)
            restService.basicAuthenticator(usersContext);

        HttpContext productContext = restService.getServer().createContext("/api/products", productHandler::handle);
        if(!authenticated)
            restService.basicAuthenticator(productContext);

        restService.getServer().createContext("/api/products/add", productPostHandler::handle);
        if(!authenticated)
            restService.basicAuthenticator(productContext);
    }

    private void basicAuthenticator(HttpContext context) {
>>>>>>> Stashed changes
            context.setAuthenticator(new BasicAuthenticator("Login for access.") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                authenticated = user.equals("admin") && pwd.equals("admin");
                return authenticated;
            }
        });
    }
}
