package org.ppke.itk.groupmealplanner.configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation. Bean ;
import org.springframework.context.annotation. Configuration ;
@Configuration
public class GroupMealPlannerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info( new Info()
                .title( "Champions League Bets" )
                .version( "0.0.1-SNAPSHOT" )
                .description( "ChampionsLeagueBets App" )
                .termsOfService( "http://swagger.io/terms/" )
                .license( new License().name( "Apache 2.0" ).url( "http://springdoc.org" )));
    }
}
