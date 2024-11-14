package microteam.repository;

import microteam.services.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository//Step 7 Executing Query using JDBC
public class JDBCCourse {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static String INSERT_QUERY =
            """
            insert into course (id, name, description, author)
            values(?, ?, ?, ?);
            """;

    private static String DELETE_QUERY =
            """
            delete from course\s
            where id = ?
           \s""";

    private static String SELECT_QUERY =
            """
            select * from course\s
            where id = ?
           \s""";

    public void insert(Course course){
        jdbcTemplate.update(
                INSERT_QUERY, course.getId(), course.getName(),
                course.getDescription(), course.getAuthor());
    }
    public void deleteByID(Integer id){
        jdbcTemplate.update(
                DELETE_QUERY,id);
    }
    public Course selectByID(Integer id){
        //BeanPropertyRowMapper Parameter is a Row Mapper from Result Set to Bean
        return jdbcTemplate.queryForObject(SELECT_QUERY,
                new BeanPropertyRowMapper<>(Course.class),id);
    }
}
