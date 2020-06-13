package com.emard.api;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.emard.api");

        noClasses()
            .that()
                .resideInAnyPackage("com.emard.api.service..")
            .or()
                .resideInAnyPackage("com.emard.api.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.emard.api.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
