package org.discovery.services;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.discovery.DBFIELDS;
import org.discovery.entities.TelemetryEntity;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class TelemetryDatabaseService {
    private static String atlasUri = "mongodb+srv://" + DBFIELDS.DB_USER + ":" + DBFIELDS.DB_PSWD + "@discoverydb.c4g2ias.mongodb.net/?retryWrites=true&w=majority&appName=DiscoveryDB";
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    public static void startClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(atlasUri);
        }
        loadDatabase();
    }

    public static void closeClient() {
        mongoClient.close();
        mongoClient = null;
        database = null;
    }

    private static void loadDatabase() {
        if (database == null) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            database = mongoClient.getDatabase("discovery_db").withCodecRegistry(pojoCodecRegistry);
        }
    }

    public static MongoCollection<TelemetryEntity> getCollection() {
        return database.getCollection("user_telemetry", TelemetryEntity.class);
    }
}

