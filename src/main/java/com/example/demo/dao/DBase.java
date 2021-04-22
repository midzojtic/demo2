package com.example.demo.dao;

import com.example.demo.model.FlightModel;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Component
public class DBase {

    private static DB db = DBMaker.fileDB("baze.db").fileMmapEnable().transactionEnable().closeOnJvmShutdown().make();
    private ConcurrentMap<String, List<FlightModel>> map = db.hashMap("mapDB", Serializer.STRING, Serializer.JAVA)
            .expireMaxSize(1000).expireStoreSize(1024 * 1024).createOrOpen();

    public List<FlightModel> getList(String key) {
        if (db.isClosed())
            db = DBMaker.fileDB("baze.db").fileMmapEnable().transactionEnable().closeOnJvmShutdown().make();
        map = db.hashMap("mapDB", Serializer.STRING, Serializer.JAVA)
                .expireMaxSize(1000).expireStoreSize(1024 * 1024).createOrOpen();
        return map.get(key);
    }

    public void saveData(String key, List<FlightModel> list) {

        if (db.isClosed())
            db = DBMaker.fileDB("baze.db").fileMmapEnable().transactionEnable().closeOnJvmShutdown().make();

        map = db.hashMap("mapDB", Serializer.STRING, Serializer.JAVA)
                .expireMaxSize(1000).expireStoreSize(1024 * 1024).createOrOpen();

        map.put(key, list);

        db.commit();
        db.close();

    }

}
