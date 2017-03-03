<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${pkgName}.dao.${ClassName}DAO">
	<resultMap type="${ClassName}" id="${ClassName}Map">
		<!-- mapping -->
		<#if Autos??>
		<#list Autos as item>
		<#assign info=item?split("#")>
		<result column="${info[8]}" property="${info[0]}"/>		
		</#list>
		</#if>
	</resultMap>

	<select id="findAll" resultMap="${ClassName}Map">
		select * from ${table}
	</select>
	
	<select id="get" resultMap="${ClassName}Map">
		select * from ${table} where ${oidColumn}=${r"#{"}${Oid}}
	</select>
	
	<insert id="save" useGeneratedKeys="true" keyColumn="${oidColumn}" keyProperty="${Oid}">
		insert into ${table}
			(<#if Autos??><#list Autos as item><#assign info=item?split("#")>${info[8]}<#if item_has_next>,</#if></#list></#if>) 
		values
			(<#if Autos??><#list Autos as item><#assign info=item?split("#")>${r"#{"}${info[0]}}<#if item_has_next>,</#if></#list></#if>)
	</insert>
	
	<insert id="update">
		update ${table} 
		set 
		<#if Autos??><#list Autos as item><#assign info=item?split("#")>${info[8]}=${r"#{"}${info[0]}}<#if item_has_next>,</#if></#list></#if>
		where ${oidColumn}=${r"#{"}${Oid}}
	</insert>
	
	
	<delete id="delete">
		delete from ${table} where ${oidColumn}=${r"#{"}${Oid}}
	</delete>
	
	<delete id="deleteByIds">
		delete from ${table} 
		 WHERE ${oidColumn} in
		  <foreach item="item" index="index" collection="array"
		      open="(" separator="," close=")">
		       ${r"#{"}item}
		  </foreach>
	</delete>
	
	<select id="pagination" resultMap="${ClassName}Map">
		${r"${"}autoSQL}
	</select>

	<select id="findMaxRow" resultType="int">
		select count(*) from ${table}
	</select>
	
</mapper>