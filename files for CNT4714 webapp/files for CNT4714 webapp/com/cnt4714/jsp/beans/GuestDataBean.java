// GuestDataBean.java
// Class GuestDataBean makes a database connection and supports 
// inserting and retrieving data from the database.  Database created with guestbookscript.sql
package com.cnt4714.jsp.beans;

import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import java.util.ArrayList;


public class GuestDataBean 
{
   private CachedRowSet rowSet;
  
   // construct TitlesBean object 
   public GuestDataBean() throws Exception
   {
      // load the MySQL driver
      Class.forName( "com.mysql.jdbc.Driver" );
      
      // specify properties of CachedRowSet
      rowSet = RowSetProvider.newFactory().createCachedRowSet();  
      rowSet.setUrl( "jdbc:mysql://localhost:3310/guestbook" ); 
      rowSet.setUsername( "root" );
      rowSet.setPassword( "root" );

	  // obtain list of titles
      rowSet.setCommand( 
         "SELECT firstName, lastName, email FROM guests" );
      rowSet.execute();
   } // end GuestDataBean constructor

   // return an ArrayList of GuestBeans
   public ArrayList< GuestBean > getGuestList() throws SQLException
   {
      ArrayList< GuestBean > guestList = new ArrayList< GuestBean >();

      rowSet.beforeFirst(); // move cursor before the first row

      // get row data
      while ( rowSet.next() ) 
      {
         GuestBean guest = new GuestBean();

         guest.setFirstName( rowSet.getString( 1 ) );
         guest.setLastName( rowSet.getString( 2 ) );
         guest.setEmail( rowSet.getString( 3 ) );

         guestList.add( guest ); 
      } // end while

      return guestList;
   } // end method getGuestList
   
   // insert a guest in guestbook database
   public void addGuest( GuestBean guest ) throws SQLException
   {
      rowSet.moveToInsertRow(); // move cursor to the insert row

      // update the three columns of the insert row 
      rowSet.updateString( 1, guest.getFirstName() ); 
      rowSet.updateString( 2, guest.getLastName() ); 
      rowSet.updateString( 3, guest.getEmail() ); 
      rowSet.insertRow(); // insert row to rowSet
      rowSet.moveToCurrentRow(); // move cursor to the current row
      rowSet.acceptChanges(); // propagate changes to database
   } // end method addGuest
} // end class GuestDataBean


 