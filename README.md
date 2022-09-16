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
  
![1](https://user-images.githubusercontent.com/490696/190654403-904fcb9e-042d-4706-a705-9201b3d3c16e.jpg)
  

2. Search for Webhooks. Click on the tile for Webhooks in the search results. Click on the Connect button on the 
Webhooks screen.

![2](https://user-images.githubusercontent.com/490696/190654548-0f1402f8-378a-412c-afea-30052330e7f6.JPG)


3. Go back to the search menu and search for Smart Life. Click on the tile for Smart Life in the search results. 
Click on the Connect button and proceed with the login process.

![3](https://user-images.githubusercontent.com/490696/190654584-4febd8c2-33c9-4458-a015-a10076c203a0.JPG)


4. Click on the Create button.

![4](https://user-images.githubusercontent.com/490696/190654624-3954dbdd-3f07-48c6-804a-5f9bdc1ba7e4.JPG)


5. Click on If This button.

![5](https://user-images.githubusercontent.com/490696/190654676-ccdcb89b-ae2f-414c-9241-96356aab68da.JPG)


6. Search for Webhooks.

![6](https://user-images.githubusercontent.com/490696/190654722-07187a29-8c26-42e8-a1a9-234261f2731f.JPG)


7. Click on Receive a web request.

![7](https://user-images.githubusercontent.com/490696/190654752-837dd377-8844-473e-a911-eda9a97e40c7.JPG)


8. Enter a relevant event name. I have used low_battery as the event name to designate the event that'll be fired when 
my laptop's battery falls below a certain threshold.

![8](https://user-images.githubusercontent.com/490696/190654793-b1a6f236-500b-46e8-b322-379d36586ba7.JPG)


9. Click on Then That button.

![9](https://user-images.githubusercontent.com/490696/190654827-3fda6912-b251-49e3-815f-a6f59e1adec6.JPG)


10. Search for and select Smart Life.

![10](https://user-images.githubusercontent.com/490696/190654864-08935152-5a33-4a51-91c6-4465a78f3d5d.JPG)


11. Click on Turn On(since this example is for the low battery event).

![11](https://user-images.githubusercontent.com/490696/190659585-ae36d122-7916-484d-9760-e198f70513a5.JPG)


12. Select the Smart Life account name and the smart switch name under the device/group name.

![12](https://user-images.githubusercontent.com/490696/190659826-be4d3688-c077-4af4-84b4-3756f802fa88.JPG)


13. Click on Continue.

![13](https://user-images.githubusercontent.com/490696/190660062-01299e7b-8f1c-411b-8caf-1bdf69313eba.JPG)


14. Click on Finish.

![14](https://user-images.githubusercontent.com/490696/190660229-6c70301b-6650-4e5c-9cab-f29ab858331e.JPG)


15. The first Applet for turning the smart switch on is ready.

![15](https://user-images.githubusercontent.com/490696/190660270-16efa092-87b1-47c2-b672-b0167ed8b308.JPG)


16. Create a similar Applet for the event when the laptop battery gets fully charged. Select Webhooks again for the 
If This section and give a different event name. The example here has the event name as full_battery.

![16](https://user-images.githubusercontent.com/490696/190660669-8951de00-68c1-48b0-9be1-8db86a8eddd1.JPG)


17. Select Turn Off this time for the Smart Life configuration after clicking the Then That button.

![17](https://user-images.githubusercontent.com/490696/190660704-d83be759-ee48-477d-847f-ac758c964baf.JPG)


18. Select the Smart Life account name and the smart switch name under the device/group name.

![18](https://user-images.githubusercontent.com/490696/190660737-e5f62786-e1a5-4c33-b27f-526b13ab402a.JPG)


19. Click on Continue.

![19](https://user-images.githubusercontent.com/490696/190660824-70efafc5-20b7-47e0-a1fa-fe293c10b2ac.JPG)


20. Click on Finish.

![20](https://user-images.githubusercontent.com/490696/190660880-d8cda6ab-88d1-48e7-98ac-6f2a69f2f2b6.JPG)

21. The second Applet for turning the smart switch off is ready.

22. Search for Webhooks. Click on the documentation button. This page will display the key. This key will need to be
used along with the jar to be used in the next steps.
