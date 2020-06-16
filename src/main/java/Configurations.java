import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import handler.product.ProductPostHandler;
import handler.user.UserHandler;
import exceptions.GlobalExceptionHandler;
import handler.product.ProductHandler;
import handler.registration.RegistrationHandler;
import service.RestService;
import utils.Constants;

import java.io.IOException;
import java.net.InetSocketAddress;

class Configurations {

<<<<<<< Updated upstream
    private static HttpServer HTTP_SERVER = null;
    private static final RestService REST_SERVICE = RestService.getInstance();
=======
    private static final RestService REST_SERVICE = RestService.getInstance();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);
    private static final UserHandler USER_HANDLER = new UserHandler( Configurations.getObjectMapper(), Configurations.getErrorHandler());
    private static final RegistrationHandler REGISTRATION_HANDLER = new RegistrationHandler(Configurations.getObjectMapper(), Configurations.getErrorHandler());
    private static final ProductHandler PRODUCT_HANDLER = new ProductHandler(Configurations.getObjectMapper(), Configurations.getErrorHandler());
    private static HttpServer HTTP_SERVER = null;
    private static ProductPostHandler PRODUCT_POST_HANDLER;
>>>>>>> Stashed changes

    static {
        try {
            HTTP_SERVER = HttpServer.create(new InetSocketAddress(Constants.SERVER_PORT), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< Updated upstream
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);
    private static final UserHandler USER_HANDLER = new UserHandler( Configurations.getObjectMapper(), Configurations.getErrorHandler());
    private static final RegistrationHandler REGISTRATION_HANDLER = new RegistrationHandler(Configurations.getObjectMapper(), Configurations.getErrorHandler());
    private static final ProductPostHandler PRODUCT_POST_HANDLER = new ProductPostHandler(Configurations.getObjectMapper(), Configurations.getErrorHandler());
    private static final ProductHandler PRODUCT_HANDLER = new ProductHandler(Configurations.getObjectMapper(), Configurations.getErrorHandler());
=======
    static {
        PRODUCT_POST_HANDLER = new ProductPostHandler(Configurations.getObjectMapper(), Configurations.getErrorHandler());
    }
>>>>>>> Stashed changes

    static {
        REST_SERVICE.setServer(HTTP_SERVER);
    }

<<<<<<< Updated upstream
    static ObjectMapper getObjectMapper() {return OBJECT_MAPPER;}

    static RestService getRestService() {return REST_SERVICE;}

    static GlobalExceptionHandler getErrorHandler() {return GLOBAL_ERROR_HANDLER;}
=======
    private static ObjectMapper getObjectMapper() {return OBJECT_MAPPER;}

    static RestService getRestService() {return REST_SERVICE;}

    private static GlobalExceptionHandler getErrorHandler() {return GLOBAL_ERROR_HANDLER;}
>>>>>>> Stashed changes

    static RegistrationHandler getRegistrationHandler() {return REGISTRATION_HANDLER;}

    static ProductHandler getProductHandler() {return PRODUCT_HANDLER;}

    static UserHandler getUserHandler() {return USER_HANDLER;}

    static ProductPostHandler getProductPostHandler() {return PRODUCT_POST_HANDLER;}
<<<<<<< Updated upstream

    static HttpServer getHttpServer() {return HTTP_SERVER;}
=======
>>>>>>> Stashed changes
}