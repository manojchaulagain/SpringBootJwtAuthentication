package com.anoush.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Created using IntelliJ IDEA
 * User: Manoj Chaulagain
 * Date: 2019-06-15
 * Time: 00:12
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfession {

   /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

   /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

   /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

    @Id
    private String id;

    private String name;

   /*--------------------------------------------
    |   P U B L I C    A P I    M E T H O D S   |
    ============================================*/

   /*--------------------------------------------
    |    N O N - P U B L I C    M E T H O D S   |
    ============================================*/

   /*--------------------------------------------
    |   A C C E S S O R S / M O D I F I E R S   |
    ============================================*/

}
