import java.util.HashMap;

import spark.Request;
import spark.Response;
import spark.Route;

public class UserHandler  implements Route{
	
	private final String htmlHead = "<html>\n" + 
			"<head>\n" + 
			"  <title>Nutrition App</title>\n" + 
			"  <meta charset=\"utf-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" + 
			"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" + 
			"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" + 
			"</head>" +
			"  <div class=\"container\">\n" + 
			"  <img src=\"https://www.forksoverknives.com/wp-content/uploads/fly-images/35705/plantbased-diet-1142x541-c.jpg\" alt=\"food\" style=\"width:100%;opacity: 0.5;\">\n" + 
			"  <div class=\"text-block\">\n" + 
			"    <h1>Welcome to Nutrition App</h1>\n" + 
			"    <p>your health matters</p>\n" + 
			"  </div>\n" + 
			"</div>" +
			"<body><div class=\"container\"><h2>Tell us about yourself</h2>";
	
	private final String userForm = "<form action=\"/addProfile\" method=\"post\">Add User Profile:"
			+ "<br>"
			+ "<br>"
			+ "<label for=\"userName\">Tell us your name!</label>"
			+ "<br>"
			+ "<br>"
			+ "<input type=\"text\" name=\"userName\">"
			+ "<br>"
			+ "<br>"
			+ "<label for=\"userAge\">What is your age? (between 2 and 100)</label>"
			+ "<br>"
			+ "<br>"
			+ "<input id=\"rangeInput\" type=\"range\" name=\"userAge\" , min=\"2\" max=\"100\"value=\"0\"\n"
			+ "oninput=\"amount.value=rangeInput.value\">"
			+ "<output id=\"amount\" name=\"amount\" for=\"rangeInput\">20</output>" 
			+ "<br><br>"
			+ "<label for=\\\"userGender\\\">What is your gender? (We only has M and F now)</label>"
			+ "<br><br>"
			+ "<select id=\"userGender\" name=\"userGender\">\n"
			+ " <option value=\"M\">Male</option>\n" 
			+ " <option value=\"F\">Female</option>\n" 
			+ "</select>"
			+ "<br><br>"
			+ "<label for=\\\"userActivity\\\">How ofte do you work out?</label>"
			+ "<br><br>"
			+ "<select id=\"userActivity\" name=\"userActivity\">\n"
			+ " <option value=\"L\">low (less than 1 time/week)</option>\n" 
			+ " <option value=\"M\">medium</option>\n" 
			+ " <option value=\"H\">high (more than 4 times per week)</option>\n" 
			+ "</select>"
			+ "<br><br>"
			+ "<button style=\"margin-left: 10px\" type=\"submit\">I am done!</button>"
			+ "</form>";
	
	
	public Object handle(Request request, Response response) throws Exception {
		
		if ("/addProfile".equals(request.pathInfo())) {
			response.cookie("name", request.queryParams("userName"), 3600);  
			response.cookie("age", request.queryParams("userAge"), 3600);  
			response.cookie("gender", request.queryParams("userGender"), 3600);  
			response.cookie("activityLevel", request.queryParams("userActivity"), 3600);
			response.redirect("/reward");
		}
		return htmlHead + userForm + "</div></body></html>";
	}

}
