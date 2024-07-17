package com.doubao.sysblogs.common.utils;


import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author: doubao
 * @time: 2024/07/15
 * @description:分页工具类
 */
@Data
public class PageUtils<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	private Long totalCount;
	/**
	 * 每页记录数
	 */
	private Long pageSize;
	/**
	 * 总页数
	 */
	private Long totalPage;
	/**
	 * 当前页数
	 */
	private Long currPage;
	/**
	 * 列表数据
	 */
	private List<E> list;


	/**
	 *
	 * @param pageSize  每页记录数
	 * @param currPage  当前页数
	 * @param totalList 总记录列表
	 */
	public PageUtils(Long pageSize, Long currPage, List<E> totalList) {
		this.totalCount =  Long.valueOf(totalList.size());
		this.pageSize = pageSize;
		this.totalPage = (long)Math.ceil((double) totalCount / pageSize);
		this.currPage = currPage;
		this.list = this.currPage >= 1 ? totalList.stream().skip((long) (currPage - 1) * pageSize).limit(pageSize).collect(Collectors.toList()) : new ArrayList<>();
	}
}
