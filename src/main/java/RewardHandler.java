import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;

public class RewardHandler implements Route{
	
	private final String htmlHead = "<html>\n" + 
			"<head>\n" + 
			"  <title>Nutrition App</title>\n" + 
			"  <meta charset=\"utf-8\">\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" + 
			"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" + 
			"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" + 
			"</head>\n" + 
			"<body><div class=\\\"container\\\"><h2>Our suggestion</h2>";

	public Object handle(Request request, Response response) throws Exception {
		HashMap<String, Double> meal = new HashMap<String, Double>();
		String mealString = request.cookie("meal");
		mealString = mealString.substring(1, mealString.length()-1); 
		String[] keyValuePairs = mealString.split(",");              //split the string to creat key-value pairs

		for (String pair : keyValuePairs)                        //iterate over the pairs
		{
		    String[] entry = pair.split("=");                   //split the pairs to get key and value 
		    meal.put(entry[0].trim(), Double.parseDouble(entry[1].trim()));          //add them to the hashmap and trim whitespaces
		}
		// get data
		String userName = request.cookie("name");
		int age = Integer.parseInt(request.cookie("age"));
		String gender = request.cookie("gender");
		String activityLevel = request.cookie("activityLevel");
		
		// clear form
		response.removeCookie("name");      
		response.removeCookie("age");      
		response.removeCookie("gender");      
		response.removeCookie("activityLevel");      
		response.removeCookie("meal");      
		
		NutritionRecommender nutritionApp = new NutritionRecommender(userName, age, gender, activityLevel);
		nutritionApp.addMeal(meal);
		ArrayList<String> suggestions = nutritionApp.getSuggestions();
		String htmlBody = "<ul class=\"list-group\">\n";
		meal = new HashMap<String, Double>();
		for (String suggestion: suggestions) {
			htmlBody +=  "<li class=\"list-group-item active\">" + suggestion + "</li>\n";
		}
		htmlBody += "</ul>";
		return htmlHead + "<body><h3>Hi " + userName + "! Here is your suggestion!<h3>" + htmlBody + "</div></body></html>";
	}
	
}