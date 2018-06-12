package com.alexb.springjdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

}

@Component
@RequiredArgsConstructor
@Slf4j
class QueryEmployeesAndDeptsCount implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        StringUtils.line();

        jdbcTemplate.query("SELECT d.DEPTNO, d.DNAME, e.ENAME, e.EMPNO FROM DEPT d LEFT JOIN EMP e ON d.DEPTNO = e.DEPTNO\n" +
                "ORDER BY d.DEPTNO;", new ResultSetExtractor<Collection<Department>>() {

            Department currentDepartment = null;
            @Override
            public Collection<Department> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()){
                    if (currentDepartment == null || currentDepartment.getDeptno() != rs.getInt("DEPTNO")){

                    }
                }
            }
        });

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Department {
        Set<Employee> employees = new HashSet<>();
        private int deptno;
        private String dname;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Employee {
        private int empno;
        private String name;
    }

}

@Slf4j
abstract class StringUtils {
    public static void line() {
        log.info("==================================");
    }
}

