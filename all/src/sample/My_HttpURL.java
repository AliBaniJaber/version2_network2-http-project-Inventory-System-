package sample;

import javafx.scene.control.Alert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;


import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;


class My_HttpURL{

     String URL_products_php="http://localhost/network2_Inventory/products.php";
     String URLusers_php="http://localhost/network2_Inventory/userpage.php";
     String URL_NET2_servlet="http://localhost:8084/Network2_Servlet_war_exploded/";












    StringBuffer response;

     void send_GET_method_to_add_new_item(String name , int amount , double pricPerItem)
     {
         try {
             response=new StringBuffer();
             URL obj = new URL(GET_URL_products+"?type=insert_new_products&name="+name+"&amount="+amount+"&pricPerItem="+pricPerItem);
             HttpURLConnection con = (HttpURLConnection) obj.openConnection();
             con.setRequestMethod("GET");
             //con.setRequestProperty("type","get_me_all_data");

             int responseCode = con.getResponseCode();

             System.out.println("GET Response Code :: " + responseCode);
             if (responseCode == HttpURLConnection.HTTP_OK) { // success
                 BufferedReader in = new BufferedReader(new InputStreamReader(
                         con.getInputStream()));
                 String inputLine;
                 while ((inputLine = in.readLine()) != null) {
                     response.append(inputLine);
                     System.out.println(inputLine);
                 }
                 in.close();
             } else {
                 System.out.println("GET request not worked");
             }
         }
         catch (Exception e)
         {

         }
     }

    public StringBuffer send_POST_get_my_all_product()  {
         try {
             response=new StringBuffer();

             Map<Object, Object> data = new HashMap<>();
             data.put("type", "get_me_all_data");
             data.put("ts", System.currentTimeMillis());

             HttpRequest request = HttpRequest.newBuilder()
                     .POST(buildFormDataFromMap(data))
                     .uri(URI.create("http://localhost/network2_Inventory/products.php"))
                     .setHeader("User-Agent", "Java 11 HttpClient Bot")
                     .header("Content-Type", "application/x-www-form-urlencoded")
                     .build();
             HttpClient httpClient = HttpClient.newBuilder()
                     .version(HttpClient.Version.HTTP_2)
                     .build();

             HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
             System.out.println(response.statusCode());
             System.out.println(response.body());
             return new StringBuffer( response.body().toString());
         }
         catch (Exception e)
         {

         }
         return  new StringBuffer("");

    }
    public void send_post_to_add_new_product(String name , int amount , double pricPerItem)
    {

        try {
            response=new StringBuffer();
            Map<Object, Object> data = new HashMap<>();
            data.put("type", "insert_new_products");
            data.put("name", name);
            data.put("amount",amount);
            data.put("pricPerItem", pricPerItem);
            data.put("ts", System.currentTimeMillis());

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost/network2_Inventory/products.php"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());

        }
        catch (Exception e)
        {

        }



    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    public  StringBuffer sendGET_get_my_all_product() {
        try {
            response=new StringBuffer();
            URL obj = new URL(GET_URL_products+"?type=get_me_all_data");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }

       return response;

    }

    public StringBuffer sendGET_to_dec_product(int id_product, int amount_int) {

        try {
            response=new StringBuffer();
            URL obj = new URL(GET_URL_products+"?type=dec&id="+id_product+"&amount="+""+amount_int);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }


        return response;
    }

    public StringBuffer sendPOST_to_dec_product(int id_pro, int amount_int) {

        try {
            response=new StringBuffer();

            Map<Object, Object> data = new HashMap<>();
            data.put("type", "dec");
            data.put("id", id_pro);
            data.put("amount", amount_int);
            data.put("ts", System.currentTimeMillis());

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost/network2_Inventory/products.php"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            return new StringBuffer( response.body().toString());
        }
        catch (Exception e)
        {

        }
        return  new StringBuffer("");
    }

    public void sendGET_ot_giv_info_of_product(int id_product_int) {
        try {
            response=new StringBuffer();
            URL obj = new URL(GET_URL_products+"?type=show_info_of_product&IDProduct="+id_product_int);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }

        if(response.toString().equals("not found"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("the product is not exist");
            alert.setContentText("pleas enther id exist");

            alert.showAndWait();
        }
        else{

            String result[]=response.toString().split(",");


            String id_respons="";
            String name_respons="";
            String amount_respons="";
            String pricPerItem_respons="";
                id_respons=result[0].split(":")[1];
                name_respons=result[1].split(":")[1].replace("\""," ");
                amount_respons=result[2].split(":")[1].replace("\""," ");
                pricPerItem_respons=result[3].split(":")[1].replace("}"," ").replace("\""," ").replace("]"," ");
                System.out.println(id_respons + name_respons+amount_respons+pricPerItem_respons);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("id_product    :"+id_respons+
                              "\n name_product  :"+name_respons+
                            "\n   amount        :"+amount_respons+
                         "\n  pricPerItem in $  :"+pricPerItem_respons +"$"+
                           "\nSekel             :"+"20");

            alert.showAndWait();




        }

    }

    public StringBuffer sendPOST_ot_giv_info_of_product(int id_product_int) {

        try {
            response=new StringBuffer();
            Map<Object, Object> data = new HashMap<>();
            data.put("type", "show_info_of_product");
            data.put("IDProduct", id_product_int);

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost/network2_Inventory/products.php"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            return new StringBuffer( response.body().toString());

        }


        catch (Exception e)
        {
                System.out.println(e.getStackTrace());
        }


        return null;
    }

    public StringBuffer sendGET_to_inc_product(int id_product, int amount_int) {


        try {
            response=new StringBuffer();
            URL obj = new URL(GET_URL_products+"?type=inc&id="+id_product+"&amount="+""+amount_int);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }


        return response;


    }

    public StringBuffer sendPOST_to_inc_product(int id_pro, int amount_int) {

        try {
            response=new StringBuffer();

            Map<Object, Object> data = new HashMap<>();
            data.put("type", "inc");
            data.put("id", id_pro);
            data.put("amount", amount_int);
            data.put("ts", System.currentTimeMillis());

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost/network2_Inventory/products.php"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            return new StringBuffer( response.body().toString());
        }
        catch (Exception e)
        {

        }
        return  new StringBuffer("");

    }

    public StringBuffer send_get_method_changepasswor(String id_user,String lastpass, String newpass) {

        try {
            response=new StringBuffer();
            URL obj = new URL("http://localhost/network2_Inventory/userpage.php"+"?type=ChangePassword&id_user="+id_user+"&lastpass="+lastpass+"&newpass="+newpass);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }

        return response;
    }

    public StringBuffer send_post_method_changepasswor(String id_user,String lastpass, String newpass) {
        try {
            response=new StringBuffer();
            Map<Object, Object> data = new HashMap<>();
            data.put("type", "ChangePassword");
            data.put("id_user", id_user);
            data.put("lastpass", lastpass);
            data.put("newpass", newpass);
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost/network2_Inventory/userpage.php"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            return new StringBuffer( response.body().toString());

        }


        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }


        return null;


    }

    public String send_GETrequest_to_login(String username, String pass) {

        try {
            response=new StringBuffer();
            URL obj = new URL("http://localhost/network2_Inventory/userpage.php"+"?type=login&username="+username+"&pass="+pass);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();

            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println(inputLine);
                }
                in.reset();
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        }
        catch (Exception e)
        {

        }

        return response.toString();

    }

    public String send_POSTRrequest_to_login(String username, String pass) {
        try {
            response=new StringBuffer();
            Map<Object, Object> data = new HashMap<>();
            data.put("type","login");
            data.put("username", username);
            data.put("pass", pass);

            HttpRequest request = HttpRequest.newBuilder()
                    .POST(buildFormDataFromMap(data))
                    .uri(URI.create("http://localhost/network2_Inventory/userpage.php"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpClient httpClient = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            return  response.body().toString();

        }


        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }


        return null;

    }
}