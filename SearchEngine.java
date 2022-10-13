import java.io.IOException;
import java.net.URI;
import java.io.*; 
import java.util.*; 

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    ArrayList<String> strArray = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            System.out.println("Path: " + url.getPath());
            System.out.println("Adding");
            String[] parameters = url.getQuery().split("=");
            strArray.add(parameters[1]);
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(parameters[i]);
            }
            return "String " + parameters[1] + " added to list";
        }
        else if (url.getPath().equals("/search")) {
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            String returnValues = "Strings in the list that contain " + parameters[1] + ":";
            for (int i = 0; i < strArray.size(); i++) {
                System.out.println("i equals: " + i);
                if (strArray.get(i).contains(parameters[1])) {
                    returnValues = returnValues.concat("\n" + strArray.get(i));
                    System.out.println("Concatenating " + strArray.get(i));
                    System.out.println("Return Values: " + returnValues);
                }
            }
            return returnValues;
        }
        else {
            /*System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                strArray.add(parameters[0]);
            return "String " + parameters[0] + "added to list";
            }*/
            return "404 Not Found!";
        }
        //return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
