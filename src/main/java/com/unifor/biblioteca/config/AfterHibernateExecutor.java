package com.unifor.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AfterHibernateExecutor {

    @Autowired
    private JdbcTemplate jdbc;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterHibernate() throws Exception {

        runSqlFile("after_jpa_1.sql");
        runSqlFile("after_jpa_2.sql");

        System.out.println("Todos os arquivos SQL foram executados.");
    }

    private void runSqlFile(String filename) throws Exception {
        Resource resource = new ClassPathResource(filename);

        if (!resource.exists()) {
            System.out.println("Arquivo não encontrado: " + filename);
            return;
        }

        String sql = new String(resource.getInputStream().readAllBytes());
        jdbc.execute(sql);

        System.out.println("Arquivo executado: " + filename);
    }
}
