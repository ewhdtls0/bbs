package my.bbs.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "BBS API 명세서",
                description = "Board RESTful API 명세서",
                version = "v1.0.0")
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customTestOpenAPI() {
        String[] paths = {"/boards/**", "/members/**"};

        return GroupedOpenApi.builder()
                .group("Board, Member 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}
