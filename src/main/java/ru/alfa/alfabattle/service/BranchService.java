package ru.alfa.alfabattle.service;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfa.alfabattle.model.Branch;
import ru.alfa.alfabattle.model.BranchDto;
import ru.alfa.alfabattle.repository.BranchRepository;

import java.util.List;

@Service
public class BranchService {
    private static final Integer RADIUS = 6371;

    @Autowired
    private BranchRepository branchRepository;

    public Branch findBranchById(Integer id)
    {
        return branchRepository.getById(id);
    }

    public BranchDto findBranchByDistance(Double pointLatitude, Double pointLongitude)
    {
        List<Branch> branches = branchRepository.findAll();
        BranchDto resultBranch = new BranchDto();

        double distance;
        int minDist = Integer.MAX_VALUE;

        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition pointA = new GlobalPosition(pointLatitude, pointLongitude, 0.0); // Point A

        for (Branch branch : branches) {

            GlobalPosition userPos = new GlobalPosition(branch.getLat(), branch.getLon(), 0.0); // Point B

            distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance();

            int roundedDistance = (int) Math.round(distance);

            if (roundedDistance < minDist) {
                minDist = roundedDistance;
                resultBranch.fillFromBranch(branch);
                resultBranch.setDistance(minDist);
            }
        }

        return resultBranch;
    }
}
