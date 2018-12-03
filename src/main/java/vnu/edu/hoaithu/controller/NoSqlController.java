package vnu.edu.hoaithu.controller;

import io.swagger.annotations.ApiOperation;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vnu.edu.hoaithu.model.SelectExample;
import vnu.edu.hoaithu.payload.SelectResponse;

@RestController
@RequestMapping("/nosql")
public class NoSqlController {


    @Autowired
    private SelectExample selectExample;
    @PostMapping("/select")
    @ApiOperation("Nhập nosql query")
    public ResponseEntity<SelectResponse> getSelectTime (@RequestParam("query") String query) {
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";
        KVStore kvStore = KVStoreFactory.getStore(new KVStoreConfig(storeName, hostName
                + ":" + hostPort));
        selectExample.init(kvStore);
        SelectResponse response = selectExample.call(query);
        return new ResponseEntity<SelectResponse>(response, HttpStatus.OK);

    }
}
