package com.shabs.zUnimplemented.design;

public class PageReadTime {

  /**
   * find the top 10 read pages, given clickstream as input
   *
   *


   Y extending X

   super return X
   overiding return X, Y

   Find the "top read" pages in the last one hour.
   input -> ( pagename, count )

   stream

   key - pagename A, B, C
   info - A - 2 ... A, starttime, endtime

   (timestamp, url)
   (timestamp, url/end)


   serverTimestamp -- {clientTimestamp, pageName, widgetName, eventName (startreading, endreading)}

   map ->
   ((pageName, user), (event -> clientTimestamp))
   pageName, {start -> clientTimestamp}
   pageName, {start -> clientTimestamp}
   pageName, {end -> clientTimestamp}


   reduce ->
   key=A, {{end -> clientTimestamp}, {start -> clientTimestamp}, ...}

   readTime += if end + , start -

   ((pageName, user), readTime)

   map ->
   (pageName, readTime)

   reduce
   (pageName, sum(readTime))


   sort on readTime


   map -> (readTime, pageName)

   reduce -> (5, {A, B})
   reduce -> (9, {X, J})

   A - 2, 4 = 6
   B - 1 1 1 1 1 = 5
   C - 9 = 9


   {A, readTime}
   {B, readTime}
   {C, readTime}



   sort


   1,3,5,10

   13-6 =7



   elapsedTime


   (starttime, url)
   ...

   (A, endtime)



   Top - C, A, B
   */
}
