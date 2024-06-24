package org.discovery.repository;

import org.discovery.dao.TelemetryDao;
import org.discovery.entities.TelemetryEntity;

import java.util.List;

public class TelemetryRepository {
    private final TelemetryDao dao;

    public TelemetryRepository(TelemetryDao dao) {
        this.dao = dao;
    }

    public List<TelemetryEntity> query() {
        return dao.query();
    }

    public void updateLocalizacao(String id, float latitude, float longitude) {
        dao.updateLocalizacao(id, latitude, longitude);
    }

    public TelemetryEntity find(String id) {
        return dao.find(id);
    }

    public void insert(TelemetryEntity entity) {
        dao.insert(entity);
    }

    public void replace(TelemetryEntity entity) {
        dao.replace(entity);
    }

    public void remove(String id) {
        dao.remove(id);
    }
}
