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
			"</head><body><div class=\"container\"><h2>What you have eaten</h2>";
	
	private final String newTaskForm = "<div><form action=\"/createtodo\" method=\"post\">Add Food name and number of servings:"
			+ "<br>"
			+ "<label for=\"foodname\">Tell us what you ate today!</label>"
			+ "<br>"
			+ "<input type=\"text\" name=\"foodname\">"
			+ "<br>"
			+ "<label for=\"userName\">Tell us how many g of this food you had?</label>"
			+ "<br>"
			+ "<input type=\"portion\" name=\"portion\">"
			+ "<br>"
			+ "<br>"
			+ "<button style=\"margin-left: 10px\" type=\"submit\">Create Food</button>"
			+ "</form></div>";
	
	private final String exampleList = "<div class=\"container\"><li>Here are some sample food you can use: </li>"
			+ "<li>Dutch Apple Pie</li>"
			+ "<li>Bread White Wheat</li>"
			+ "<li>Almond Joy Candy Bar</li>"
			+ "<li>Snacks Fruit Leather Rolls</li></div>";
	
	private final String finishButton = "<button style=\"margin-left: 10px\" onclick=\"location.href='/taskdone'\">Finished Adding Food</button>";
	

	private HashMap<String, Double> meal;
	
	public TodoListHandler() {
		meal = new HashMap<String, Double>();
	}

	public Object handle(Request request, Response response) throws Exception {
		String error = "";
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
			String foodname = request.queryParams("foodname");
			Double portion = Double.valueOf(request.queryParams("portion"));
			if (foodLib.getLibrary().containsKey(foodname)) {
				meal.put(foodname, portion);
			}
			else {
				error = "<div class=\"alert alert-primary\" role=\"alert\">\n" + 
						"Sorry! Cannot find this food in our libary!!!\n" + 
						"</div>";
			}
		}
		
		return htmlHead + "<div><h3>Todo List<h3></div>" + error +
				taskList() + newTaskForm + exampleList + "<br>" + finishButton + "</div></body></html>";
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