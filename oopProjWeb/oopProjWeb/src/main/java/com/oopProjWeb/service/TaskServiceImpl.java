package com.oopProjWeb.service;

import com.oopProjWeb.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Value("${rest.server.url}")
    private String serverUrl;

    private final static String RESOURCE_PATH = "api/tasks";

    private RestTemplate restTemplate;

    @Autowired
    public TaskServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Task> getTask(Integer taskId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(taskId))
                .build()
                .toUri();
        logger.info("REQUEST -> GET {}", url);
        return Optional.ofNullable(restTemplate.getForObject(url, Task.class));
    }

    @Override
    public Task setTask(Task task) {
        if (task.getTaskId() != null) {
            String url = getUriStringComponent(task.getTaskId());
            logger.info("REQUEST -> PUT {}", url);
            restTemplate.put(url, task);
            return task;
        } else {
            HttpEntity<Task> request = new HttpEntity<>(task);
            String url = getUriStringComponent();
            logger.info("REQUEST -> POST {}", url);
            URI location = restTemplate.postForLocation(url, request);
            logger.info("REQUEST(location)-> GET{}", location);
            return restTemplate.getForObject(location, Task.class);
        }
    }

    @Override
    public void deleteTask(Integer taskId) {
        URI url = ServiceUtil.getUriComponent(serverUrl, getResourcePath(taskId))
                .build()
                .toUri();
        logger.info("REQUEST -> DELETE {}", url);
        restTemplate.delete(url);
    }

    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return null;
    }

    private RestResponsePage<Task> getPage(URI uri, RestTemplate restTemplate) {
        return ServiceUtil.getPage(uri, restTemplate, new ParameterizedTypeReference<RestResponsePage<Task>>() {
        });
    }

    private String getResourcePath() {
        return RESOURCE_PATH;
    }

    private String getResourcePath(Integer id) {
        return RESOURCE_PATH + "/" + id;
    }

    private String getUriStringComponent() {
        return serverUrl + getResourcePath();
    }

    private String getUriStringComponent(Integer id) {
        return serverUrl + getResourcePath(id);
    }

}