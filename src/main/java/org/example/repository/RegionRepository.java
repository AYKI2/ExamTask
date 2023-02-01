package org.example.repository;

import org.example.entity.Region;

import java.util.List;

public interface RegionRepository{
    void saveRegion(Region region);
    List<Region> getAllRegions();
    void update(Region newRegion, Long oldRegionId);
}
