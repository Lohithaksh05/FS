/*Mr Suresh is working with the plain text P, a list of words w[], 
He is converting P into C [the cipher text], C is valid cipher of P, 
if the following rules are followed:
	- The cipher-text C is a string ends with '$' character.
	- Every word, w[i] in w[], should be a substring of C, and 
	the substring should have $ at the end of it.

Your task is to help Mr Suresh to find the shortest Cipher C,  
and return its length.

Input Format:
-------------
Single line of space separated words, w[].

Output Format:
--------------
Print an integer result, the length of the shortest cipher.


Sample Input-1:
---------------
kmit it ngit

Sample Output-1:
----------------
10

Explanation:
------------
A valid cipher C is "kmit$ngit$".
w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
w[1] = "it", the substring of C, and the '$' is the end character after "it"
w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


Sample Input-2:
---------------
ace

Sample Output-2:
----------------
4

Explanation:
------------
A valid cipher C is "ace$".
w[0] = "ace", the substring of C, and the '$' is the end character after "ace"

 */


 