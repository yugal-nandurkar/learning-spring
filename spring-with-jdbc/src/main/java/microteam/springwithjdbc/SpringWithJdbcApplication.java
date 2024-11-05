package microteam.springwithjdbc;

import microteam.springwithjdbc.entity.Person;
import microteam.springwithjdbc.jdbc.PersonJDBCDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Timestamp;

@SpringBootApplication
public class SpringWithJdbcApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SpringWithJdbcApplication.class);

    @Autowired
    PersonJDBCDAO dao;

    public static void main(String[] args) {
        SpringApplication.run(SpringWithJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All Users -> {}",dao.findAll());
        logger.info("User id 10001 -> {}",dao.findById(10001));
        logger.info("Deleting id 10003 -> No. of rows deleted {}",dao.deleteById(10003));
        logger.info("Inserting 10004 -> {}",
                dao.insert(new Person(10004, "John", "Berlin", new Timestamp(System.currentTimeMillis()))));
        logger.info("Update 10002 -> {}",
                dao.insert(new Person(10005, "Dom", "Denver", new Timestamp(System.currentTimeMillis()))));
    }
}
