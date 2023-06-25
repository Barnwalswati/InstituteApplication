package com.instituteApplication.controller;

import com.instituteApplication.model.Batch;
import com.instituteApplication.service.BatchServiceImpl;
import com.instituteApplication.service.IBatchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batches")
public class BatchController {

    private final BatchServiceImpl batchService;

    @Autowired
    public BatchController(BatchServiceImpl batchService) {
        this.batchService = batchService;
    }

    @ApiOperation(value = "View Batch by using Id", response = Batch.class, nickname = "getBatch")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved batch"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Batch> getBatch(@PathVariable long id){
        return new ResponseEntity<>(batchService.getBatch(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Batch>> getAllBatches () {
        return new ResponseEntity<>(batchService.getAllBatches(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Batch> addBatch(@RequestBody Batch batch) {
        return new ResponseEntity<>(batchService.addBatch(batch), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Batch> updateBatch(@RequestBody Batch batch, @PathVariable long id){
        return new ResponseEntity<>(batchService.updateBatch(batch, id), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBatch(@PathVariable long id){
        return new ResponseEntity<>(batchService.deleteBatch(id), HttpStatus.OK);
    }
}
