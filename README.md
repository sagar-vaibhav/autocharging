# Autocharging
Use a smart plug to automatically turn the laptop's charging on and off.

Why do this?

To keep the laptop's battery cycling instead of staying charged at 100% for laptops that are usually kept connected to a
power source for long durations.

Prerequisites – 
1. Hardware Requirements: Smart plug, Android/iPhone, a computer to run the application with Java 8 or above.    
2. Install app the app Smart Life – Smart Living  
[Android](https://play.google.com/store/apps/details?id=com.tuya.smartlife)  
[IPhone](https://apps.apple.com/us/app/smart-life-smart-living/id1115101477)  
Note - You can also use any other app that is supported by IFTTT for controlling smart devices.
3. Add the smart plug in the application. Create an account, add the smart plug and preferably give the plug a custom 
name. More details can be found here under "How to Add a Device to the Smart Life App" section:  
[Smart Home Guide](https://www.smarthome.news/how-tos/other-systems/smart-life-app-review-and-guide)
4. Install IFTTT. Create an account.  
Note: IFTTT can also be configured through a web browser by visiting www.ifttt.com.

Setup Instructions -
1. Log into the IFTTT account.  
2. Search for Webhooks. Click on the tile for Webhooks in the search results. Click on the Connect button on the 
Webhooks screen.    
3. Go back to the search menu and search for Smart Life. Click on the tile for Smart Life in the search results. 
Click on the Connect button and proceed with the login process.  
4. Click on the Create button.
5. Click on If This button.
6. Search for Webhooks.
7. Click on Receive a web request.
8. Enter a relevant event name. I have used low_battery as the event name to designate the event that'll be fired when 
my laptop's battery falls below a certain threshold.
9. Click on Then That button.
10. Search for and select Smart Life.
