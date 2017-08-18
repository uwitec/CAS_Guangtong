package cn.guangtong.dao.cooperation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.guangtong.controller.cooperation.CooperationPageBean;
import cn.guangtong.entity.cooperation.CooperationInfo;
import cn.guangtong.model.AdminCooperation;

public interface CooperationInfoDao {

	/**
	 * 查询加盟企业总数
	 */
	Integer sCooperationInfoCount();

	/**
	 * 查询企业总数
	 */
	Integer sCooperationCount(CooperationPageBean coopertationPageBean);

	/**
	 * 查询一条企业记录
	 */
	Map<String, Object> sCooperationInfoOne(@Param("id") String id);

	/**
	 * 查询所有企业信息+分页+模糊查询
	 */
	List<Map<String, Object>> sCooperationInfoA(CooperationPageBean coopertationPageBean);

	/**
	 * 批量冻结企业
	 */
	Integer uCooperationInfoFreezing(@Param("type") String type, @Param("id") String id);

	/**
	 * 修改企业信息
	 */
	Integer uCooperationInfo(CooperationInfo cooperationInfo);

	/**
	 * 增加一条企业信息
	 */
	Integer iCooperationInfo(CooperationInfo cooperationInfo);

	/**
	 * 查询所有企业
	 * 
	 * @return
	 */
	List<CooperationInfo> findCooperationInfo();

	/**
	 * 查询全部企业列表(超级管理员)
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectAll();

	/**
	 * 根据用户id查询可见企业(普通用户)
	 * 
	 * @param adminId
	 * @return
	 */
	List<Map<String, Object>>  findCooperationInfoByAdminId(int adminId);

	/**
	 * 根据企业Id集合查询 超管
	 * 
	 * @param coopList
	 * @return
	 */
	List<String> getCnameById(@Param("coopList") List<String> coopList);
	
	/**
	 * 根据企业Id集合查询 普通
	 * 
	 * @param coopList
	 * @return
	 */
	List<String> getCnameByIdByOther(@Param("coopList") List<String> coopList, @Param("adminId") String adminId);

	List<String> findCoopIdByAid(int adminId);

	/**
	 * 根据id查询企业信息
	 */
	CooperationInfo getCooperationInfoById(String id);

	/**
	 * 权限控制-查询所有企业以及可管理企业
	 * 
	 * @param adminId
	 * @return
	 */
	List<AdminCooperation> getCooperationInfoByAdminId(int adminId);

	/**
	 * 查询企业总数(超级管理员)
	 */
	Integer sCooperationCountS(CooperationPageBean coopertationPageBean);

	/**
	 * 查询所有企业信息+分页+模糊查询(超级管理员)
	 */
	List<Map<String, Object>> sCooperationInfo(CooperationPageBean coopertationPageBean);

}
