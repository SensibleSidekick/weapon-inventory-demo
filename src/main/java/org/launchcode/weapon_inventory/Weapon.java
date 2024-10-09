package org.launchcode.weapon_inventory;

public class Weapon {
    private Long id;
    private String name;
    private String type;
    private int damage;
    private String range;

    public Weapon() {
    }

    public Weapon(Long id, String name, String type, int damage, String range) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.range = range;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
