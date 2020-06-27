package ru.alfa.alfabattle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfa.alfabattle.model.Branch;
import ru.alfa.alfabattle.repository.BranchRepository;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public Branch findBranchById(Integer id)
    {
        return branchRepository.getById(id);
    }
}
