package com.gruntik.microservices.dao;

import com.gruntik.microservices.Application;
import com.gruntik.microservices.model.Request;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class RequestDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RequestDao requestDao;

    @Test
    void insert() {
        Request request = new Request(UUID.randomUUID(), UUID.randomUUID(), "2500", 4, LocalDateTime.now());
        requestDao.insert(request);

        System.out.println(request);

        Map<String, Object> objectMap = jdbcTemplate.queryForMap("select * from request where id = ?", request.getId());
        System.out.println(objectMap);

        assertEquals(request.getId(), objectMap.get("id"));

    }

    @Test
    void delete() {
        Request request = new Request(UUID.randomUUID(), UUID.randomUUID(), "2500", 4, LocalDateTime.now());
        requestDao.insert(request);

        requestDao.delete(request.getId());

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(" select * from request where id = ?", request.getId());
        System.out.println(mapList.size());

        Integer number = jdbcTemplate.queryForObject(" select count(*) from request where id = ?", new Object[]{request.getId()}, Integer.class);

        System.out.println(number);

        assertEquals(0, number);
    }
}