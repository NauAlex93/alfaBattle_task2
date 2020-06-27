package ru.alfa.alfabattle.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchDto {
    private Integer id;
    private String title;
    private Double lon;
    private Double lat;
    private String address;
    private Integer distance;

    public void fillFromBranch(Branch branch) {
        this.id = branch.getId();
        this.title = branch.getTitle();
        this.lon = branch.getLon();
        this.lat = branch.getLat();
        this.address = branch.getAddress();
    }
}
