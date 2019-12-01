# RIPE Atlas 2 Go
##### Android App for Ripe Atlas

This App is part of my Master Thesis called "Untersuchung zum Einsatz der RIPE Atlas Plattform für Messung von Internet Diensten".
The platform RIPE Atlas is building the largest Internet measurement network.
With your help, you can expand the network, if you become an user and host your own probe.
For further information check out the RIPE Atlas website:

[RIPE Atlas](https://atlas.ripe.net/)

As user you can perform your own measurements and test your own targets.
This App allows you to monitor your profile with your Measurements, Credits and API Keys.
For the functionality the app uses the RIPE Atlas API.
The manual can be found here:

[RIPE Atlas APIs Manual](https://atlas.ripe.net/docs/api/v2/manual)

### Installation

###### Enable installing "unkown apps"
1. Go to Settings --> Apps and Notifications
2. Select Install Unknown Apps (or Install Other Apps).

###### Install the apk
1. Download the APK file "app-release.apk" from [/ripe-atlas-2-go/app/release/](release)
2. Open your Android's file manager app.
3. Go to the Downloads folder on your device.
4. Tap the APK file.
5. Allow the app any required permissions it asks for.
6. Then, at the bottom of the installer window, click INSTALL.

### Instruction
###### API Keys
1. Create all necessary API Keys via the website:
[https://atlas.ripe.net/keys/](ripe_atlas_link_keys)
2. Go to API Keys to add all your created keys to the app.
3. Click \"Add API Keys\" and type the UUID of the API Key with the permission \"List all of your api keys\". To ease the typing of the long UUID, you can create a QR Code of the UUID and scan this code. To create a QR Code use a online tool like this:
[https://qrcode.tec-it.com/](ripe_atlas_link_qr)
4. Finally you see all your imported keys in the list view. Now you can start with the other activities.
###### Credits
1. Go to Credits and check your actual information about your credits.
2. Click \"Check Credits\" and choose your API Key with the permission \"Get information about your credits\".
3. It\'s theoretically possible to transfer credits to other users, but it\'s temporally not possible (seems to be a bug of the RIPE Atlas API)
###### Measurements
1. Go to Measurements to add or create measurements.
2. Click \"Add Measurements\" to add measurements to the app. You can choose between all, a typical type or a specific measurement. For a couple of measurements use an API Key with the permission \"List your measurements\". For a specific measurement use an API Key with the permission \"Get results from a non-public measurement\" and specify the measurement with his ID.
3. Finally you see all your imported measurements in the list view. Click on a measurement element to get detailed information.
4. Click \"Create Measurement\" to create a new measurement via the app. You can specify your parameters via an input wizzard.
5. Finally you see an ID of your created measurement. Now you can also add this measurement to the list view.

Also check this out:
[RIPE Atlas Introduction Video](RipeAtlas2Go_Introduction.mp4)
