package org.discovery.entities;

import java.util.Objects;

public class LocalizacaoEntity {
    private double latitude;
    private double longitude;

    public LocalizacaoEntity() {
    }

    //region construtor principal, getters, setters, toString, equals, hashCode aqui
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "\n\tlatitude: " + latitude + "\n\tlongitude: " + longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizacaoEntity that = (LocalizacaoEntity) o;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    public LocalizacaoEntity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    //endregion
}
