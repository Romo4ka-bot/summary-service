package ru.kpfu.itis.summaryservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.summaryservice.entity.CustomSequences;
import ru.kpfu.itis.summaryservice.service.CustomSequencesService;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Roman Leontev
 * 22:42 11.11.2021
 * student ITIS
 */

@Service
public class CustomSequencesServiceImpl implements CustomSequencesService {

    private MongoOperations mongo;

    @Autowired
    public CustomSequencesServiceImpl(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public Long getNextSequence(String seqName) {
        CustomSequences counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequences.class);
        return counter.getSeq();
    }
}
