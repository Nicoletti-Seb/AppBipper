package app.mbds.fr.unice.appbipper.entity;

/**
 * Created by 53js-Seb on 21/10/2016.
 */

public class Person {

    private String nom;
    private String prenom;
    private String sexe;
    private String telephone;
    private String email;
    private String createdBy;
    private String password;

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getSexe() {return sexe;}

    public void setSexe(String sexe) {this.sexe = sexe;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
