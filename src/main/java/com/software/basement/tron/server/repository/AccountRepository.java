package com.software.basement.tron.server.repository;

import com.software.basement.tron.server.account.Account;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findById(ObjectId _id);


}
