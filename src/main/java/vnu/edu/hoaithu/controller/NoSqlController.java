package vnu.edu.hoaithu.controller;

import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vnu.edu.hoaithu.model.SelectExample;

@RestController
@RequestMapping("/nosql")
public class NoSqlController {


    @Autowired
    private SelectExample selectExample;
    @PostMapping("/select")
    public ResponseEntity<Long> getSelectTime (String query) {
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";
        KVStore kvStore = KVStoreFactory.getStore(new KVStoreConfig(storeName, hostName
                + ":" + hostPort));
        selectExample.init(kvStore);
        long time = selectExample.call(query);
        return new ResponseEntity<Long>(Long.valueOf(time), HttpStatus.OK);

    }
}
