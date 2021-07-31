# Cek Resi Android | Air Waybill Track

Aplikasi android untuk cek resi, menggunakan API dari binderbyte

Support pengecekan resi dengan kurir:
1. JNE - ok
2. SiCepat - ok
3. Ninja van - ok
4. Pos Indonesia - ok
5. Wahana - ok
6. Tiki - ok
7. anteraja - ok
8. JNT - ok
9. dll

Fitur tambahan:
1. History
2. Scan barcode resi
3. Cek Ongkir

### Techstack:
1. Kotlin
2. MVVM-LiveData pattern
3. Rest-API with Retrofit
4. Dependency Injection with Dagger 2 
5. Barcode scanner with zxing
6. Room

### Demo:
<img src="https://user-images.githubusercontent.com/52147185/97078379-186fbe00-1616-11eb-88bb-e36dd3c33026.gif" width="250" title="Usage">

### Screenshot:
<p float="left">
<img src="https://user-images.githubusercontent.com/52147185/97077914-d04e9c80-1611-11eb-91f2-732d5b1d1414.jpg" width="250" title="Main Screen">
<img src="https://user-images.githubusercontent.com/52147185/97077920-d80e4100-1611-11eb-80a3-b541d7b627d6.jpg" width="250" title="Courier list">
<img src="https://user-images.githubusercontent.com/52147185/97077921-d8a6d780-1611-11eb-940b-0a4fc4dd35c2.jpg" width="250" title="Barcode scan">
<img src="https://user-images.githubusercontent.com/52147185/97077945-2cb1bc00-1612-11eb-892c-1d8369ba206d.jpg" width="250" title="Detail tracking">
</p>

### TL:DR;
- To build your own apk, add Object named Service Data contains BASE_URL(use from binderbyte), API_KEY(from binderbyte),
remove any ad-related line code (if you dont want to use ad) e.g MobileAd.initialize, initAds function
- To try the generated apk, search in folder release [Generated APK](https://github.com/Ram-adhan/cek_resi_android/blob/master/app/release/app-release.apk)
- Now released on Google Play - [Google Play](https://play.google.com/store/apps/details?id=com.inbedroom.couriertracking)
