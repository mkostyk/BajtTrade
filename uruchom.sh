mvn exec:java -Dexec.mainClass=main.Main -Dexec.args="$1 $2"
read -n1 -r -p "Press any key to continue..." key