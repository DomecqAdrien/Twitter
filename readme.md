POST /user/register/ {"username":"..."} -> inscription
GET /user/login/{username} -> login
GET /user/{id} -> getUser
GET /user/follow/{id} -> follow user

POST /post  -> new post
GET /historique/{id} -> posts by author
GET /timeline -> posts by user followed
GET /post/{id} -> one post
GET /fav/{idPost} -> fav a post
GET /repost/{idPost} -> retweet a post