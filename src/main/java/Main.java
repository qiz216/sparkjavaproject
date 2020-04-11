import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    
    
	public static void main(String[] args) {
		port(getHerokuAssignedPort());
		HomePageHandler homePageHandler = new HomePageHandler();
		TodoListHandler todoListHandler = new TodoListHandler();
		RewardHandler rewardHandler = new RewardHandler();
		
		get("/", homePageHandler);
		get("/toeat", todoListHandler);
		get("/taskdone", todoListHandler);
		get("/reward", rewardHandler);
		post("/addProfile", rewardHandler);
		post("/createtodo", todoListHandler);
	}
}