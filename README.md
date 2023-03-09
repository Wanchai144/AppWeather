# AppWeather


<img width="273" alt="Screen Shot 2566-03-09 at 22 59 16" src="https://user-images.githubusercontent.com/68962015/224081117-d3460310-f35a-4de7-b401-30c0751d6f97.png">
<img width="276" alt="Screen Shot 2566-03-09 at 22 59 05" src="https://user-images.githubusercontent.com/68962015/224081136-50e5d9ee-7c70-4a39-b64b-95b4b8837a00.png">


Communication between layers

UI calls method from ViewModel.
ViewModel executes Use case.
Use case combines data from Album and Photo Repositories.
Each Repository returns data from a Data Source (Cached or Remote).
Information flows back to the UI where we display the list of posts.

**Architecture**

MVVM  Clean Architecture 

**Dependency Injection**

Koin

**Threading**

Coroutines


**มีฟีเจอร์ใหม่ๆ ให้อัพเดทตลอดเวลามีลูกเล่นโดดเด่น ไม่ว่าจะเป็นการปักหมุดเพื่อดูอุญภมิรายวันในเเต่ละพื้นที่หรือสามารถเลือกสถานที่เพื่อให้สะดวกยิ่งขึ้น**
