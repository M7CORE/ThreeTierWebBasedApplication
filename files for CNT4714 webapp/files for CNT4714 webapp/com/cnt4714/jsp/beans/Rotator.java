// Rotator.java
// A JavaBean that rotates advertisements.
package com.cnt4714.jsp.beans;

public class Rotator 
{
   private String images[] = { "images/image1.jpg",
      "images/image2.jpg", "images/image3.jpg",
      "images/image4.jpg", "images/image5.jpg" };
      
   private String links[] = {
      "http://www.eddymerckx.be",
      "http://www.competitivecyclist.com",
      "http://www.bianchi-usa.com",
      "http://www.colnago.it",
		"http://www.cometkartsales.com" };
         
   private int selectedIndex = 0;

   // returns image file name for current ad  
   public String getImage()
   {
      return images[ selectedIndex ];
   } // end method getImage

   // returns the URL for ad's corresponding Web site
   public String getLink()
   {
      return links[ selectedIndex ];
   } // end method getLink

   // update selectedIndex so next calls to getImage and 
   // getLink return a different advertisement
   public void nextPic()
   {
      selectedIndex = ( selectedIndex + 1 ) % images.length;
   } // end method nextAd
} // end class Rotator


 