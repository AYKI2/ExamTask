package org.example.services;

import org.example.entity.Region;

import java.util.List;

public interface RegionService {
    String saveRegion(Region region);
    List<Region> getAllRegions();
    String update(Region newRegion, Long oldRegionId);
}
