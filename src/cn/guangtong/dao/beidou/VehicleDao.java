package cn.guangtong.dao.beidou;

import cn.guangtong.entity.beidou.Vehicle;



public interface VehicleDao {
  
    int insert(Vehicle record);

    int update(Vehicle record);
    
    Vehicle getVehiclebySimNo(String simNo);
}