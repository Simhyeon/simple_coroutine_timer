## Simple Coroutine Timer

### Color Progress

### Wave Progress
- ~~현재로서는 내부구조가 getIntrinsic으로 되어 있어 device density에 따라 크기가 다른 문제가 있음.~~
- ~~getIntrinsic과 관련된 부분을 수정해보려고 했으나 효과가 없음. dpi별로 리소스 폴더를 만드는 방법을 조사중~~
- 그냥 color resource만을 활용하는 것이 나아보임. 대신 gradient를 넣는다면 구려보이진 않을 것....\
- Status Bar와 Navigation 바를 없애니 마술같이 된다. 현재로선 status bar와 navigation바를 포함하면서 코드 내에서 화면을 가득 채우는 건 어떻게 해야하는지를 모르겠음

window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        or View.SYSTEM_UI_FLAG_FULLSCREEN)
