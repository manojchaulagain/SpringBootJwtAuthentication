package com.anoush.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/** Created using IntelliJ IDEA User: Manoj Chaulagain Date: 2019-06-15 Time: 00:16 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private AgeRange ageRange;

  private LocationRange defaultLocationRange;

  private UserProfession userProfession;

  private List<ChatSubscription> chatSubscriptions = new ArrayList<>();

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
