package api_endpoints;

public class Routes {
    //keep only the URLs

    // public so we can use it across all classes
    // static to use it using className without need to create object
    public static String base_url = "https://petstore.swagger.io/v2";

    //User Module
    // the "{username}" is path parameter
    public static String post_url   = base_url + "/user";
    public static String get_url    = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";
    public static String login_url = base_url + "/user/login";
    public static String logout_url = base_url + "/user/logout";

    //Store Module
        //here create store module urls

    //Pet Module
        //here create pet module urls
}
