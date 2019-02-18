import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/", IndexController.index);
    }

    private static class IndexController {
        public static Route index = (Request request, Response response) -> {
            return "hello";
        };
    }
}
