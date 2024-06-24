package org.discovery.entities;

import java.util.Objects;

public class TelemetryEntity {
    private String sensorId;
    private float temperatura;
    private float frequenciaCardiaca;
    private int passos;
    private float umidade;
    private LocalizacaoEntity localizacaoEntity;

    public TelemetryEntity() {
    }

    //region construtor principal, getters, setters, toString, equals, hashCode aqui
    public TelemetryEntity(String sensorId, float temperatura, float frequenciaCardiaca, int passos, float umidade, LocalizacaoEntity localizacaoEntity) {
        this.sensorId = sensorId;
        this.temperatura = temperatura;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.passos = passos;
        this.umidade = umidade;
        this.localizacaoEntity = localizacaoEntity;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(float frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public int getPassos() {
        return passos;
    }

    public void setPassos(int passos) {
        this.passos = passos;
    }

    public float getUmidade() {
        return umidade;
    }

    public void setUmidade(float umidade) {
        this.umidade = umidade;
    }

    public LocalizacaoEntity getLocalizacao() {
        return localizacaoEntity;
    }

    public void setLocalizacao(LocalizacaoEntity localizacaoEntity) {
        this.localizacaoEntity = localizacaoEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelemetryEntity that = (TelemetryEntity) o;
        return Float.compare(temperatura, that.temperatura) == 0 && Float.compare(frequenciaCardiaca, that.frequenciaCardiaca) == 0 && passos == that.passos && Float.compare(umidade, that.umidade) == 0 && Objects.equals(sensorId, that.sensorId) && Objects.equals(localizacaoEntity, that.localizacaoEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, temperatura, frequenciaCardiaca, passos, umidade, localizacaoEntity);
    }

    @Override
    public String toString() {
        return " - sensorId: " + sensorId + "\n\ttemperatura: " + temperatura + "\n\tfrequenciaCardiaca: " + frequenciaCardiaca + "\n\tpassos: " + passos + "\n\tumidade: " + umidade + localizacaoEntity+"\n";
    }
    //endregion
}
