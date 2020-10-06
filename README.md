# MVVMExample
Android Architecture Component를 이용한 간단한 MVVM 예제 프로젝트입니다. 영화 리스트를 retrofit을 이용하여 가져와 RecyclerView에 나타내고 아이템을 선택하여 보고싶은 영화에 추가할 수 있습니다. MVVM 패턴을 이용하였고, 두개의 프래그먼트를 사용하여 이동간에는 navigation 컴포넌트를 활용했습니다. 그리고 보고 싶은 영화 목록은 스와이프, 드래그앤 드랍으로 삭제 및 이동이 가능합니다.

<img src="https://user-images.githubusercontent.com/30337408/89999297-908e3f00-dcc9-11ea-9465-be85a4fef237.png" width=60% height=auto>

## 활용 라이브러리 및 기술 📖 

* Room
* LiveData
* ViewModel
* Databinding
* Coroutine
* Navigation
* ListAdapter
* Retrofit
* Glide
* ItemTouchHelper

## 구현 화면 🎨

![ezgif com-video-to-gif (1)](https://user-images.githubusercontent.com/30337408/91189632-a4a45880-e72d-11ea-9f78-6cc306f01e2b.gif)


## 참조 📃 

[블로그]
https://bb-library.tistory.com/95?category=843046


[영화 API 참조]
https://developers.themoviedb.org/3/movies/get-movie-details
