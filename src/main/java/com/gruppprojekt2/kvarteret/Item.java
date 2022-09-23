package com.gruppprojekt2.kvarteret;

import javax.persistence.*;

@Entity
public class Item
{
    @Id // Primärnyckeln
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private int id;
    private String name;
    private int price;
    private String description;
    //@Column(columnDefinition = "varchar(64) default 'default.png'")
    private String img = "/image/ads/default.png";
    @ManyToOne
    private Siteuser siteuser;

    public Siteuser getSiteuser() {
        return siteuser;
    }

    public void setSiteuser(Siteuser siteuser) {
        this.siteuser = siteuser;
    }

    public Item() { }

    public Item(int id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
