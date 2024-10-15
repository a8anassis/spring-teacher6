package gr.aueb.cf.teacherapp.service;

import gr.aueb.cf.teacherapp.model.static_data.Region;

import java.util.List;

public interface IRegionService {
    List<Region> findAllRegions();
}
