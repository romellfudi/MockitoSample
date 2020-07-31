package fudi.freddy.mokitosample.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

    private String company, name, email, cell, application, position,comments;
    private boolean fl_app
            ,fl_cot;

    Contact(){

    }

    public Contact(String company, String name, String email, String cell, String application,
                   String position, boolean fl_app, boolean fl_cot, String comments) {
        this.company = company;
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.application = application;
        this.position = position;
        this.fl_app = fl_app;
        this.fl_cot = fl_cot;
        this.comments = comments;
    }

    public Contact(String company, String name, String email, String cell, String position) {
        this.company = company;
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isFl_app() {
        return fl_app;
    }

    public void setFl_app(boolean fl_app) {
        this.fl_app = fl_app;
    }

    public boolean isFl_cot() {
        return fl_cot;
    }

    public void setFl_cot(boolean fl_cot) {
        this.fl_cot = fl_cot;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

//    public void save() {
//        DatabaseHelper.getInstance().createContact(this);
//    }
//
//
//    public void update() {
//        DatabaseHelper.getInstance().updateContact(this);
//    }
}
