package com.backend.db.mapper;

import com.backend.db.bean.Tache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IssueMapper extends BaseMapper<Tache> {

}
