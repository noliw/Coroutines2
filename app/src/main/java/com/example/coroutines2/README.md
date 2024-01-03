- A function is a sequence of instructions that takes inputs and gives us outputs
- Thread:
    - Describes in which context this sequence should be executed
    - In an android app, all instructions are executed in a single thread: The Main Thread
    - this can lead to some problems:
        - the Ui needs to be updated in the same thread
## Coroutines:
    - executes within a thread
    - are suspendable : We can pause and start when we want
    - can change context


- Every coroutine needs to be started in a scope
- GlobalScope means that the coroutine will live as long as the app does

## Suspend function:
- They can only be executed inside another suspend function or a crtn

## Coroutine context:
- crtns are always started in a specific context
- the context will describe in which thread our crtn will be started in

## Dispatchers:
- context they deal with the where, which thread
1. Dispatchers.Main:
  - this will start a coroutine within the main thread.
  - Usefull for UI operations
  - bc you can only change the UI from the main thread
2. - Dispatchers.IO:
   - used for all kinds of data operations
   - such as networking writing to databases or reading and writing to files
3. Dispatchers.Default:
   - used when doing long and complex calculations that will block the main thread
   - like sorting a list of 10k elements, this should be done here to not block the main thread
4. Dispatchers.Unconfined:
   - It is not confined to any thread
   - it will stay in the thread that the suspend function resumed
5. Start our own thread:
   - We can also start our own thread 
   - GlobalScope.launch(newSingleThreadContext(name = "MyThread))
- The best thing about context is that we can easily switch them within a cortn:
  - to do this we use withContext

## runblocking
- even if we call delay in a cortn it wont actually block the thread it is running in
- however the is a function that will start a fun in the main thread and also block it:
  - RUNBLOCKING
- The difference with GlobalScope.launch(){} is that run blocking will actually block the main thread
- if we use delay in runblicking it will delay our ui updates