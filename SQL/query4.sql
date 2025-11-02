/*
Write a SQL query using CTEs and ranking functions to identify the most active 
reviewer in each decade. 

Display:
--------
    decade
    reviewer_name
    review_count

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+---------------+--------------+                                                                                                             
| decade | reviewer_name | review_count |                                                                                                             
+--------+---------------+--------------+                                                                                                             
|   1970 | Bob Smith     |            1 |                                                                                                             
|   1980 | Jane Doe      |            2 |                                                                                                             
|   1990 | Alice Johnson |            1 |                                                                                                             
|   1990 | Olivia Taylor |            1 |                                                                                                             
|   1990 | Bob Smith     |            1 |                                                                                                             
|   2000 | David Brown   |            1 |                                                                                                             
|   2000 | John Doe      |            1 |                                                                                                             
|   2000 | Chris Evans   |            1 |                                                                                                             
|   2010 | Olivia Taylor |            1 |                                                                                                             
|   2010 | Chris Evans   |            1 |                                                                                                             
|   2020 | Emma Stone    |            1 |                                                                                                             
|   2020 | Alice Johnson |            1 |                                                                                                             
+--------+---------------+--------------+


*/