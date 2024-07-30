package io.jmix.tests.ui.initializer

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "main.datasource.url=jdbc:tc:postgresql:16.3:///postgres-test-db",
                "main.datasource.username=test",
                "main.datasource.password=pass",
                "jmix.data.dbmsType=postgres",
                "main.liquibase.change-log=com/company/samplesales/liquibase/changelog.xml",
                "main.liquibase.drop-first=true"
        ).applyTo(applicationContext.getEnvironment())
    }
}
