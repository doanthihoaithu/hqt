package vnu.edu.hoaithu.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-api")
                .useDefaultResponseMessages(false)
//                .additionalModels(resolver.resolve(ProductCreateDTO.class))
//                .additionalModels(resolver.resolve(ProductUpdateDTO.class))
//
//                .additionalModels(resolver.resolve(SubmissionUpdateDTO.class))
//                .additionalModels(resolver.resolve(SubmissionCreateDTO.class))
//
//                .additionalModels(resolver.resolve(CourseCreateDTO.class))
//                .additionalModels(resolver.resolve(CourseUpdateDTO.class))
//
//                .additionalModels(resolver.resolve(TestcaseCreateDTO.class))
//                .additionalModels(resolver.resolve(TestcaseUpdateDTO.class))
//
//                .additionalModels(resolver.resolve(LectureCreateDTO.class))
//
//                .additionalModels(resolver.resolve(ProblemCreateDTO.class))
//                .additionalModels(resolver.resolve(ProblemUpdateDTO.class))
//
//
//                .additionalModels(resolver.resolve(UserUpdateDTO.class))
//                .additionalModels(resolver.resolve(UserCreateDTO.class))
//
//                .additionalModels(resolver.resolve(NamedPayload.class))
//
//                .additionalModels(resolver.resolve(NameCreateDTO.class))
//                .additionalModels(resolver.resolve(NameUpdateDTO.class))

                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));


        // TODO: only support single mapping for entity
//                .directModelSubstitute(Course.class, CourseUpdateDTO.class)
//                .directModelSubstitute(Lecture.class, LectureCreateDTO.class)
//                .directModelSubstitute(Problem.class, ProblemUpdateDTO.class)
//                .directModelSubstitute(Student.class, StudentUpdateDTO.class)
//                .directModelSubstitute(Submission.class, SubmissionUpdateDTO.class)
//                .directModelSubstitute(Testcase.class, TestcaseUpdateDTO.class)
//                .directModelSubstitute(User.class, UserUpdateDTO.class)
//                .directModelSubstitute(ProblemCategory.class, NamedPayload.class)
//                .directModelSubstitute(Semester.class, NamedPayload.class);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!/auth).*")) // any paths except for /auth/**
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = { authorizationScope };
        return Collections.singletonList(new SecurityReference("apiKey", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("UETMan API")
                .description("The API Documentation of OracleNosql").termsOfServiceUrl("")
                .contact(new Contact("Doan Thi Hoai Thu", "", "hoaithuxt1998@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("0.0.1")
                .build();
    }
}
