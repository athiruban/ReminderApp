

<Author> <Athiruban SM>

<Version> <1.0>

This purpose of this application is to remind users on various events....

The events which are entered thru the GUI should exist only in the future. In coming version we will enable storing
important events that occurred in the past also.

This can be used on a variety of situations like Birthday Alarm (which occurs every year), Booster Pack Expiry 
date reminder alarm(which occurs every 28 days), etc and almost all type of scheduling can be done using this application.

A light weight and UI rich GUI should be developed in the second version.

This application will be hosted in the cloud to track all the events the user performs so that at any point
of time we can find the activity the user did. This application will store all the Twitter, Facebook (tweets/posts)
in a log file. 

Developer Notes
+++++++++++++++

As for this application is concerned we need to store & retrieve several event 
objects (as mentioned above). To do this we need a central object to persist
all the information we need.

Designing event object hierarchy
--------------------------------
This needs to be explored more.

Designing a single object Vs object hierarchy
---------------------------------------------
If we go by this approach we end up with Bean theory.
Yes bean stores the core application data and have business methods floating around it.

Now I understood the purpose of Bean! I can now confidently say "Objects which are central to any
application can be modeled using Bean concept".

Can we use SPRING framework to design our application functionality?
-------------------------------------------------------------------- 