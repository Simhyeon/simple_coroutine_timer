# Simple Coroutine Timer

## Installation

- 클론으로 다운받아서 직접 코드를 사용 (비추천)

```git
git clone https://github.com/Simhyeon/simple_coroutine_timer
```

- WaveTimerView.jar 를 다운 받아서 사용 (ColorProgress.kt는 미 포함)

- 라이브러리 사용 환경

  - 코틀린 버전 1.3.x으로 설정

  ![Kotlin_Plugin_Setting](kotlin_plugin_setting.png)

  - build.gradle (Proejct)

  ```gradle
  buildscript {
    ext.kotlin_version = '1.3.50'
    ...

    dependencies {
        ...
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        ...
    }
  }

  ```

  - build.gradle (Module)

  ```gradle
  apply plugin: 'com.android.application'
  apply plugin: 'kotlin-android'

    dependencies {
        ...
        implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1'
        implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1'
        ...
    }

  ```

## Color Progress

- 단순하게 화면을 채우는 타이머, 아마 쓰지는 않을 예정

## Wave Progress

- 데모

![WaveProgress_DEMO](WaveProgress.gif)

- 물결 모양으로 화면을 채우는 타이머

- 예시 (코틀린, 자바)

  - 코틀린

  ```kotlin
    var waveTimerView: WaveTimerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.wave_progress)

        waveTimerView = WaveTimerView(this, waveRoot, 33, 120)
        waveTimerView!!.setWaveDrawable(R.drawable.gradient_red_salvation, Color.argb(100,255,255,255), PorterDuff.Mode.SCREEN)
        //waveTimerView.setWaveDrawable(R.drawable.gradient_morpheus_den, Color.argb(0,255,255,255), PorterDuff.Mode.SRC)
        waveTimerView!!.requestLayout()

        waveTimerView!!.setOnClickListener {
            Toast.makeText(this, "Toggled", Toast.LENGTH_SHORT).show()
            waveTimerView!!.toggleTimer()
        }
    }

    override fun onPause() {
        super.onPause()
        waveTimerView!!.endTimer()
    }
  ```

  - 자바

  ```java
    WaveTimerView waveTimerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup rootView = findViewById(R.id.rootView);
        waveTimerView = new WaveTimerView(this, rootView, 33, 60);
        waveTimerView.setWaveDrawable(R.color.colorAccent, Color.argb(100, 255, 255, 255), PorterDuff.Mode.SCREEN);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveTimerView.toggleTimer();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        waveTimerView.endTimer();
    }
  ```

- 메서드 스펙

  - WaveTimerView.kt

   ```kotlin
   WaveTimerView(context: Context, rootViewGroup: ViewGroup, var delayMilliSeconds: Int, var durationS: Int) : ImageView(context)
   // WaveTimerView의 생성자
   fun setWaveDrawable(colorRes: Int) : CorocWaveDrawable?
   // WaveTimerView에 drawable 오브젝트를 할당하는 메서드 기본적으로는 색을 전달한다. gradient Color도 가능
   fun setWaveDrawable(colorRes: Int, bgColorFilter: Int, filterMode: PorterDuff.Mode = PorterDuff.Mode.SRC) : CorocWaveDrawable?
   // 배경의 컬러필터도 임의로 할당한다.
   fun toggleTimer() /*타이머를 토글한다.*/
   fun endTimer() // 타이머를 종료한다.
   fun restartTimer() // 타이머를 재시작한다.
   ```
