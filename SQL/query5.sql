/*
Write a SQL query using CTEs to find directors whose average movie ratings are 
higher than the global average rating of all movies combined.

Display:
--------
    director_name
    avg_director_rating
    global_avg

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+---------------+---------------------+------------+                                                                                                  
| director_name | avg_director_rating | global_avg |                                                                                                  
+---------------+---------------------+------------+                                                                                                  
| John Doe      |                9.00 |       8.38 |                                                                                                  
| Alice Johnson |                9.00 |       8.38 |                                                                                                  
| Emma Stone    |                9.00 |       8.38 |                                                                                                  
| David Brown   |                8.50 |       8.38 |                                                                                                  
| Olivia Taylor |                8.50 |       8.38 |                                                                                                  
+---------------+---------------------+------------+ 


*/


