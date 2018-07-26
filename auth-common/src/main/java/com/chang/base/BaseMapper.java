
package com.chang.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基础mapper
 * @author Mr.Chang
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
  
