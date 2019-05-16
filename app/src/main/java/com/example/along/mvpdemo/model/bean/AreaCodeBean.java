package com.example.along.mvpdemo.model.bean;
/**
 * @描述 国别码
 */
public class AreaCodeBean {

    /**
     * country_id : 214
     * country : 中国
     * en_country : China
     * region : 86
     * area : 亚洲
     * created_at : 2018-11-13 17:16:18
     * updated_at : 2018-11-13 17:16:18
     * tw_country : 中國
     */

    private int country_id;
    private String country;
    private String en_country;
    private String region;
    private String area;
    private String created_at;
    private String updated_at;
    private String tw_country;

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEn_country() {
        return en_country;
    }

    public void setEn_country(String en_country) {
        this.en_country = en_country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTw_country() {
        return tw_country;
    }

    public void setTw_country(String tw_country) {
        this.tw_country = tw_country;
    }

    @Override
    public String toString() {
        return "AreaCodeBean{" +
                "country_id=" + country_id +
                ", country='" + country + '\'' +
                ", en_country='" + en_country + '\'' +
                ", region='" + region + '\'' +
                ", area='" + area + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", tw_country='" + tw_country + '\'' +
                '}';
    }
}
