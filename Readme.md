
HOME ASSIGNMENT - CLIENT ANDROID APP

Language Used - JAVA 


SETUP INSTRUCTIONS:

1. Firstly you need to have Android Studio (Bumblebee or later) Version 2021.1.1 Patch 3.
2. In Android Studio you need to go to File Option and then on Open Option.
3. Then select the project
4. After opening the project it will take some time to set up
5. And then try to run it.

IMPORTANT : IF you run this app in physical device you need to insert your ipv4 address and port number in baseURl.
->retrofits->RetrofitService.class->retrofit->baseURL.baseUrl("http://10.0.2.2:8080") -> baseUrl("YOUR IPV4 Address")

To get IP address - type in cmd this command - "ipconfig"


DEPENDENCY MANAGEMENT :

1. I used these dependency in this project i.e Retrofit, converter-gson library
2. implementation 'com.squareup.retrofit2:retrofit:2.9.0'
3. implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   
   
HOW TO TRY :

1. There are two activities in android app.
2. First is Main Activity and other is Login Activity;
3. You need to enter your email in edit text and then request otp
4. After that it will show you to enter otp.
5. you can copy otp from the terminal of OTP API SERVER SIDE
6. and then paste it and verify it.
7. If Successfull it will show you the login activity.
