package org.discovery.dao;

import com.mongodb.client.MongoCollection;
import org.discovery.entities.TelemetryEntity;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class TelemetryDaoImpl implements TelemetryDao {
    private final MongoCollection<TelemetryEntity> collection;

    public TelemetryDaoImpl(MongoCollection<TelemetryEntity> collection) {
        this.collection = collection;
    }

    @Override
    public TelemetryEntity find(String id) {
        return collection.find(eq("sensorId", id)).projection(excludeId()).first();
    }

    @Override
    public List<TelemetryEntity> query() {
        ArrayList<TelemetryEntity> list = new ArrayList<>();
        collection.find().projection(excludeId()).forEach(list::add);
        return list;
    }

    @Override
    public void insert(TelemetryEntity entity) {
        collection.insertOne(entity);
    }

    @Override
    public void replace(TelemetryEntity entity) {
        collection.replaceOne(eq("sensorId", entity.getSensorId()), entity);
    }

    @Override
    public void remove(String id) {
        collection.deleteOne(eq("sensorId", id));
    }

    @Override
    public void updateLocalizacao(String id, float latitude, float longitude) {
        collection.updateOne(
                eq("sensorId", id),
                combine(set("localizacao.latitude", latitude),
                        set("localizacao.longitude", longitude)));
    }
}
