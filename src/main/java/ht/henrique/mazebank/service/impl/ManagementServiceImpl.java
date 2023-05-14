package ht.henrique.mazebank.service.impl;

import ht.henrique.mazebank.exception.DatabaseException;
import ht.henrique.mazebank.model.BaseResponse;
import ht.henrique.mazebank.model.create.CreateRequest;
import ht.henrique.mazebank.model.create.CreateResponse;
import ht.henrique.mazebank.model.database.User;
import ht.henrique.mazebank.model.fetch.FetchUserResponse;
import ht.henrique.mazebank.model.mapper.UserMapper;
import ht.henrique.mazebank.service.ManagementService;
import ht.henrique.mazebank.service.MongoDbService;
import ht.henrique.mazebank.util.type.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MongoDbService mongoDbService;

    @Override
    public ResponseEntity<BaseResponse> createUser(CreateRequest createRequest) throws DatabaseException {

        if (findUserByKey(createRequest.getUseremail()) != null){
            log.info("User already used with key");
            throw new DatabaseException(ReturnCode.ALREADY_USER, "User already used");
        }

        User user = userMapper.createToUser(createRequest);

        try {
        }catch (DataIntegrityViolationException exception){
            log.info("Required field not informed");
            throw new DatabaseException(ReturnCode.INVALID_PARAMETERS, "Required field not informed");
        }catch (Exception exception){
            throw new DatabaseException(ReturnCode.INTERNAL_SERVER_ERROR, "Database unavailable");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(201000, new CreateResponse("Success")));
    }

    @Override
    public ResponseEntity<BaseResponse> getUser(String userKey) throws DatabaseException {

        User user = findUserByKey(userKey);

        if (user == null){
            log.info("User not found");
            throw new DatabaseException(ReturnCode.NOT_FOUND, "User not found");
        }

        FetchUserResponse fetchUser = userMapper.userToFetchUser(user);

        return ResponseEntity.ok(new BaseResponse(200000, fetchUser));
    }

    @Override
    public ResponseEntity<BaseResponse> getUserMongo(String userId) {
        User result = mongoDbService.getUserInDatabase(userId);
        log.info(result.toString());
        FetchUserResponse fetchUser = new FetchUserResponse();
        fetchUser.setUserName(result.getUser_name());
        return ResponseEntity.ok(new BaseResponse(ReturnCode.SUCCESS.getReturnCode(), fetchUser));
    }

    private User findUserByKey(String userKey) throws DatabaseException {
        List<User> listUser;
        User user = null;

        log.info("Searching for user with key: " + userKey);
        listUser = null;

        if (listUser.size() == 1){
            user = listUser.get(0);
        }else {
            if (listUser.size() > 1){
                throw new DatabaseException(ReturnCode.MORE_THAN_ONE_USER, "More than one user");
            }
        }
        return user;
    }
}
