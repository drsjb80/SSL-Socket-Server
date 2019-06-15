    keytool -genkey -keyalg RSA -alias testkey -keypass testpwd -keystore testkeystore.ks
    keytool -export -alias testkey -file testkeystore.cer -keystore testkeystore.ks
    keytool -import -alias testkey -file testkeystore.cer -keystore pubtestkeystore.ks
