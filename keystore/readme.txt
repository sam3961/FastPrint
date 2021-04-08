####################################################################

keystore alias name : appypie
keystore Password :  appypie123


############################### jarsigner ###################zipalign##################
jarsigner -verbose -sigalg MD5withRSA -digestalg SHA1 -keystore {{_Path_of_Keystore_}} {{_Path_Of_sing_apk_}} -storepass appypie123  -keypass appypie123 appypie
