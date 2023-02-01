package org.example.services;

import org.example.entity.Region;
import org.example.repository.RegionRepository;
import org.example.repository.RegionRepositoryImpl;

import java.util.List;

public class RegionServiceImpl implements RegionService{
    private RegionRepository regionRepository = new RegionRepositoryImpl();
    public String saveRegion(Region region) {
        regionRepository.saveRegion(region);
        return "Successfully saved!";
    }

    public List<Region> getAllRegions() {
        return regionRepository.getAllRegions();
    }

    public String update(Region newRegion, Long oldRegionId) {
        regionRepository.update(newRegion, oldRegionId);
        return "Successfully updated!";
    }
}
