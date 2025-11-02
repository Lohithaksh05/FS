/*
Write a SQL query using multi-level CTEs to determine which decades produced the 
most top-rated movies (average rating >= 8).

Display:
--------
    decade
    high_rating_count
    popularity_rank

Show the top 3 decades with the most highly rated movies.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+-------------------+-----------------+                                                                                                      
| decade | high_rating_count | popularity_rank |                                                                                                      
+--------+-------------------+-----------------+                                                                                                      
|   1990 |                 2 |               1 |                                                                                                      
|   1970 |                 1 |               2 |                                                                                                      
|   2000 |                 1 |               2 |                                                                                                      
|   2010 |                 1 |               2 |                                                                                                      
|   1980 |                 1 |               2 |                                                                                                      
|   2020 |                 1 |               2 |                                                                                                      
+--------+-------------------+-----------------+ 


*/
