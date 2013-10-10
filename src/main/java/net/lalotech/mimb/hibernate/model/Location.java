/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lalotech.mimb.hibernate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Eduardo
 */
@Entity
@Table(name = "location")
public class Location implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude; 

    public Location() {
    }

    public Location(Double latitude, Double longitude) {        
        this.latitude = latitude;
        this.longitude = longitude;
    }  
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }    
}
