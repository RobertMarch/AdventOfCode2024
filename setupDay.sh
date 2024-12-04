echo "Setting up day $1"

DAY_NUMBER=`printf "%02d" $1`
FILE_NAME="Day${DAY_NUMBER}.kt"
TEST_NAME="Day${DAY_NUMBER}Test.kt"

if test -f "$FILE_NAME"; then
    echo "File already exists"
    exit 1
fi

cp src/main/kotlin/days/DayXX.kt "src/main/kotlin/days/${FILE_NAME}"
sed -i 's/99/'"$1"'/' src/main/kotlin/days/$FILE_NAME
sed -i 's/XX/'"$DAY_NUMBER"'/g' src/main/kotlin/days/${FILE_NAME}

touch "src/main/resources/inputs/day$DAY_NUMBER.txt"

cp src/test/kotlin/days/DayXXTest.kt "src/test/kotlin/days/${TEST_NAME}"
sed -i 's/99/'"$1"'/' src/test/kotlin/days/$TEST_NAME
sed -i 's/XX/'"$DAY_NUMBER"'/g' src/test/kotlin/days/${TEST_NAME}

echo "Set up for day $1 complete"
