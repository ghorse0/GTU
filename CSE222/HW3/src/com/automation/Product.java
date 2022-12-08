package com.automation;

public class Product {

    private type_t type;
    private color_t color;
    private String model;
    int stock;
    public enum type_t{
        chair, desk, table, cabinet, bookcase, error;

        public static type_t convertToType(int num){
            switch(num){
                case 0: return chair; 
                case 1: return desk; 
                case 2: return table;
                case 3: return cabinet;
                case 4: return bookcase;
                default: return error;
            }
        }
    } 
    public enum color_t{
        white, black, brown, red, green, yellow, orange, purple, pink, error;

        public static color_t convertToColor(int num){
            switch(num){
                case 0: return white;
                case 1: return black;
                case 2: return brown;
                case 3: return red;
                case 4: return green;
                case 5: return yellow;
                default: return error;
            }
        }

    }
   
    public Product(type_t type, color_t color, String model){
       this.type = type;
       this.color = color;
       this.model = model;
       stock = 1;
    }
    public String getType(){
       String str;
       switch(type){
        case chair:
           str = "Office chair";
           break;
        case desk:
           str = "Office desk";
           break;
        case table:
            str = "Meeting table";
            break;
        case cabinet:
            str = "Office cabinet";
            break;
        case bookcase:
            str = "Bookcase";
            break;
        default: str = "unknown";

       }
       return str;
    }

    public color_t getEnumColor(){
        return color;
    }

    public type_t getEnumType(){
        return type;
    }

    public String getColor(){
       String str;
       switch(color){
           case white:  str = "White"; break;
           case black:  str = "Black"; break;
           case brown:  str = "Brown"; break;
           case red:    str = "Red"; break;
           case green:  str = "Green"; break;
           case yellow: str = "Yellow"; break;
           case orange: str = "Orange"; break;
           case purple: str = "Purple"; break;
           case pink:   str = "Pink"; break;
           default: str = "unknown";
       }
       return str;
    }    

    public String getModel(){
       return model;
    }

    public int getStock(){
        return this.stock;
    }

    public boolean checkStock(){
        return this.stock != 0;
    }

    public void setStock(int no){
        this.stock = no;
    }

    public void increaseStock(int no){
        this.stock += no;
    }

    public void decreaseStock(int no){
        if((stock - no) >= 0)
            this.stock -= no;
    }


    @Override
    public String toString(){
        String str = getType() + " " + getColor() + " " + getModel();

        return str;
    }
}
