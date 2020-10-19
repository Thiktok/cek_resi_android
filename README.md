# Cek Resi Android

Aplikasi android untuk cek resi, menggunakan API dari binderbyte

Support pengecekan resi dengan kurir:
1. JNE - ok
2. SiCepat - ok
3. Ninja van - ok
4. Pos Indonesia - ok
5. Wahana - not tested
6. Tiki - not tested
7. anteraja - ok
8. JNT - ok

Fitur tambahan:
1. History
2. Scan barcode resi

### Techstack:
1. Kotlin
2. MVVM-LiveData pattern
3. Rest-API with Retrofit
4. Dependency Injection with Dagger 2 
5. Barcode scanner with zxing

### Screenshot:
<p float="left">
<img src="https://github.com/Ram-adhan/md-photo/blob/master/courier-tracking-android/Screenshot_20201019-195030_Courier%20Tracking.jpg" width="200" title="Main Screen">
<img src="https://github.com/Ram-adhan/md-photo/blob/master/courier-tracking-android/Screenshot_20201019-195034_Courier%20Tracking.jpg" width="200" title="Courier list">
<img src="https://github.com/Ram-adhan/md-photo/blob/master/courier-tracking-android/Screenshot_20201019-195045_Courier%20Tracking.jpg" width="200" title="Barcode scan">
<img src="https://github.com/Ram-adhan/md-photo/blob/master/courier-tracking-android/Screenshot_20201019-195103_Courier%20Tracking.jpg" width="200" title="Detail tracking">
</p>

### TL:DR;
- To build your own apk, add Object named Service Data contains BASE_URL(use from binderbyte), API_KEY(from binderbyte)
remove any ad-related line code (if you dont want to use ad)
- To try the generated apk, search in folder release
