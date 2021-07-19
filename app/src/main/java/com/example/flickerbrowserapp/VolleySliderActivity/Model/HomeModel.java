package com.example.flickerbrowserapp.VolleySliderActivity.Model;

public class HomeModel {

    //Slider Image
    private String SliderImg;

    //Cat data
    private String ID;
    private String TITLE;
    private int IMG_SRC;


    //SubCat data
    private String SubCatID;
    private String SubCatTITLE;
    private String SubCatIMG;
    private String SubCatIMG_URL;
    private String SubCatCONTENT;

    public String getSliderImg() {
        return SliderImg;
    }

    public void setSliderImg(String sliderImg) {
        SliderImg = sliderImg;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getIMG_SRC() {
        return IMG_SRC;
    }

    public void setIMG_SRC(int IMG_SRC) {
        this.IMG_SRC = IMG_SRC;
    }

    public String getSubCatID() {
        return SubCatID;
    }

    public void setSubCatID(String subCatID) {
        SubCatID = subCatID;
    }

    public String getSubCatTITLE() {
        return SubCatTITLE;
    }

    public void setSubCatTITLE(String subCatTITLE) {
        SubCatTITLE = subCatTITLE;
    }

    public String getSubCatIMG() {
        return SubCatIMG;
    }

    public void setSubCatIMG(String subCatIMG) {
        SubCatIMG = subCatIMG;
    }

    public String getSubCatIMG_URL() {
        return SubCatIMG_URL;
    }

    public void setSubCatIMG_URL(String subCatIMG_URL) {
        SubCatIMG_URL = subCatIMG_URL;
    }

    public String getSubCatCONTENT() {
        return SubCatCONTENT;
    }

    public void setSubCatCONTENT(String subCatCONTENT) {
        SubCatCONTENT = subCatCONTENT;
    }
}
