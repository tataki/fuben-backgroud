package com.foo.service;

import com.foo.common.utils.PagedGridResult;
import com.foo.pojo.Project;
import com.foo.pojo.vo.ProjectVO;

import java.util.List;

public interface ProjectService {
    public Project getProjectInfo(int id);
    public void saveProject();
    public void updateProject(int id);
    public void deleteProject(int id);
    public List<Project> getProjectList();

    /**
     * 根据产品，获得产品信息及参与人员
     * @return
     */
    public PagedGridResult getAllProjectWithSubUser(Integer page, Integer pageSize);
}
