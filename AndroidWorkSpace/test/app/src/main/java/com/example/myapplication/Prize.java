package com.example.myapplication;

public class Prize {
    private String name;
    private String date;
    private String id;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return name+","+date;
    }


    public Prize(String id,String name, String date){  //String name, String date
        super();
        this.name=name;
        this.date=date;
        this.id=id;
    }


}
