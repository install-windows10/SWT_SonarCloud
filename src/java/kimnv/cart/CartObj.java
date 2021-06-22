package kimnv.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class CartObj implements Serializable{
    private Map<String, Integer> items;//Tên sách là key, số lượng là value

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addBookToCart(String title){
        //1.Kiểm tra giỏ có item chưa
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //2.Kiểm tra món đồ có chưa
        int quantity = 1;
        if(this.items.containsKey(title)){
            quantity = this.items.get(title) + 1;
        }
        //3.Update giỏ
        this.items.put(title,quantity);
    }
    
    public void removeBookFromCart(String title){
        //1.Kiểm tra trong giỏ có nào ko
        if(this.items == null){
            return;
        }
        //2.Kiểm tra món đồ có trong giỏ ko -> Xoá
        if(this.items.containsKey(title)){
            this.items.remove(title);
        //Nếu xoá hết giỏ -> Gán Null cho giỏ
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
