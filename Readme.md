# IMT Calculator Readme



### 1. Installation

>File: IMT-Calculator-\app\build\outputs\apk\release\app-release.apk
### 2. Open project

### 3. First Activity
Upon launch, we are greeted by the first window of the calculator itself, where we can see 3 data text fields, gender selection and measurement system selection.
<table>
  <tr>
    <td><img src="app\src\main\res\drawable-v24\screen\firstFragment.png"></td>
    <td><img src="app\src\main\res\drawable-v24\screen\first2.png"></td>
  </tr>
</table>

> > File code: MainFragment.java 

### 4. HelperActivity
After we enter the data, the scale will change, the result will be displayed, below it is highlighted in blue and a question mark will appear.
When you click on the question mark, we will be transferred to the help selection screen. For example, if you are obese, there will be diets and gyms, and if you are underweight, there will be help in recruiting

<table>
  <tr>
    <td><img src="app\src\main\res\drawable-v24\screen\helper.png"></td>
  </tr>
</table>

### 5. FragmentCount
The application also has additional functions in the form of a pedometer. When you swipe to the right, a window with a pedometer opens.
When you click on the activate button, the pedometer will start, and when you click on stop, it will stop and save the data to the cloud database
The pedometer has the function of a glass of water, that is, when walking, the water balance of the human body is depleted, and when following the program, it will be possible to monitor this and drink water. until you finish your walk.
<table>
  <tr>
    <td><img src="app\src\main\res\drawable-v24\screen\count.png"></td>
<td><img src="app\src\main\res\drawable-v24\screen\water.png"></td>
  </tr>
</table>
Throughout the month, you can monitor when and how much you have walked by clicking on the check result button, you will be directed to the list of your walks window, where you can select any walk and view the results
<table>
  <tr>
    <td><img src="app\src\main\res\drawable-v24\screen\check1.png"></td>
<td><img src="app\src\main\res\drawable-v24\screen\check2.png"></td>
  </tr>
</table>