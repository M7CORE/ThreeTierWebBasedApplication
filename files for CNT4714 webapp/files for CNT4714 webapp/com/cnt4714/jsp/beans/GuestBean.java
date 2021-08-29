//  GuestBean.java
// JavaBean to store data for a guest in the guest book.

package com.cnt4714.jsp.beans;

public class GuestBean 
{
   private String firstName;
   private String lastName;
   private String email;

   // set the guest's first name
   public void setFirstName( String name )
   {
      firstName = name;  
   } // end method setFirstName
   
   // get the guest's first name
   public String getFirstName()
   {
      return firstName;  
   } // end method getFirstName

   // set the guest's last name
   public void setLastName( String name )
   {
      lastName = name;  
   } // end method setLastName

   // get the guest's last name
   public String getLastName()
   {
      return lastName;  
   } // end method getLastName

   // set the guest's email address
   public void setEmail( String address )
   {
      email = address;
   } // end method setEmail

   // get the guest's email address
   public String getEmail()
   {
      return email;  
   } // end method getEmail
} // end class GuestBean


 