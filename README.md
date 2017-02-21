# BackgroundCommunication

Helps creating background tasks.

# To create a task that must run in background
- Create a class that implements <code>BackgroundCommunication</code>
- In the method <code>doCommunication()</code>, call your API.
- Start your service:
<br>
<code>
        Intent intentService = new Intent(context, CommunicationService.class);
        intentService.putExtra(context.getString(R.string.intent_communication), backgroundCommunication);
        context.startService(intentService);
</code>      
* Where <code>backgroundCommunication</code> is your class that implements BackgroundCommunication.

# To create a task that run periodically in background
- Create a class that extends <code>BackgroundCommunicationActions</code>
- Create a method to schedule your task with the periodicity you need. This method must call <code>BackgroundCommunicationActions.schedule</code>
- In your <code>Application</code>, run your scheduling method.
- Take care to kill your task when the app is terminated.

#Example
- Package <code>weather</code> contains the implementation for both approaches above.
