package trivia;
import org.javalite.activejdbc.Base;
import trivia.User;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest{
    @Before
    public void before(){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/trivia_test", "root", "c4j0i20g");
        System.out.println("UserTest setup");
        Base.openTransaction();
    }

    @After
    public void after(){
        System.out.println("UserTest tearDown");
        Base.rollbackTransaction();
        Base.close();
    }

     @Test
     public void validateUniquenessOfUsernames(){
         User user = new User();
         user.set("username", "anakin");
         user.set("password", "messi");
         user.saveIt();

         User user2 = new User();
         user2.set("username", "anakin2");

         assertEquals(user2.isValid(), false);
     }
     

   @Test
    public void validateExistenceOfUsername(){
        User user = new User();
        user.set("username", "");
        user.set("password", "1234");

        assertEquals(user.isValid(), false);
    }

}
