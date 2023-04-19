//package application;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Restock {
    private static final int RESTOCK_LEVEL = 10;
    
    public static ArrayList<product> getProductsToRestock(/*Connection con*/) throws Exception {
        ArrayList<product> productsToRestock = new ArrayList<>();
        String query = "SELECT serial_num, product_name, stock, price, cost, supplier_name, category " +
                       "FROM products " +
                       "WHERE stock <= " + RESTOCK_LEVEL;
        
        try (Statement stmt = MakeConnection.con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int serial_num = rs.getInt("serial_num");
                String product_name = rs.getString("product_name");
                int stock = rs.getInt("stock");
                double price = rs.getDouble("price");
                double cost = rs.getDouble("cost");
                String supplier_name = rs.getString("supplier_name");
                String category = rs.getString("category");
                
                product product = new product(serial_num, product_name, stock, price, cost, supplier_name, category);
                productsToRestock.add(product);
            }
        }
        
        return productsToRestock;
    }
}