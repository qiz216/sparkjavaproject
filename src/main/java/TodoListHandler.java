import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import spark.Request;
import spark.Response;
import spark.Route;

public class TodoListHandler implements Route{
	private final FoodLibrary foodLib = new FoodLibrary();
	
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
			"</div>"
			+ "<body><div class=\"container\">";
	
	private final String newTaskForm = "<div><form action=\"/createtodo\" method=\"post\">Add Food name and number of servings:"
			+ "<br>"
			+ "<label for=\"searchfood\">Tell us what you ate today!</label>"
			+ "<br>"
			+ "<input type=\"text\" name=\"searchfood\" value=\"\">"
			+ "<br>"
			+ "<br>"
			+ "<button style=\"margin-left: 10px\" type=\"submit\">Search Food</button>"
			+ "</form></div>";


	
	private final String finishButton = "<button style=\"margin-left: 10px\" onclick=\"location.href='/taskdone'\">Finished Adding Food</button>";
	

	private HashMap<String, Double> meal;
	
	public TodoListHandler() {
		meal = new HashMap<String, Double>();
	}

	public Object handle(Request request, Response response) throws Exception {
		String error = "";
		String suggest = "";
		if ("/taskdone".equals(request.pathInfo())) {
			if (meal.isEmpty()) {
				error = "<div class=\"alert alert-primary\" role=\"alert\">\n" + 
						"Sorry! Please add at least one food!!!\n" + 
						"</div>";
			} else {
				response.cookie("meal", meal.toString(), 3600);  
				meal.clear();
				response.redirect("/user");
			}
			
			
		} else if ("/createtodo".equals(request.pathInfo())) {
			
			String foodName = request.queryParams("searchfood");
			FoodFinder finder = new FoodFinder();
			ArrayList<String> foodList = finder.getTopNMatched(foodName, 10);
			
			if (foodList.size() >0) {
				suggest = "<br>";
				StringBuilder sb = new StringBuilder();
				sb.append("<div><form action=\"/searchFood\" method=\"post\"> We have some matches<br><br>");
				sb.append("<label for=\"foodname\">Select a food in our library: </label><br><br>");
				sb.append("<select id=\"foodname\", name=\"foodname\">");
				for ( String fd : foodList) {
					Food food = foodLib.getLibrary().get(fd);
					sb.append("<option value=\"" + fd + "\">"+ "Food: " + fd + " ------  Each serving is: " + food.getServingDes() + "</option>");
					sb.append("</ul></div>");
				}
				sb.append("</select>");
				sb.append("<br><br><input type=\"text\" name=\"portion\"><br><br>");
				sb.append("<button style=\"margin-left: 10px\" type=\"submit\">Add Food</button>");
				sb.append("</form></div>");
				suggest += sb.toString();	
			} else {
				error = "<div class=\"alert alert-primary\" role=\"alert\">\n" + 
						"Sorry! Cannot find any food that matches this food.\n" +
						"</div>";
			}
		} else if ("/searchFood".equals(request.pathInfo())) {
			String foodname = request.queryParams("foodname");
			Double portion = Double.valueOf(request.queryParams("portion"));
			meal.put(foodname, portion);
		}
		return htmlHead + error +
				taskList() + newTaskForm + "<br>"  + suggest + "<br>" + finishButton + "</div></body></html>";
	}
	
	public HashMap<String, Double> getMeal() {
		return meal;
	}
	
	public String taskList() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div><ul>");
		
		for (Entry<String, Double> task : meal.entrySet()) {
			sb.append("<li>" + "Food: " + task.getKey() + " servings: " + String.valueOf(task.getValue()) + "</li>");
		}
		
		sb.append("</ul></div>");
		
		return sb.toString();
	}
	
}