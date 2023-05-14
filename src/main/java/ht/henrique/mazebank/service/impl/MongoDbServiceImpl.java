package ht.henrique.mazebank.service.impl;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ht.henrique.mazebank.model.database.User;
import ht.henrique.mazebank.service.MongoDbService;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;

public class MongoDbServiceImpl implements MongoDbService {

    @Autowired
    private String mongoUri;

    CodecProvider pojoCodecProvider;
    CodecRegistry pojoCodecRegistry;

    private void init(){
        pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    }


    public User getUserInDatabase(String userId){
        User user;
        try (MongoClient mongoClient = MongoClients.create(mongoUri)) {
            MongoDatabase database = mongoClient.getDatabase("MazebankDatabase").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<User> collection = database.getCollection("users", User.class);
             user = collection.find(eq("_userEmail", userId)).first();
        }
        return user;
    }
}
