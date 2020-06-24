package com.marshalpackersandmovers.mymarshal;

import java.util.List;

public class HomePageModel {
    public static final int BANNER_SLIDER =0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;
    private String backgroundColor;

    ////////Banner Slider
    private List<SliderModel> sliderModelList;
    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }
    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    ////////Banner Slider

    ///////strip ad layout
    private String  resource;


    public HomePageModel(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    ///////strip ad layout

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    private List<QuotationModel> viewAllProductList;

    ///////horizontal product layout
    private String title;

    public HomePageModel(int type, String title, String backgroundColor ,List<HorizontalProductScrollModel> horizontalProductScrollModelList, List<QuotationModel> viewAllProductList) {
        this.type = type;
        this.backgroundColor = backgroundColor;
        this.title = title;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
        this.viewAllProductList = viewAllProductList;
    }

    public List<QuotationModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<QuotationModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }

    ///////horizontal product layout


    ////// grid product layout

    public HomePageModel(int type, String title, String backgroundColor , List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.type = type;
        this.backgroundColor = backgroundColor;
        this.title = title;
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    ///////grid product layout
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalProductScrollModel> getHorizontalProductScrollModelList() {
        return horizontalProductScrollModelList;
    }

    public void setHorizontalProductScrollModelList(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }


}
