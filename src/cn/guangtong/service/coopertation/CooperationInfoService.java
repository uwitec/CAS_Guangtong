package cn.guangtong.service.coopertation;

import java.util.List;
import java.util.Map;

import cn.guangtong.controller.cooperation.CooperationPageBean;
import cn.guangtong.entity.cooperation.CooperationInfo;
import cn.guangtong.model.AdminCooperation;

public interface CooperationInfoService {

	/**
	 * 查询全部企业列表
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectAll();

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
	Map<String, Object> sCooperationInfoOne(String id);

	/**
	 * 查询所有加盟企业信息+分页+排序+模糊查询
	 */
	List<Map<String, Object>> sCooperationInfoA(CooperationPageBean cooperationPageBean);

	/**
	 * 批量冻结企业
	 */
	Integer uCooperationInfoFreezing(String type, String id);

	/**
	 * 修改企业信息
	 */
	Integer uCooperationInfo(CooperationInfo cooperationInfo);

	/**
	 * 增加一条企业信息
	 */
	Boolean iCooperationInfo(CooperationInfo cooperationInfo);

	List<String> findCoopIdByAid(int adminId);

	/**
	 * 根据id查询企业信息
	 */
	CooperationInfo getCooperationInfo(String id);

	/**
	 * 权限控制-查询所有企业以及可管理企业
	 * 
	 * @param adminId
	 * @return
	 */
	List<AdminCooperation> getCooperationInfoByAdminId(int adminId);

	/**
	 * 查询所有加盟企业信息+分页+排序+模糊查询(超级管理员)
	 */
	List<Map<String, Object>> sCooperationInfo(CooperationPageBean cooperationPageBean);

	/**
	 * 查询企业总数(超级管理员)
	 */
	Integer sCooperationCountS(CooperationPageBean coopertationPageBean);
}
