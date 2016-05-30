MissileGuidedForAndroid
=======================

![Missile Guided For Android](image_readme.jpg)

Again we come back with the same problems of Android and Java; Serialization and methods unvalidated.

This time, our new security flaw, It allows us crashing applications, creating a permanent denial of service (DoS), if runs that, can crashing repeatedly, 
and stop application completely and indefinitely, too, this security issue affects all applications Android from version SDK 2.0 to 6+.

Well, the reason this happens is very simple: Each application has a different ClassLoader, which has classes of the core android and the current compiled APK,
referenced by a DexClassLoader, and only that, not the other clases compiled in other APK's.

It is really simple to send a Parcelable or Serializable to an application, service, methods, etc, if the application does not contain these classes simply crash (explodes), Well with this simple theory, just enough to find entry points to send these Parcelable and / or Serializable.

Logcat Application: [Logcat](https://raw.githubusercontent.com/JhetoX/MissileGuidedForAndroid/master/logcat_app.log)<BR/>
Logcat Service: [Logcat](https://raw.githubusercontent.com/JhetoX/MissileGuidedForAndroid/master/logcat_srv.log)<BR/>

<b>Video PoC:</b><br>
[![Video PoC](http://img.youtube.com/vi/4bfE_hIlayM/0.jpg)](http://www.youtube.com/watch?v=4bfE_hIlayM)

Note: Because I do not bring anything, simple, always they do not answer me that is not a security breach, or do not respond to me, and every time they do not even thanks to me, sorry WhatsApp, is easy for me test things with you application.


Happy Cyber War \m/<BR/><BR/>

 

This code was developed by me: Jheto Xekri


You can contact me in:

Profile web: http://about.me/jheto.xekri<br>
or by Email: jheto.xekri@outlook.com<br>
or by Whatsapp: +573122844198<br>
or by Viber: +573122844198<br>
or by Skype: jheto.xekri

Donations:

Coinbase: 1NzDu9iuZJPbsyQJxMFtk4YfWPMyVgNea1<br>
Paypal: jheto.xekri@outlook.com
