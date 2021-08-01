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
<p float="left">
<img src="https://user-images.githubusercontent.com/52147185/127768549-a28a10ba-be4e-4c02-8d94-c3025bb74b2b.gif" width="250" title="Cek Resi">
<img src="https://user-images.githubusercontent.com/52147185/127769847-aa20c7ff-0874-44f9-880e-7359715193f9.gif" width="250" title="Cek Ongkir">
</p>

### Screenshot:
<p float="left">
<img src="https://user-images.githubusercontent.com/52147185/127769930-41bf6724-16ab-4763-bd46-e4b8d8d031ce.jpg" width="250" title="Main Screen">
<img src="https://user-images.githubusercontent.com/52147185/127769927-6abcb969-e75f-485c-820e-aad68c45bdc5.jpg" width="250" title="Courier list">
<img src="https://user-images.githubusercontent.com/52147185/127769919-719fca74-0d81-48a8-bf56-d08fc5b0fa6a.jpg" width="250" title="Barcode scan">
<img src="https://user-images.githubusercontent.com/52147185/127769928-52c43cb6-c3fb-4d6d-a016-3ef14267cc06.jpg" width="250" title="Detail tracking">
<img src="https://user-images.githubusercontent.com/52147185/127769932-b91c1f47-b5d9-4c74-b8e7-9b35fa15ddff.jpg" width="250" title="Check Ongkir">
<img src="https://user-images.githubusercontent.com/52147185/127769933-db72dd4e-460b-470c-a3ea-6bc956db5650.jpg" width="250" title="Detail Ongkir">
</p>

### TL:DR;
- To build your own apk, add Object named Service Data contains BASE_URL(use from binderbyte), API_KEY(from binderbyte),
remove any ad-related line code (if you dont want to use ad) e.g MobileAd.initialize, initAds function
- To try the generated apk, search in folder release [Generated APK](https://github.com/Ram-adhan/cek_resi_android/blob/master/app/release/app-release.apk)
- Now released on Google Play - [Google Play](https://play.google.com/store/apps/details?id=com.inbedroom.couriertracking)
