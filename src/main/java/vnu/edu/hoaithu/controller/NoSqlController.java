package vnu.edu.hoaithu.controller;

import io.swagger.annotations.ApiOperation;
import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vnu.edu.hoaithu.model.MyCreateTable;
import vnu.edu.hoaithu.model.SelectExample;
import vnu.edu.hoaithu.payload.SelectResponse;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/nosql")
public class NoSqlController {

    @Autowired
    private SelectExample selectExample;
    @Autowired
    private MyCreateTable createExample;
    @PostMapping("/select")
    @ApiOperation("Nhập nosql query")
    public ResponseEntity<SelectResponse> getSelectTime (@RequestParam("query") String query) {
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";
        KVStoreConfig kvconfig = new KVStoreConfig(storeName, hostName
                + ":" + hostPort);
//        kvconfig.setRequestTimeout(3600000, TimeUnit.MILLISECONDS);
//        kvconfig.setSocketReadTimeout(3600000,TimeUnit.MILLISECONDS);
        KVStore kvStore = KVStoreFactory.getStore(kvconfig.setSocketReadTimeout(3600000,TimeUnit.MILLISECONDS));
        selectExample.init(kvStore);
        SelectResponse response = selectExample.call(query);
        return new ResponseEntity<SelectResponse>(response, HttpStatus.OK);

    }

//    @PostMapping ("/import")
//    @ApiOperation("Nhập số lượng row muốn import")
//    public ResponseEntity<Long> getImportTime (@RequestParam("begin_id") int beginId, @RequestParam("end_id") int endId) {
//        String storeName = "kvstore";
//        String hostName = "localhost";
//        String hostPort = "5000";
//        KVStoreConfig kvconfig = new KVStoreConfig(storeName, hostName
//                + ":" + hostPort);
////        kvconfig.setSocketReadTimeout(3600000,TimeUnit.MILLISECONDS);
//        KVStore kvStore = KVStoreFactory.getStore(kvconfig);
//        createExample.init(kvStore);
//        long time = createExample.call(beginId,endId);
//        return new ResponseEntity<Long> (new Long(time), HttpStatus.OK);
//
//    }
}
