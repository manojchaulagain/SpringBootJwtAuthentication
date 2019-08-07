package com.anoush.authentication.utilities;

import com.anoush.authentication.fetchers.AllUsersDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created using IntelliJ IDEA
 * User: Manoj Chaulagain
 * Date: 2019-05-04
 * Time: 03:36
 */
@Component
@RefreshScope
public class GraphQlUtility {

   /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

    /*--------------------------------------------
     |    I N S T A N C E   V A R I A B L E S    |
     ============================================*/
    @Value("classpath:models.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private AllUsersDataFetcher allUsersDataFetcher;

   /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

    @Autowired
    public GraphQlUtility(AllUsersDataFetcher allUsersDataFetcher) {
        this.allUsersDataFetcher = allUsersDataFetcher;
    }

   /*--------------------------------------------
    |   P U B L I C    A P I    M E T H O D S   |
    ============================================*/

   @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
        return GraphQL.newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("users", allUsersDataFetcher))
                .build();
    }
   /*--------------------------------------------
    |    N O N - P U B L I C    M E T H O D S   |
    ============================================*/

   /*--------------------------------------------
    |   A C C E S S O R S / M O D I F I E R S   |
    ============================================*/

}
