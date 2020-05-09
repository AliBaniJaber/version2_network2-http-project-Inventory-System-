package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Optional;


public class Controller {

    @FXML
    private Button login;

    @FXML
    ComboBox Settings;

    @FXML
    AnchorPane login_panel ;

    @FXML
    TextField inputusername;
    @FXML
    TextField inputpass;
    @FXML
    AnchorPane page1 ;
    @FXML
    TableView dataTeable;
    @FXML
    TableColumn id;
    @FXML
    TableColumn Name;
    @FXML
    TableColumn Mont;

    @FXML
    TableColumn Dollar;

    @FXML
    TableColumn shekel;
    @FXML
   Button add_data_to_table;
    @FXML
    Button add_new_product;
    @FXML
    Button withdraw_item;
    @FXML
    Button show_info_of_product;
    @FXML
    Button ChangePassword;

    My_HttpURL obj_request=new My_HttpURL();

    String type_of_request="GET";
    String name_server=null;
    @FXML
    MenuButton Select_Server;

    @FXML
    MenuButton Select_Method;

    @FXML
    public void handeLAllRequest(ActionEvent  event)
    {
        if(event.getSource()==add_data_to_table)
        {
            System.out.println("Show All Products in Inventory");
                add_data_to_table(type_of_request);
        }
        if(event.getSource()==add_new_product)
        {
            handel_add_new_product(type_of_request);
        }

        if(event.getSource()==withdraw_item)
        {
            handle_withdraw_item(type_of_request);
        }
        if(event.getSource()==show_info_of_product)
        {
            show_info_of_product(type_of_request);
        }
        if(event.getSource()==ChangePassword)
        {
            this.changepasswordfunction(type_of_request);
        }



    }

    private void handle_withdraw_item(String method) {

        TextInputDialog dialog1 = new TextInputDialog("walter");
        dialog1.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog1.setHeaderText("");
        dialog1.setContentText("Please enter  id of producct :");
        Optional<String> id_product = dialog1.showAndWait();
        if (id_product.isPresent()){
            System.out.println("Your name: " + id_product.get());
        }
        int id_product_int = 0;
        try {
            id_product_int=Integer.parseInt(id_product.get().toString());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();
            return;
        }
        TextInputDialog dialog2 = new TextInputDialog("walter");
        dialog2.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog2.setHeaderText("");
        dialog2.setContentText("Please enter  amount to dec in : ");
        Optional<String> aount_dec = dialog2.showAndWait();
        if (aount_dec.isPresent()){
            System.out.println("Your name: " + aount_dec.get());
        }
        int int_amount_pro=0;
        try {
            int_amount_pro=Integer.parseInt(aount_dec.get().toString());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();
            return;
        }

        StringBuffer respopons=new StringBuffer("");
        if(method.equals("GET"))
             respopons=obj_request.sendGET_to_dec_product(id_product_int,int_amount_pro);
        if(method.equals("POST"))
             respopons= obj_request.sendPOST_to_dec_product(id_product_int,int_amount_pro);

        if(respopons.toString().equals("cant do this operation")){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("the amount is not enough");
            alert.setContentText("Please enter a amount of the product");

            alert.showAndWait();

        }
        else if(respopons.toString().equals("not found "))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("There is no product with this id "+id_product_int+"it\n");
            alert.setContentText("Please enter a amount of the product");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(" update the product information successfully");
            alert.setContentText(" successfully  ");

            alert.showAndWait();
        }






    }


    private boolean valedation(String data){
        return true;

    }

    String username=null;
    String pass=null;
    @FXML
    Label Employee_ID;


    @FXML
    public void handlerLongIn()
    {
        System.out.println(inputusername.getText()+inputpass.getText());
        username=inputusername.getText();
        pass=inputpass.getText();

        boolean result1= valedation(username);
        boolean result2= valedation(pass);
        if(result1 && result2) {

            String id_user=type_of_request.equals("GET")?obj_request.send_GETrequest_to_login(username,pass):obj_request.send_POSTRrequest_to_login(username,pass).replace("\r","").replace("\n","");

            if(!id_user.equals("-1"))
            {
                login_panel.setVisible(false);
                page1.setVisible(true);
                Employee_ID.setText(id_user);



            }
        }

    }

  @FXML
  public void php_server(){

        name_server="php";
  }
  @FXML
  public void java_server()
  {
       name_server="java";
  }
  @FXML
  public void post_method()
  {
      type_of_request="POST";
  }
  @FXML
  public void get_method()
  {
        type_of_request="GET";
  }




 private void add_data_to_table(String method ){
        dataTeable.getItems().clear();
        String id_respons="";
        String name_respons="";
        String amount_respons="";
        String pricPerItem_respons="";
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Mont.setCellValueFactory(new PropertyValueFactory<>("mount"));
        Dollar.setCellValueFactory(new PropertyValueFactory<>("USD"));
        shekel.setCellValueFactory(new PropertyValueFactory<>("EUR"));
        StringBuffer response;
        if(method.equals("GET"))
         response =obj_request.sendGET_get_my_all_product();
        else
        {
            response =obj_request.send_POST_get_my_all_product();
        }
        String result[]=response.toString().split(",");
        Record_In_Teable in_data;
        for(int i=0;i<result.length;i=i+4)
        {

            id_respons=result[i].split(":")[1];
            name_respons=result[i+1].split(":")[1].replace("\""," ");
            amount_respons=result[i+2].split(":")[1].replace("\""," ");
            pricPerItem_respons=result[i+3].split(":")[1].replace("}"," ").replace("\""," ").replace("]"," ");
            System.out.println(id_respons + name_respons+amount_respons+pricPerItem_respons);
            dataTeable.getItems().add(new Record_In_Teable(""+id_respons,""+name_respons , ""+amount_respons, ""+pricPerItem_respons, "20"));


        }

    }

 private void show_info_of_product(String method) {


       TextInputDialog dialog1 = new TextInputDialog("walter");
       dialog1.setTitle("show_info_of_product");
       dialog1.setHeaderText("");
       dialog1.setContentText("Please enter  id of producct to show info  :");
       Optional<String> id_product = dialog1.showAndWait();
       if (id_product.isPresent()){
           System.out.println("Your name: " + id_product.get());
       }
       int id_product_int = 0;
       try {
           id_product_int=Integer.parseInt(id_product.get().toString());
       }
       catch (Exception e )
       {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error Dialog");
           alert.setHeaderText("Please enter valid data in another attempt\n ");
           alert.setContentText(" error!");

           alert.showAndWait();
           return;
       }
       if(method.equals("GET"))
            obj_request.sendGET_ot_giv_info_of_product(id_product_int);
       else{
          StringBuffer response=obj_request.sendPOST_ot_giv_info_of_product(id_product_int);




           if(response.toString().equals("not found"))
           {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error Dialog");
               alert.setHeaderText("the product is not exist");
               alert.setContentText("pleas enther id exist");

               alert.showAndWait();
           }
           else {

               String result[] = response.toString().split(",");


               String id_respons = "";
               String name_respons = "";
               String amount_respons = "";
               String pricPerItem_respons = "";
               id_respons = result[0].split(":")[1];
               name_respons = result[1].split(":")[1].replace("\"", " ");
               amount_respons = result[2].split(":")[1].replace("\"", " ");
               pricPerItem_respons = result[3].split(":")[1].replace("}", " ").replace("\"", " ").replace("]", " ");
               System.out.println(id_respons + name_respons + amount_respons + pricPerItem_respons);


               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Information Dialog");
               alert.setHeaderText(null);
               alert.setContentText("id_product    :" + id_respons +
                       "\n name_product  :" + name_respons +
                       "\n   amount        :" + amount_respons +
                       "\n  pricPerItem in $  :" + pricPerItem_respons + "$" +
                       "\nSekel             :" + "20");

               alert.showAndWait();

           }
        }








   }

 @FXML
 public void handel_add_new_product(String  method ) {

        TextInputDialog dialog1 = new TextInputDialog("walter");
        dialog1.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog1.setHeaderText("");
        dialog1.setContentText("Please enter  id of producct :");
        Optional<String> id_product = dialog1.showAndWait();
        if (id_product.isPresent()){
            System.out.println("Your name: " + id_product.get());
        }
        int id_product_int = 0;
        try {
            id_product_int=Integer.parseInt(id_product.get().toString());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();
            return;
        }
        TextInputDialog dialog2 = new TextInputDialog("walter");
        dialog2.setTitle("Withdraw #Items of a Specific Product from Inventory");
        dialog2.setHeaderText("");
        dialog2.setContentText("Please enter  amount to dec in : ");
        Optional<String> aount_dec = dialog2.showAndWait();
        if (aount_dec.isPresent()){
            System.out.println("Your name: " + aount_dec.get());
        }
        int int_amount_pro=0;
        try {
            int_amount_pro=Integer.parseInt(aount_dec.get().toString());
        }
        catch (Exception e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Please enter valid data in another attempt\n ");
            alert.setContentText(" error!");

            alert.showAndWait();
            return;
        }

        StringBuffer respopons=new StringBuffer("");
        if(method.equals("GET"))
            respopons=obj_request.sendGET_to_inc_product(id_product_int,int_amount_pro);
        if(method.equals("POST"))
            respopons= obj_request.sendPOST_to_inc_product(id_product_int,int_amount_pro);

        if(respopons.toString().equals("cant do this operation")){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("the amount is not enough");
            alert.setContentText("Please enter a amount of the product");

            alert.showAndWait();

        }
        else if(respopons.toString().equals("not found "))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("There is no product with this id "+id_product_int+"it\n");
            alert.setContentText("Please enter a amount of the product");

            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(" update the product information successfully");
            alert.setContentText(" successfully  ");

            alert.showAndWait();
        }































//        TextInputDialog dialog = new TextInputDialog("walter");
//        dialog.setTitle("add  product  Inventory");
//        dialog.setHeaderText("");
//        dialog.setContentText("Please enter  name of pruduct:");
//        Optional<String> name = dialog.showAndWait();
//        if (name.isPresent()){
//            System.out.println("Your name: " + name.get());
//        }
//
//        TextInputDialog dialog1 = new TextInputDialog("walter");
//        dialog1.setTitle("add  product  Inventory");
//        dialog1.setHeaderText("");
//        dialog1.setContentText("Please enter  amount of pruduct:");
//        Optional<String> amount = dialog1.showAndWait();
//        if (amount.isPresent()){
//            System.out.println("Your name: " + amount.get());
//        }
//        int amount_int = 0;
//        try {
//            amount_int=Integer.parseInt(amount.get().toString());
//        }
//        catch (Exception e )
//        {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Dialog");
//            alert.setHeaderText("Please enter valid data in another attempt\n ");
//            alert.setContentText(" error!");
//
//            alert.showAndWait();
//            return;
//        }
//        TextInputDialog dialog2 = new TextInputDialog("walter");
//        dialog2.setTitle("add  product  Inventory");
//        dialog2.setHeaderText("");
//        dialog2.setContentText("Please enter  pric Per Item in $: ");
//        Optional<String> pricPerItem = dialog2.showAndWait();
//        if (pricPerItem.isPresent()){
//            System.out.println("Your name: " + pricPerItem.get());
//        }
//        double pricPerItem_double=0;
//        try {
//            pricPerItem_double=Double.parseDouble(pricPerItem.get().toString());
//        }
//        catch (Exception e )
//        {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Dialog");
//            alert.setHeaderText("Please enter valid data in another attempt\n ");
//            alert.setContentText(" error!");
//
//            alert.showAndWait();
//            return;
//        }
//        if(method.equals("GET"))
//         obj_request.send_GET_method_to_add_new_item(name.get().toString(),amount_int,pricPerItem_double);
//        if(method.equals("POST"))
//            obj_request.send_post_to_add_new_product(name.get().toString(),amount_int,pricPerItem_double);
//         dataTeable.getItems().clear();
//
//


    }

 @FXML
 public void logoutfunction()
 {
        page1.setVisible(false);
        login_panel.setVisible(true);

 }

 public void changepasswordfunction(String method)
 {

        try {
            TextInputDialog dialog = new TextInputDialog("walter");
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("old password");
            dialog.setContentText("Please enter your password:");


            Optional<String> lastpass = dialog.showAndWait();

                TextInputDialog dialog2 = new TextInputDialog("walter");
                dialog.setTitle("Text Input Dialog");
                dialog.setHeaderText("new password ");
                dialog.setContentText("Please enter new password :");

                Optional<String> newpass = dialog2.showAndWait();
                if (valedation(newpass.get())) {
                    System.out.println("changepasswordfunction done ");
                    ///////////////////////////---------------------------API
                    StringBuffer respons=new StringBuffer("");
                    if(method.equals("GET"))
                    {
                         respons=obj_request.send_get_method_changepasswor(Employee_ID.getText(),lastpass.get().toString(),newpass.get().toString());

                    }
                    if(method.equals("POST"))
                    {
                        respons=obj_request.send_post_method_changepasswor(Employee_ID.getText(),lastpass.get().toString(),newpass.get().toString());
                    }

                    System.out.println(respons.toString());
                    if(respons.toString().replace("\r","").replace("\n","").equals("Record updated successfully"))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("password change to "+" [ "+newpass.get()+"]");

                        alert.showAndWait();
                        pass=newpass.get();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Warning Dialog");
                        alert.setHeaderText("ERROR ");
                        alert.setContentText("last  password not correct !");

                        alert.showAndWait();
                    }

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("ERROR ");
                    alert.setContentText("password not correct !");

                    alert.showAndWait();
                    }
        }
        catch (Exception e )
        {
        }


    }
 @FXML
 public void edit_item_function()
 {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("edit info of prodact ");
        dialog.setHeaderText("edit info ");
        dialog.setContentText("Please enter id of prodact ot edit info :");


        Optional<String> result = dialog.showAndWait();
        if (!result.isEmpty()){
        }


    }
 @FXML
 public  void delet_item_function() {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("delete prodact ");
        dialog.setHeaderText("delet  ");
        dialog.setContentText("Please enter id of prodact ot delete  :");


        Optional<String> result = dialog.showAndWait();
        if (!result.isEmpty()){



        } }


}
