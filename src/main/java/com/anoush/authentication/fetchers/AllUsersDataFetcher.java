package com.anoush.authentication.fetchers;

import com.anoush.authentication.model.User;
import com.anoush.authentication.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** Created using IntelliJ IDEA User: Manoj Chaulagain Date: 2019-05-04 Time: 03:41 */
@Component
public class AllUsersDataFetcher implements DataFetcher<List<User>> {

  /*--------------------------------------------
  |             C O N S T A N T S             |
  ============================================*/

  /*--------------------------------------------
  |    I N S T A N C E   V A R I A B L E S    |
  ============================================*/

  private final UserRepository userRepository;

  /*--------------------------------------------
  |         C O N S T R U C T O R S           |
  ============================================*/
  @Autowired
  public AllUsersDataFetcher(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*--------------------------------------------
  |   P U B L I C    A P I    M E T H O D S   |
  ============================================*/
  @Override
  public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) {
    User user = dataFetchingEnvironment.getSource();
    List<User> users = new ArrayList<>();
    if (user != null) {
      users.add(userRepository.findByUsername(user.getUsername()).get());
    } else {
      users = userRepository.findAll();
    }
    return users;
  }

  /*--------------------------------------------
  |    N O N - P U B L I C    M E T H O D S   |
  ============================================*/

  /*--------------------------------------------
  |   A C C E S S O R S / M O D I F I E R S   |
  ============================================*/

}
