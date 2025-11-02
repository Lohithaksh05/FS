/*
Write a SQL query using CTEs and conditional aggregation to list all movies that
were reviewed by their own director and at least one other reviewer. 

Display:
--------
    movie_name
    total_reviewers
    director_reviews

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+------------+-----------------+------------------+                                                                                                   
| movie_name | total_reviewers | director_reviews |                                                                                                   
+------------+-----------------+------------------+                                                                                                   
| Movie B    |               3 |                1 |                                                                                                   
+------------+-----------------+------------------+  


*/

WITH DirectorReviews AS (
    SELECT 
        m.name AS movie_name,
        COUNT(DISTINCT r.Artist_id) AS total_reviewers,
        SUM(CASE WHEN r.Artist_id = d.Artist_id THEN 1 ELSE 0 END) AS director_reviews
    FROM 
        Movies m
    JOIN 
        Reviews r ON m.id = r.Movie_id
    JOIN 
        Directions d ON m.id = d.Movie_id
    GROUP BY 
        m.id, m.name
)
SELECT 
    movie_name,
    total_reviewers,
    director_reviews
FROM 
    DirectorReviews 
WHERE
    director_reviews >= 1 AND total_reviewers > director_reviews;
    RankedMovies
WHERE
    rank <= 3
ORDER BY  
    decade, avg_rating DESC;
    