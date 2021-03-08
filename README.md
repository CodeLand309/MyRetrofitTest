# MyRetrofitTest
This is a sample app that demonstrates REST API. I have used Retrofit library. I have used Picasso library to load images. There are 3 values in the table.
1. Roll Number
2. Name 
3. Image


So basically we have a UI / Activity with 5 buttons. Each Button represents 5 commonly used HTTP requests.
1. Read (GET Request)
2. Write (POST Request)
3. Update (PUT Request)
4. Patch (PATCH Request)
5. Delete (DELETE Request)

When user clicks on the button he/she would be taken to respective activity.
Now below you can see screenshot of each activity.

Main UI/Activity
<p float="center">
  <img src="/images/Main UI.jpg" width="300" /> 
</p>
<br><br><br><br>

Read UI/Activity
Here the user can see all the data as a list of items.
<p float="center">
  <img src="/images/Write UI.jpg" width="300" /> 
</p>
<br><br><br><br>

Write Activity
Here the user enter all the values and uploads an image and does POST Request. If successful he/she can see the image uploaded and the values entered as shown below.
<p float="left">
  <img src="/images/Write UI.jpg" width="300" />
  <img src="/images/Write UI Response.png" width="300" /> 
</p>
<br><br><br><br>

Update UI/Activity
Here the user enters old roll number (used to get old details) and then he/she enter new details and creates a PUT Request. If successful he/she can see the updated values in response. Its actually "New Name". OOPs I had changed the name before updating. That is why the name is shown as New Name instead of New Value in response.
<p float="left">
  <img src="/images/Update UI.jpg" width="300" />
  <img src="/images/Update UI Response.png" width="300" /> 
</p>
<br><br><br><br>

Patch UI/Activity
Here the user enter old roll number and has option to change either Roll number or Name. Here we have chosen roll number and entered the new roll number. After that we get a response.
<p float="left">
  <img src="/images/Patch UI.jpg" width="300" />
  <img src="/images/Patch UI response.jpg" width="300" /> 
</p>
<br><br><br><br>


Delete UI/Activity
Here the user enters the name to identify row which has to be deleted. If found, he/she can see message "ok" as shown below.
<p float="left">
  <img src="/images/Delete UI.jpg" width="300" />
  <img src="/images/Delete UI response.jpg" width="300" /> 
</p>
