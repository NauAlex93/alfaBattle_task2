package ru.alfa.alfabattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfa.alfabattle.model.Branch;
import ru.alfa.alfabattle.model.BranchDto;
import ru.alfa.alfabattle.model.NoBranchResponse;
import ru.alfa.alfabattle.service.BranchService;

@RestController
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable Integer id){
        Branch branch = branchService.findBranchById(id);

        if (branch == null) {
            NoBranchResponse response = new NoBranchResponse();
            response.setStatus("branch not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getBranchByDistance(@RequestParam Double lat, @RequestParam Double lon){
        BranchDto branch = branchService.findBranchByDistance(lat, lon);

        return new ResponseEntity<>(branch, HttpStatus.OK);
    }
}
