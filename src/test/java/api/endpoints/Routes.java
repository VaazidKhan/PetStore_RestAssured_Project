package api.endpoints;



//Maintain the urls in routes

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User Module
	public static String post_url = base_url+"/user";
	public static String get_url = post_url+"/{userName}";
	public static String update_url = post_url+"/{userName}";
	public static String delete_url = post_url+"/{userName}";
	
	//store module
	//pet module

}