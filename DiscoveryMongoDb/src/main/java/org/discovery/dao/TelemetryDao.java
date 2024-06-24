package org.discovery.dao;

import org.discovery.entities.TelemetryEntity;

import java.util.List;

public interface TelemetryDao {
    List<TelemetryEntity> query();
    TelemetryEntity find(String id);
    void insert(TelemetryEntity entity);
    void replace(TelemetryEntity entity);
    void updateLocalizacao(String id, float latitude, float longitude);
    void remove(String id);
}
