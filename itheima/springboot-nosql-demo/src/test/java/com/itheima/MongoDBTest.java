package com.itheima;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongoDBTest {

    private static final Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void testMongoDbFindAll() {
    }
}
