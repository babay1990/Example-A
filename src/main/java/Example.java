import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example {

    public static void main(String[] args) {
        ArrayList<Order> orders;
        try{
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(Example.getJsonFile());
            orders = parseLines(array);

            System.out.println("[");
            for(int i = 0; i < orders.size(); i++){
               ArrayList<Order> list = new ArrayList<>();

                for(int j = 0; j < orders.size(); j++){
                    if(orders.get(j).getOrder_id() == i){
                        list.add(orders.get(j));
                    }
                }

                if(!list.isEmpty()){
                    Collections.sort(list);

                    if(!list.get(0).getStatus().equals("CANCEL")){
                        System.out.println("    {");
                        System.out.println("        \"id\": " + list.get(0).getOrder_id() + ",");
                        System.out.println("        \"items\": [");
                        System.out.println("            {");
                        System.out.println("                \"count\": " + (list.get(0).getCount() - list.get(0).getReturn_count()) + ",");
                        System.out.println("                \"id\": " + list.get(0).getItem_id());
                        System.out.println("            }");
                        System.out.println("        ]");
                        System.out.println("    },");
                    }
                }
            }
            System.out.println("]");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static String getJsonFile(){

        StringBuilder builder = new StringBuilder();
        try{
            List<String> lines = Files.readAllLines(Paths.get("file.json"));
            lines.forEach(line -> builder.append(line));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static ArrayList<Order> parseLines(JSONArray linesArray){

        ArrayList<Order> orders = new ArrayList<>();

        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
              orders.add(new Order(
                    ((Long) lineJsonObject.get("event_id")).intValue(),
                    ((Long) lineJsonObject.get("order_id")).intValue(),
                    ((Long) lineJsonObject.get("item_id")).intValue(),
                    ((Long) lineJsonObject.get("count")).intValue(),
                    ((Long) lineJsonObject.get("return_count")).intValue(),
                    (String) lineJsonObject.get("status")
            ));
        });

        return orders;
    }
}
