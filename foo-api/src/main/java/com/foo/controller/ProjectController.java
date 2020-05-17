package com.foo.controller;

import com.foo.common.utils.JSONResult;
import com.foo.common.utils.JsonUtils;
import com.foo.common.utils.PagedGridResult;
import com.foo.pojo.Project;
import com.foo.pojo.vo.ProjectVO;
import com.foo.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController extends BaseController {
    private final Logger logger = Logger.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;
    @GetMapping("/getProject")
    public Object getProject(int id){
        return projectService.getProjectInfo(id);
    }
    @PostMapping("/saveProject")
    public Object saveProject(){
        projectService.saveProject();
        return "ok";
    }
    @PostMapping("/updateProject")
    public Object updateProject(int id){
        projectService.updateProject(id);
        return "ok";
    }
    @DeleteMapping("/deleteProject")
    public Object deleteProject(int id){
        projectService.deleteProject(id);
        return "ok";
    }
    @GetMapping("/getProjectList")
    public Object getProjectList(){
        return projectService.getProjectList();
    }
    @ApiOperation(value = "获得所有项目详情WITH分页", notes = "获得所有项目详情包括参加人员", httpMethod = "GET")
    @GetMapping("/getAllProjectDetail")
    public JSONResult getProjectDetail(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize){
        if(page==null){
            page = 1;
        }
        if(pageSize==null){
            pageSize=PAGE_SIZE;
        }
        PagedGridResult gird = projectService.getAllProjectWithSubUser(page,pageSize);
        return JSONResult.ok(gird);
    }
}
