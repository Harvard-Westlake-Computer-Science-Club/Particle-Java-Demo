import java.io.*;
import java.net.*;
import java.util.*;

public class Main
{
    public static void main(String[] args){
        
        // PASTE YOUR STRINGS HERE (view instructions)
        String deviceId = "e00fce684a70b154a260e2f8";
        String accessToken = "3c801adde90f9e42e1262263251c95563e18fd54";
        
        
        // Let's ask the user for a color
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a color name (options: red, green, blue): ");
        String color = keyboard.next();
        
        System.out.println("Sending your color to the Neopixel...");
        
        // Wrapping all of our networking logic in a try/catch block to intercept any errors
        // you'll see most of this code is the same as from our HW Lunch Menu demo!
        try {
            // Create a URL object and open a connection to the web server
            URL myUrl = new URL("https://api.particle.io/v1/devices/" + deviceId + "/changeColor");
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            
            // Set the HTTP method to POST, which means "set something" (i.e. set the color)
            connection.setRequestMethod("POST");
            // Allow ourselves to send data along with our request
            connection.setDoOutput(true);
            // Set the type of data we are sending. This simply means data in the form of:
            // variableOne=valueOne&variableTwo=valueTwo&variableThree=valueThree
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            
            // Let's create a PrintStream that contains our OutputStream that allows us to write
            // data to our web request. You can use PrintStream the same way you would use System.out
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            PrintStream out = new PrintStream(outputStream);
            
            // Write our data to the web request
            out.print("access_token=" + accessToken + "&args=" + color);
            
            // Send our request and get the response code! 200 = OK, any other code = ERROR
            int status = connection.getResponseCode();
            System.out.println("Ended with status " + status);
            
            if(status != 200) {
                // Let's get the error message to see what went wrong
                Scanner scanner = new Scanner(connection.getErrorStream());
		while (scanner.hasNext()) {
	            System.out.println(scanner.nextLine());
                }
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
