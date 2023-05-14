package ht.henrique.mazebank.service;

import ht.henrique.mazebank.model.database.User;

public interface MongoDbService {

    User getUserInDatabase(String userId);
}
