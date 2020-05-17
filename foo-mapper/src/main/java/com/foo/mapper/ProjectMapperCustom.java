package com.foo.mapper;

import com.foo.my.mapper.MyMapper;
import com.foo.pojo.Project;
import com.foo.pojo.vo.ProjectVO;

import java.util.List;

public interface ProjectMapperCustom {
    public List<ProjectVO> getAllProjectWithSubUser();
}