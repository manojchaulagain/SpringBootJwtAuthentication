package com.anoush.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.*;

/**
 * Created using IntelliJ IDEA
 * User: Manoj Chaulagain
 * Date: 2019-06-14
 * Time: 20:28
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChat {

   /*--------------------------------------------
    |             C O N S T A N T S             |
    ============================================*/

   /*--------------------------------------------
    |    I N S T A N C E   V A R I A B L E S    |
    ============================================*/

    @Id
    private String id;
    private String name;
    private Set<ChatUser> users = new HashSet<>();
    private List<ChatMessage> chatMessages = new ArrayList<>();
    private Date createdDate;
    private Location initiatedLocation;
    private ChatUser chatCreator;
    private ChatVisibilityType chatVisibilityType;
    private AgeRange ageRange;
    private ChatCategory chatCategory;
    private long locationRange;

   /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

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
