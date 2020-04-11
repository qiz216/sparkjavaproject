import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import spark.Request;
import spark.Response;
import spark.Route;

public class TodoListHandler implements Route{
	private final String htmlHead = "<html><head><title>Eating</title></head>";
	
	private final String newTaskForm = "<div><form action=\"/createtodo\" method=\"post\">Add Food name and number of servings:"
			+ "<li>Food</li>"
			+ "<input type=\"text\" name=\"foodname\">"
			+ "<li>Number of Servings</li>"
			+ "<input type=\"number\" name=\"portion\">"
			+ "<button style=\"margin-left: 10px\" type=\"submit\">Create Food</button>"
			+ "</form></div>";
	private final String finishButton = "<button style=\"margin-left: 10px\" onclick=\"location.href='/taskdone'\">Finished Adding Food</button>";
	
	private final int TARGET_NUM_OF_TASKS = 10;
	private int tasksCompleted;
	private HashMap<String, Double> meal;
	
	public TodoListHandler() {
		tasksCompleted = 0;
		meal = new HashMap<String, Double>();
	}

	public Object handle(Request request, Response response) throws Exception {
		if ("/taskdone".equals(request.pathInfo())) {
				response.redirect("/reward");
		} else if ("/createtodo".equals(request.pathInfo())) {
			String foodname = request.queryParams("foodname");
			Double portion = Double.valueOf(request.queryParams("portion"));
			meal.put(foodname, portion);
		}
		
		return htmlHead + "<body><div><h3>Todo List<h3></div>" +
				taskCounter() + taskList() + newTaskForm + finishButton + "</body></html>";
	}
	
	public HashMap<String, Double> getMeal() {
		return meal;
	}

	public String taskCounter() {
		return "<div><h4>Target Number of Tasks: " + TARGET_NUM_OF_TASKS +
				"</h4><h4>Tasks Completed: " + tasksCompleted + "</h4></div>";
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