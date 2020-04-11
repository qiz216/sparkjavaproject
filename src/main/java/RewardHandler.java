import spark.Request;
import spark.Response;
import spark.Route;

public class RewardHandler implements Route{
	private final String htmlHead = "<html><head><title>Food</title></head>";
	private final String userForm = "<div><form action=\"/addProfile\" method=\"post\">Add User Profile:"
			+ "<li>User Name</li>"
			+ "<input type=\"text\" name=\"userName\">"
			+ "<li>User Age</li>"
			+ "<input type=\"number\" name=\"userAge\">"
			+ "<li>User Gender</li>"
			+ "<select id=\"userGender\" name=\"userGender\">\n"
			+ " <option value=\"M\">Male</option>\n" 
			+ " <option value=\"F\">Female</option>\n" 
			+ "</select>"
			+ "<select id=\"userActivity\" name=\"userActivity\">\n"
			+ " <option value=\"L\">low (less than 1 time/week)</option>\n" 
			+ " <option value=\"M\">medium</option>\n" 
			+ " <option value=\"H\">high (more than 4 times per week)</option>\n" 
			+ "</select>"
			+ "<button style=\"margin-left: 10px\" onclick=\\\\\\\"location.href='/taskdone'\\\\\\>Submit</button>"
			+ "</form></div>";

	public Object handle(Request request, Response response) throws Exception {
		return htmlHead + userForm + "<body><h3>Good job!<h3></body></html>";
	}
	
}