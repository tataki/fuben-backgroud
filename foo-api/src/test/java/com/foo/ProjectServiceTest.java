package com.foo;

import com.foo.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class ProjectServiceTest extends BaseTest {
    @Autowired
    private ProjectService projectService;

    @Test
    public void getHello() throws Exception {
        projectService.saveProject();
    }
}
