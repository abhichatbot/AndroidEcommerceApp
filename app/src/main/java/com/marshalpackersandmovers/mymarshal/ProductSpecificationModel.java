package com.marshalpackersandmovers.mymarshal;

public class ProductSpecificationModel {

    public static final int SPECIFICATION_TITLE =0;
    public static final int SPECIFICATION_BODY = 1;

    private int type;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    /////specification title
    private String title;

    public ProductSpecificationModel(int type, String title) {
        this.type = type;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /////specification title

    /////specification body
    private String featureName;
    private String featureVale;

    public ProductSpecificationModel(int type, String featureName, String featureVale) {
        this.type = type;
        this.featureName = featureName;
        this.featureVale = featureVale;
    }
    public String getFeatureName() {
        return featureName;
    }
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }
    public String getFeatureVale() {
        return featureVale;
    }
    public void setFeatureVale(String featureVale) {
        this.featureVale = featureVale;
    }

    /////specification body


}
