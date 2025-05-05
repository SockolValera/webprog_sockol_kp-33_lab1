package com.Lab1;

import java.util.Objects;

public class Visitor {
    private int id;
    private String name;
    private String phone;

    public Visitor(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // Бізнес логіка: перевірка валідності номера
    public boolean isPhoneValid() {
        return phone.matches("\\+?\\d{10,13}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor)) return false;
        Visitor visitor = (Visitor) o;
        return id == visitor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
