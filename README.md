# uOttaHack4 Submission - Con-jeu-guez

<img src="https://challengepost-s3-challengepost.netdna-ssl.com/photos/production/software_photos/001/382/008/datas/original.png">

## Inspiration

COVID-19 has hurt many parts of society and seriously impacted the way we go to school and learn. As a French Immersion student who grew up in an incredibly Anglophone community, I was upset to learn that many French Immersion students in my hometown have been forced to drop out since the program is not available online there.

The idea behind the app is to offer a way to practice some French skills from home or remotely, similarly to the drills we did in my French classes growing up.

## What it does

This app is a game to help students learn French verb conjugations. Users can select one of four verb tenses (Présent, Passé composé, Imparfait, and Futur) and a chosen time limit (1, 2, or 5 minutes).

During the game, the app provides verbs and pronouns to use for conjugation (for example, conjugate "demander" for "il"). The user must input the correct answer to earn points. My idea is that this could occur in a group setting, with the student that gets the most points in a time period winning.

## How I built it

The game is developed on Android Studio in Java. It uses a series of .CSV files I prepared for each verb tense that include the verb and its conjugations for each pronoun, pulling verbs and choosing pronouns pseudo-randomly from the files.

## Challenges I ran into

There were a variety of challenges throughout the project. 

The first roadblock was crashing issues when selecting the verb from the CSV file. A built-in function I was going to use was constantly returning an empty string array, rather than correctly splitting each row. I ended up having to create my own CSV reader class that was able to fix this issue.

Another issue was with the timer. When a user would leave the game (i.e return to the main screen), the timer would continue to run and the puzzle would remain active until they returned. I learned about the lifecycle of an Activity, and was able to override this behaviour with the onPause() and onResume() methods that I had never really used before.

## Accomplishments that I'm proud of

This is my first Hackathon, so I'm very proud that I was able to come up with and create an idea, then fully implement it. I'm normally not a super creative person, but thinking about the challenges that face French Immersion students in COVID-19 created this idea that I was passionate about. 
I'm very proud with the result, especially considering it took about 24 hours to produce. With further refinement, I'm sure this app can continue to improve and be something useful for some people.

## What I learned

I learned more about mobile development for Android, such as how to use a Handler to run commands in the future and to enqueue actions to be performed on other threads. This helps to make mobile applications more versatile than they normally would be. This could be used to introduce a timeout ability, for example.

I learned about the Android OS as well, and how we can store data in the system. I used an agile method for storing user's attempts and correct answers that would make it very easy to expand the game in the future.

## What's next for Con-jeu-guez!

The game can continue to be expanded by adding new verb tenses and verbs. 

I currently intend on releasing this to Google Play in the near future and can then share it with my network, some of whom are studying to become French teachers.