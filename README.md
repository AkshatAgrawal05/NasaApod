# NasaApod

Criteria Done : 
1. Given: The NASA APOD API is up (working) AND the phone is connected to the internet When:
The user arrives at the APOD page for the first time today Then: The page should display the
image of Astronomy Picture of the Day along with the title and explanation, for that day
2. Given: The user has already seen the APOD page once AND the phone is not connected to
the internet When: The user arrives at the APOD page on the same day Then: The page
should display the image of Astronomy Picture of the Day along with the title and explanation,
for that day
3. Given: The user has not seen the APOD page today AND the phone is not connected to the
internet When: The user arrives at the APOD page Then: The page should display an error
"We are not connected to the internet, showing you the last image we have." AND The page
should display the image of Astronomy Picture of the Day along with the title and explanation,
that was last seen by the user
4. Given: The NASA APOD API is up (working) AND the phone is connected to the internet When:
The APOD image loads fully on the screen Then: The user should be able to see the complete
image without distortion or clipping

Dependencies used :
 * ViewModel
 * LiveData
 * Hilt (for dependency injection)
 * Kotlin Coroutines
 * Retrofit
 * Room
 * Glide
 
 Instruction to run clone repo in Android Studio and run
 
 Improvement Areas:
 1. NetworkResourceBound can be used to show loading, error and success data as stated in google doc using Flow.
 2. Image is persisted using Glide cache strategy, DB blob can also be used for image storage
 3. Navigation component can also be used for Fragment navigation for future app improvements
 4. Data can be stored according to current days and fetched wrt to current day
 5. In api either video or image comes so mediatype check is added if media type is image then only we'll show image
 
