package com.foo.service.impl;

import com.foo.common.enums.StatusEnum;
import com.foo.common.utils.FileUtils;
import com.foo.common.utils.PagedGridResult;
import com.foo.mapper.ProjectMapperCustom;
import com.foo.pojo.vo.ProjectVO;
import com.foo.service.ProjectService;
import com.foo.mapper.ProjectMapper;
import com.foo.pojo.Project;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectMapperCustom projectMapperCustom;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Project getProjectInfo(int id) {
        return projectMapper.selectByPrimaryKey(id);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveProject() {
        Project project = new Project();
        project.setTitle("jack");
        project.setDes("tom");
        project.setStatus(StatusEnum.progressing.type);
        project.setCreateTime(new Date());
        projectMapper.insert(project);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateProject(int id) {
        Project project = new Project();
        project.setId(id);
        project.setTitle("lucy");
        project.setDes("mary");
        projectMapper.updateByPrimaryKey(project);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteProject(int id) {
        projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Project> getProjectList() {
        return projectMapper.selectAll();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getAllProjectWithSubUser(Integer page,Integer pageSize) {
        /**
         * 使用mybatis-pagehelper 对sql就行拦截
         * 执行分页操作
         * page:第几页
         * pageSize:每页显示条数
         */
        PageHelper.startPage(page,pageSize);
        List<ProjectVO> list = projectMapperCustom.getAllProjectWithSubUser();
        list.forEach(o->{
            o.getSubUserList().forEach(v->{
                v.setFrontImage(FileUtils.getUrl("127.0.0.1","8088",v.getFrontImage()));});});
        return setterPagedGrid(list,page);
    }
    private PagedGridResult setterPagedGrid(List<?> list,Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
