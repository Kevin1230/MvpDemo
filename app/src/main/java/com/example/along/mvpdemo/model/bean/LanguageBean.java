package com.example.along.mvpdemo.model.bean;
/**
 * @描述 选择语言
 */
public class LanguageBean {

    /**
     * descript : 简体中文
     * lname : 简体中文
     * type : cn
     * typename : 简体中文
     */

    private String descript;
    private String lname;
    private String type;
    private String typename;
    private boolean isSelect;

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

}
