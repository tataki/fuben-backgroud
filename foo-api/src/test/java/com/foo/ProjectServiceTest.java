package com.foo;

import com.foo.service.ProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @Test
    public void getHello() throws Exception {
        projectService.saveProject();
    }
}
